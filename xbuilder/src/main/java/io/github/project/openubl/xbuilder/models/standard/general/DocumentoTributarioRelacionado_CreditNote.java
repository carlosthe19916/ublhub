/**
 * Copyright 2019 Project OpenUBL, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Eclipse Public License - v 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.project.openubl.xbuilder.models.standard.general;

import io.github.project.openubl.xcontent.catalogs.Catalog12_Doc_Trib_Relacionado_NotaCredito;
import io.github.project.openubl.xcontent.catalogs.validation.CatalogConstraint;

public class DocumentoTributarioRelacionado_CreditNote extends BaseDocumentoTributarioRelacionado {

    @CatalogConstraint(value = Catalog12_Doc_Trib_Relacionado_NotaCredito.class)
    public String tipoDocumento;

}
