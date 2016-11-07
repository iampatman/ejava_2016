package iss.ft02.api;

import iss.ft02.business.PodBean;
import iss.ft02.entity.Pod;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/items")
public class Items {
    @EJB
    private PodBean podBean; 
          
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        List<Pod> pods = podBean.findAllPod();
        
        JsonArrayBuilder builder = Json.createArrayBuilder();
        if (pods == null) {
            return Response.status(Response.Status.NOT_FOUND)
               .entity("Cannot find any pod")
               .build();
        } else {
            pods.stream()
                    .forEach(e -> { builder.add(Json.createObjectBuilder()
                                           .add("teamId", "8db3c30e")
                                           .add("podId", e.getPodId())
                                           .add("address", e.getDeliver().getAddress())
                                           .add("name", e.getDeliver().getName())
                                           .add("phone", e.getDeliver().getPhone())
                                           .build()
                                    );
                    });
            return  Response.status(Response.Status.OK)
                            .entity(builder.build())
                            .build();
        }
        
//        if (opt.isPresent()) {
//            People people = opt.get();
//            JsonObject object = Json.createObjectBuilder()
//                        .add("name", people.getName())
//                        .add("email", people.getEmail())
//                        .build();
//            return Response.status(Response.Status.OK)
//                       .entity(object)
//                       .build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND)
//                           .entity("Cannot find the user name with email " + email)
//                           .build();
//        }

    }
}
