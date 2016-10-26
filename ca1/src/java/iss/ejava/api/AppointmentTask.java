/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ejava.api;

import iss.ejava.manager.AppointmentManager;
import iss.ejava.model.Appointment;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author NguyenTrung
 */
public class AppointmentTask implements Runnable{
    private AsyncResponse response;
    private String email;
    private AppointmentManager am;
    public AppointmentTask(AsyncResponse resp, String email, AppointmentManager am) {
        this.response = resp;
        this.email = email;
        this.am = am;
    }
    
    @Override
    public void run() {
        System.out.println(email);
        List<Appointment> appointments = am.findByPeople(email);
        System.out.println(appointments);
        JsonArrayBuilder array = Json.createArrayBuilder();
        appointments.stream().forEach(app -> {
            System.out.println(app.toString());
            JsonObjectBuilder appObj = Json.createObjectBuilder();
            array.add(appObj.add("personId", app.getPeople().getPersonId())
                    .add("dateTime", app.getApptDate().getTime())
                    .add("description", app.getDescription())
                    .add("appointmentId", app.getApppointmentId()).build());
        });
        response.resume(Response.ok(array.build()).build());
    }
    
    
}
