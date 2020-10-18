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
package io.github.project.openubl.xsender.core.resources.admin;

import io.github.project.openubl.xsender.core.idm.OrganizationRepresentation;
import io.github.project.openubl.xsender.core.idm.PageRepresentation;
import io.github.project.openubl.xsender.core.resources.Paths;

import javax.ws.rs.*;
import java.util.List;

@Path(Paths.ORGANIZATIONS)
@Produces("application/json")
@Consumes("application/json")
public interface OrganizationsResource {

    /**
     * List organizations
     */
    @GET
    @Path("/")
    PageRepresentation<OrganizationRepresentation> listOrganizations(
            @QueryParam("name") String name,
            @QueryParam("offset") @DefaultValue("0") Integer offset,
            @QueryParam("limit") @DefaultValue("10") Integer limit,
            @QueryParam("sort_by") @DefaultValue("name") List<String> sortBy
    );

}

