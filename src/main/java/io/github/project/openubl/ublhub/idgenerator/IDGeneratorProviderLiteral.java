/*
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
package io.github.project.openubl.ublhub.idgenerator;

import javax.enterprise.util.AnnotationLiteral;

public class IDGeneratorProviderLiteral extends AnnotationLiteral<IDGeneratorProvider> implements IDGeneratorProvider {

    private final IDGeneratorType providerType;

    public IDGeneratorProviderLiteral(IDGeneratorType providerType) {
        this.providerType = providerType;
    }

    @Override
    public IDGeneratorType value() {
        return providerType;
    }

}