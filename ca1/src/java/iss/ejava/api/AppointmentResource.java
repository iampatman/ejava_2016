package iss.ejava.api;

import iss.ejava.manager.AppointmentManager;
import iss.ejava.manager.PeopleManager;
import iss.ejava.model.Appointment;
import iss.ejava.model.People;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @Resource(lookup = "concurrent/myThreadpool")
    ManagedExecutorService service;
    
    @EJB
    private AppointmentManager am;
    @EJB
    private PeopleManager pm;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{email}")
    public void get(@PathParam("email") String email, @Suspended AsyncResponse resp) {
        AppointmentTask task = new AppointmentTask(resp, email, am);
        service.execute(task);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(MultivaluedMap<String, String> form) {
        Appointment app = new Appointment();
        app.setApptDate(new Date(Long.valueOf(form.getFirst("date"))));
        app.setDescription(form.getFirst("description"));
        Optional<People> opt = pm.findByEmail(form.getFirst("email"));
        if (!opt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        app.setPeople(opt.get());
        am.add(app);
        return Response.ok().build();
    }
}
