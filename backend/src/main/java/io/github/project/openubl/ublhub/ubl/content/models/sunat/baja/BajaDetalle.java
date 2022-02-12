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
package io.github.project.openubl.ublhub.ubl.content.models.sunat.baja;

import io.github.project.openubl.ublhub.ubl.content.catalogs.Catalog1;
import io.github.project.openubl.ublhub.ubl.content.catalogs.validation.CatalogConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BajaDetalle {

    @NotBlank
    @Pattern(regexp = "^([F|B|P|R|T][A-Z]?[0-9]{0,3})[\\-]([0-9]{1,8})$")
    public String serieNumero;

    @NotBlank
    @CatalogConstraint(value = Catalog1.class)
    public String tipoComprobante;

    @NotNull
    public Long fechaEmision;

}