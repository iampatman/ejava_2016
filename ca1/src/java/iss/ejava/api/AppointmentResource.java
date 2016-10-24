package iss.ejava.api;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customerId}")
    public Response get(@PathParam("customerId") String customerId){
        return Response.status(Response.Status.ACCEPTED)
                       .build();
    }
}
