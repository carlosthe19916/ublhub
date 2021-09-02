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
package io.github.project.openubl.xsender.scheduler.commons;

import io.github.project.openubl.xmlsenderws.webservices.providers.BillServiceModel;
import io.github.project.openubl.xmlsenderws.webservices.xml.XmlContentModel;
import io.github.project.openubl.xsender.models.ErrorType;
import io.github.project.openubl.xsender.sender.XSenderConfig;

import java.util.Date;

public final class DocumentUniTicketBuilder {
    protected String namespaceId;
    protected String id;
    protected Integer retries;
    protected ErrorType error;
    protected boolean inProgress;
    protected Date scheduledDelivery;
    protected XmlContentModel xmlContent;
    protected XSenderConfig wsConfig;
    protected BillServiceModel billServiceModel;
    protected String cdrFileId;
    private String ticket;

    private DocumentUniTicketBuilder() {
    }

    public static DocumentUniTicketBuilder aDocumentUniTicket() {
        return new DocumentUniTicketBuilder();
    }

    public DocumentUniTicketBuilder withNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
        return this;
    }

    public DocumentUniTicketBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public DocumentUniTicketBuilder withRetries(Integer retries) {
        this.retries = retries;
        return this;
    }

    public DocumentUniTicketBuilder withError(ErrorType error) {
        this.error = error;
        return this;
    }

    public DocumentUniTicketBuilder withInProgress(boolean inProgress) {
        this.inProgress = inProgress;
        return this;
    }

    public DocumentUniTicketBuilder withScheduledDelivery(Date scheduledDelivery) {
        this.scheduledDelivery = scheduledDelivery;
        return this;
    }

    public DocumentUniTicketBuilder withXmlContent(XmlContentModel xmlContent) {
        this.xmlContent = xmlContent;
        return this;
    }

    public DocumentUniTicketBuilder withWsConfig(XSenderConfig wsConfig) {
        this.wsConfig = wsConfig;
        return this;
    }

    public DocumentUniTicketBuilder withBillServiceModel(BillServiceModel billServiceModel) {
        this.billServiceModel = billServiceModel;
        return this;
    }

    public DocumentUniTicketBuilder withCdrFileId(String cdrFileId) {
        this.cdrFileId = cdrFileId;
        return this;
    }

    public DocumentUniTicketBuilder withTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public DocumentUniTicket build() {
        DocumentUniTicket documentUniTicket = new DocumentUniTicket();
        documentUniTicket.setNamespaceId(namespaceId);
        documentUniTicket.setId(id);
        documentUniTicket.setRetries(retries);
        documentUniTicket.setError(error);
        documentUniTicket.setInProgress(inProgress);
        documentUniTicket.setScheduledDelivery(scheduledDelivery);
        documentUniTicket.setXmlContent(xmlContent);
        documentUniTicket.setWsConfig(wsConfig);
        documentUniTicket.setBillServiceModel(billServiceModel);
        documentUniTicket.setCdrFileId(cdrFileId);
        documentUniTicket.setTicket(ticket);
        return documentUniTicket;
    }
}
