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
package io.github.project.openubl.ublhub.models.jpa.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "generated_id", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"namespace_id", "ruc", "document_type"})
})
public class GeneratedIDEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
    public String id;

    @NotNull
    @Size(max = 11)
    @Column(name = "ruc")
    public String ruc;

    @NotNull
    @Size(max = 50)
    @Column(name = "document_type")
    public String documentType;

    @NotNull
    @Min(1)
    @Column(name = "serie")
    public int serie;

    @NotNull
    @Min(1)
    @Column(name = "numero")
    public int numero;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "namespace_id")
    public NamespaceEntity namespace;

    @Version
    @Column(name = "version")
    public int version;

}