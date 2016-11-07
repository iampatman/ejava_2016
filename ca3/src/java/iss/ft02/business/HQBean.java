/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.business;

import iss.ft02.entity.Pod;
import java.sql.Timestamp;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author NguyenTrung
 */
@Stateless
public class HQBean {
    private Client client = ClientBuilder.newClient();
    @Resource TimerService timerService;
    public void foward(Pod pod){
        String url = "10.10.0.50:8080/epod";
        WebTarget target = client.target(url);
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>(); 
        form.add("teamId", "hellohihi");
        form.add("podId", String.valueOf(pod.getPodId()));
        form.add("callback", url);
        form.add("note",pod.getNote());
//        Invocation.Builder request = target.request(MediaType.APPLICATION_FORM_URLENCODED)
//                .post(Entity.form(form), Response.class);
    }
}
