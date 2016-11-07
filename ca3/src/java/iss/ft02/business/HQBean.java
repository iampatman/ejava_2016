/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.business;

import iss.ft02.entity.Pod;
import java.sql.Timestamp;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
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
import sun.net.www.http.HttpClient;

/**
 *
 * @author NguyenTrung
 */
@Stateless
public class HQBean {

    private Client client = ClientBuilder.newClient();
    @Resource
    TimerService timerService;
    @EJB PodBean podBean;
    
    public void createTimer(Pod pod){
        System.out.println("Timer created");
        timerService.createTimer(2000, 10000, pod.getPodId());
        System.out.println("Test timer + " + System.currentTimeMillis() );
    }
    
    @Timeout
    public void sendFoward(Timer timer) {
        System.out.println("Timer ticks");        
        int podId = (int)timer.getInfo();
        Optional<Pod> optional = podBean.find(podId);
        Pod pod = null;
        if (optional.isPresent()){
            pod = optional.get();
        }
        String url = "10.10.0.50:8080/epod";
        String callback = "http://10.10.24.173:8080/epod/callback";
        WebTarget target = client.target(url);
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add("teamId", "hellohihi");
        form.add("podId", String.valueOf(pod.getPodId()));
        form.add("callback", callback);
        form.add("note", pod.getNote());
        
        target.request(MediaType.APPLICATION_FORM_URLENCODED)
                .post(Entity.form(form));
    }

    public void cancelTimer(int podId) {
        System.out.println("Timer cancel");
        for (Timer t : timerService.getTimers()) {
            if ((int) t.getInfo()== podId){
                t.cancel();
                return;
            } 
        }
    }
}

