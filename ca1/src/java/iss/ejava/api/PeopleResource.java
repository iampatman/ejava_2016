/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ejava.api;

import iss.ejava.manager.PeopleManager;
import iss.ejava.model.People;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author HieuTranNgoc
 */
@RequestScoped
@Path("/people")
public class PeopleResource {
    @EJB PeopleManager pm;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void add(MultivaluedMap<String, String> form){
        People people = new People();
        people.setName(form.getFirst("name"));
        people.setEmail(form.getFirst("email"));
        pm.add(people);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("email") String email){
        Optional<People> opt = pm.findByEmail(email);
        if (opt.isPresent()) {
            People people = opt.get();
            JsonObject object = Json.createObjectBuilder()
                        .add("name", people.getName())
                        .add("email", people.getEmail())
                        .build();
            return Response.status(Response.Status.OK)
                       .entity(object)
                       .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Cannot find the user name with email " + email)
                           .build();
        }

    }
}
