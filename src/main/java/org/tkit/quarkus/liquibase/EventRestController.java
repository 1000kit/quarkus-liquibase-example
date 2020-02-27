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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("event")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventRestController {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("all")
    public Response findAll() {
        List<Event> entities = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        if (entities == null) {
            return Response.noContent().build();
        }
        return Response.ok(entities).build();
    }

    @GET
    @Path("{guid}")
    public Response findByGuid(@PathParam("guid") String guid) {
        Event event = em.find(Event.class, guid);
        if (event == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(event).build();
    }

    @POST
    @Transactional(Transactional.TxType.REQUIRED)
    public Response create(@Context UriInfo uriInfo, Event event) {
        em.persist(event);;
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(event.getGuid());
        return Response.created(builder.build()).entity(event).build();
    }
}
