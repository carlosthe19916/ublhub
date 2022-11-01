/*
 * Copyright 2019 Project OpenUBL, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.project.openubl.ublhub.models.jpa;

import io.github.project.openubl.ublhub.keys.component.ComponentModel;
import io.github.project.openubl.ublhub.keys.component.ComponentOwner;
import io.github.project.openubl.ublhub.keys.component.utils.ComponentUtil;
import io.github.project.openubl.ublhub.models.jpa.entities.ComponentConfigEntity;
import io.github.project.openubl.ublhub.models.jpa.entities.ComponentEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import org.keycloak.common.util.MultivaluedHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.github.project.openubl.ublhub.keys.component.ComponentOwner.OwnerType.project;

@ApplicationScoped
public class ComponentRepository implements PanacheRepositoryBase<ComponentEntity, String> {

    @Inject
    ComponentUtil componentUtil;

    private String getOwnerFieldName(ComponentOwner owner) {
        return owner.getType().equals(project) ? "projectId" : "companyId";
    }

    public Uni<ComponentModel> addComponentModel(ComponentOwner owner, ComponentModel model) {
        return importComponentModel(owner, model);
    }

    public Uni<ComponentModel> importComponentModel(ComponentOwner owner, ComponentModel model) {
        return Uni.createFrom().item(() -> componentUtil.getComponentFactory(model))
                .onItem().ifNull().failWith(() -> new IllegalArgumentException("Invalid component type"))
                .onItem().ifNotNull().invoke(componentFactory -> componentFactory.validateConfiguration(owner, model))
                .chain(componentFactory -> {
                    ComponentEntity c = new ComponentEntity();
                    if (model.getId() == null) {
                        c.setId(UUID.randomUUID().toString());
                    } else {
                        c.setId(model.getId());
                    }
                    c.setName(model.getName());
                    c.setParentId(model.getParentId());
                    if (model.getParentId() == null) {
                        c.setParentId(owner.getId());
                        model.setParentId(owner.getId());
                    }
                    c.setProviderType(model.getProviderType());
                    c.setProviderId(model.getProviderId());
                    c.setSubType(model.getSubType());
                    if (owner.getType().equals(project)) {
                        c.setProjectId(owner.getId());
                    } else {
                        c.setCompanyId(owner.getId());
                    }

                    return c.<ComponentEntity>persist();
                })
                .map(c -> {
                    setConfig(model, c);
                    model.setId(c.getId());
                    return model;
                });
    }

    protected void setConfig(ComponentModel model, ComponentEntity c) {
        c.getComponentConfigs().clear();
        for (String key : model.getConfig().keySet()) {
            List<String> vals = model.getConfig().get(key);
            if (vals == null) {
                continue;
            }
            for (String val : vals) {
                ComponentConfigEntity config = new ComponentConfigEntity();
                config.setId(UUID.randomUUID().toString());
                config.setName(key);
                config.setValue(val);
                config.setComponent(c);
                c.getComponentConfigs().add(config);
            }
        }
    }

    public Uni<Void> updateComponent(ComponentOwner owner, ComponentModel component) {
        return Uni.createFrom()
                .<Void>emitter(uniEmitter -> {
                    try {
                        componentUtil.getComponentFactory(component).validateConfiguration(owner, component);
                        uniEmitter.complete(null);
                    } catch (Throwable e) {
                        uniEmitter.fail(e);
                    }
                })
                .chain(() -> ComponentEntity.<ComponentEntity>findById(component.getId()))
                .chain(c -> {
                    c.setName(component.getName());
                    c.setProviderId(component.getProviderId());
                    c.setProviderType(component.getProviderType());
                    c.setParentId(component.getParentId());
                    c.setSubType(component.getSubType());
                    setConfig(component, c);
                    return c.<ComponentEntity>persist();
                })
                .chain(() -> Uni.createFrom().voidItem());
    }

    public Uni<Boolean> removeComponent(ComponentOwner owner, ComponentModel component) {
        return removeComponents(owner, component.getId())
                .chain(() -> ComponentEntity.deleteById(component.getId()));
    }

    public Uni<Long> removeComponents(ComponentOwner owner, String parentId) {
        String query = new StringBuilder(getOwnerFieldName(owner)).append(" = :ownerId")
                .append(" and parentId = :parentId")
                .toString();

        return ComponentEntity.delete(query, Parameters.with("ownerId", owner.getId()).and("parentId", parentId));
    }

    public Uni<List<ComponentModel>> getComponents(ComponentOwner owner) {
        String query = new StringBuilder("SELECT DISTINCT c FROM ComponentEntity c LEFT JOIN FETCH c.componentConfigs")
                .append(" WHERE c.").append(getOwnerFieldName(owner)).append(" = :ownerId")
                .toString();
        return ComponentEntity
                .find(query, Parameters.with("ownerId", owner.getId()))
                .<ComponentEntity>list()
                .map(entities -> entities.stream()
                        .map(this::entityToModel)
                        .collect(Collectors.toList())
                );
    }

    public Uni<List<ComponentModel>> getComponents(ComponentOwner owner, String parentId) {
        String query = new StringBuilder("SELECT DISTINCT c FROM ComponentEntity c LEFT JOIN FETCH c.componentConfigs")
                .append(" WHERE c.").append(getOwnerFieldName(owner)).append(" = :ownerId")
                .append(" and c.parentId = :parentId")
                .toString();
        return ComponentEntity
                .find(query, Parameters.with("ownerId", owner.getId()).and("parentId", parentId)
                ).<ComponentEntity>list()
                .map(entities -> entities.stream()
                        .map(this::entityToModel)
                        .collect(Collectors.toList())
                );
    }

    public Uni<List<ComponentModel>> getComponents(ComponentOwner owner, String parentId, String providerType) {
        String query = new StringBuilder("SELECT DISTINCT c FROM ComponentEntity c LEFT JOIN FETCH c.componentConfigs")
                .append(" WHERE c.").append(getOwnerFieldName(owner)).append(" = :ownerId")
                .append(" and c.parentId = :parentId")
                .append(" and c.providerType = :providerType")
                .toString();

        return ComponentEntity
                .find(query, Parameters.with("ownerId", owner.getId()).and("parentId", parentId).and("providerType", providerType))
                .<ComponentEntity>list()
                .map(entities -> entities.stream()
                        .map(this::entityToModel)
                        .collect(Collectors.toList())
                );
    }

    public Uni<ComponentModel> getComponent(ComponentOwner owner, String id) {
        String query = new StringBuilder("SELECT DISTINCT c FROM ComponentEntity c LEFT JOIN FETCH c.componentConfigs")
                .append(" WHERE c.").append(getOwnerFieldName(owner)).append(" = :ownerId")
                .append(" and c.id = :id")
                .toString();
        return ComponentEntity
                .<ComponentEntity>find(query, Parameters.with("ownerId", owner.getId()).and("id", id))
                .firstResult()
                .map(entity -> entity != null ? entityToModel(entity) : null);
    }

    private ComponentModel entityToModel(ComponentEntity c) {
        ComponentModel model = new ComponentModel();
        model.setId(c.getId());
        model.setName(c.getName());
        model.setProviderType(c.getProviderType());
        model.setProviderId(c.getProviderId());
        model.setSubType(c.getSubType());
        model.setParentId(c.getParentId());
        MultivaluedHashMap<String, String> config = new MultivaluedHashMap<>();
        for (ComponentConfigEntity configEntity : c.getComponentConfigs()) {
            config.add(configEntity.getName(), configEntity.getValue());
        }
        model.setConfig(config);
        return model;
    }

}