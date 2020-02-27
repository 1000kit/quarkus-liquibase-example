/*
 * Copyright 2020 1000kit.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tkit.quarkus.liquibase;

import io.quarkus.liquibase.LiquibaseContext;
import io.quarkus.liquibase.LiquibaseFactory;
import liquibase.Liquibase;
import liquibase.changelog.ChangeSetStatus;
import liquibase.changelog.RanChangeSet;
import liquibase.exception.LiquibaseException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("liquibase")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LiquibaseRestController {

    @Inject
    LiquibaseFactory liquibaseFactory;

    @GET
    public Response getChangeLogs() {
        try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
            List<RanChangeSet> tmp = liquibase
                    .getChangeSetStatuses(liquibaseFactory.createContexts(), liquibaseFactory.createLabels())
                    .stream()
                    .map(ChangeSetStatus::getRanChangeSet)
                    .collect(Collectors.toList());
            return Response.ok(tmp).build();
        } catch (Exception e) {
           throw new InternalServerErrorException(e);
        }
    }
}
