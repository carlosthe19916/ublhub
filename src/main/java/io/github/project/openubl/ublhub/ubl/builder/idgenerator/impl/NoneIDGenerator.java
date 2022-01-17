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
package io.github.project.openubl.ublhub.ubl.builder.idgenerator.impl;

import io.github.project.openubl.ublhub.models.jpa.entities.NamespaceEntity;
import io.github.project.openubl.ublhub.ubl.builder.idgenerator.ID;
import io.github.project.openubl.ublhub.ubl.builder.idgenerator.IDGenerator;
import io.github.project.openubl.ublhub.ubl.builder.idgenerator.IDGeneratorProvider;
import io.github.project.openubl.ublhub.ubl.builder.idgenerator.IDGeneratorType;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@IDGeneratorProvider(IDGeneratorType.none)
public class NoneIDGenerator implements IDGenerator {
    
    @Override
    public Uni<ID> generateInvoiceID(NamespaceEntity namespace, String ruc, boolean isFactura) {
        return Uni.createFrom().nullItem();
    }

    @Override
    public Uni<ID> generateCreditNoteID(NamespaceEntity namespace, String ruc, boolean isFactura) {
        return Uni.createFrom().nullItem();
    }

    @Override
    public Uni<ID> generateDebitNoteID(NamespaceEntity namespace, String ruc, boolean isFactura) {
        return Uni.createFrom().nullItem();
    }

    @Override
    public Uni<ID> generateVoidedDocumentID(NamespaceEntity namespace, String ruc, boolean isPercepcionRetencionOrGuia) {
        return Uni.createFrom().nullItem();
    }

    @Override
    public Uni<ID> generateSummaryDocumentID(NamespaceEntity namespace, String ruc) {
        return Uni.createFrom().nullItem();
    }
}
