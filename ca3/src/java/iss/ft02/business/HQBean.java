/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.business;

import iss.ft02.entity.Pod;
import iss.ft02.servlet.Callback;
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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import sun.net.www.http.HttpClient;

/**
 *
 * @author NguyenTrung
 */
@Stateless
public class HQBean {

    private Client client = ClientBuilder.newClient();
//<<<<<<< HEAD
    @Resource TimerService timerService;
    public void foward(Pod pod){
        String url = "10.10.0.50:8080/epod/upload";
//        UriBuilder builder = UriBuilder.fromResource(Callback.class);
//        String callback = builder.scheme("http").host("{server}").port("{port}");
        String callback = "http://10.10.24.154:8080/epod/callback";
        System.out.println(">>> Start forwarding to HQ");

        WebTarget target = client.target(url);
       
        MultivaluedMap<String, Object> form = new MultivaluedHashMap<>(); 
        form.add("teamId", "8db3c30e");
        form.add("podId", String.valueOf(pod.getPodId()));
        form.add("callback", callback);
        form.add("note",pod.getNote());
        form.add("image", pod.getImage());
        
//        Invocation.Builder request = target.request(MediaType.APPLICATION_FORM_URLENCODED)
//                .post(Entity.form(form), Response.class);

        Response resp = client.target(url)
                              .request(MediaType.MULTIPART_FORM_DATA)
                              .post(Entity.entity(form, MediaType.MULTIPART_FORM_DATA));
//=======
//    @Resource
//    TimerService timerService;
//    @EJB PodBean podBean;
//    
//    public void createTimer(Pod pod){
//        System.out.println("Timer created");
//        timerService.createTimer(2000, 10000, pod.getPodId());
//        System.out.println("Test timer + " + System.currentTimeMillis() );
//    }
//    
//    @Timeout
//    public void sendFoward(Timer timer) {
//        System.out.println("Timer ticks");        
//        int podId = (int)timer.getInfo();
//        Optional<Pod> optional = podBean.find(podId);
//        Pod pod = null;
//        if (optional.isPresent()){
//            pod = optional.get();
//        }
//        String url = "10.10.0.50:8080/epod";
//        String callback = "http://10.10.24.173:8080/epod/callback";
//        WebTarget target = client.target(url);
//        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
//        form.add("teamId", "hellohihi");
//        form.add("podId", String.valueOf(pod.getPodId()));
//        form.add("callback", callback);
//        form.add("note", pod.getNote());
//        
//        target.request(MediaType.APPLICATION_FORM_URLENCODED)
//                .post(Entity.form(form));
//    }
//
//    public void cancelTimer(int podId) {
//        System.out.println("Timer cancel");
//        for (Timer t : timerService.getTimers()) {
//            if ((int) t.getInfo()== podId){
//                t.cancel();
//                return;
//            } 
//        }
//>>>>>>> 17468e2afaf5970c1eb8b54669a158d7c4e9776f
    }
}

