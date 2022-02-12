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
package io.github.project.openubl.ublhub.ubl.content.catalogs;

public enum Catalog12_Doc_Trib_Relacionado_BoletaFactura implements Catalog {
    TICKET_DE_SALIDA("04"),
    CODIGO_SCOP("05"),
    FACTURA_ELECTRONICA_REMITENTE("06"),
    GUIA_DE_REMISION_REMITENTE("07"),
    DECLARACION_DE_SALIDA_DEL_DEPOSITO_FRANCO("08"),
    DECLARACION_SIMPLIFICADA_DE_IMPORTACION("09"),
    OTROS("99");

    private final String code;

    Catalog12_Doc_Trib_Relacionado_BoletaFactura(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

}
