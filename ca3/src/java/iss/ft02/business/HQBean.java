/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.business;

import iss.ft02.entity.Pod;
//<<<<<<< HEAD
import iss.ft02.servlet.Callback;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
//=======
//>>>>>>> 2f91d4b0bb777b4db0ab3f4977689be770106f30
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
//<<<<<<< HEAD
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
import javax.ejb.TimerService;
import javax.imageio.ImageIO;
//=======
//>>>>>>> 2f91d4b0bb777b4db0ab3f4977689be770106f30
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
//<<<<<<< HEAD
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

/**
 *
 * @author NguyenTrung
 */
@Stateless
public class HQBean {

    private Client client = ClientBuilder.newClient();
//<<<<<<< HEAD
//<<<<<<< HEAD
//    @Resource TimerService timerService;
    public void foward(Pod pod) throws FileNotFoundException, IOException{
        String url = "http://10.10.0.50:8080/epod/upload";

        String callback = "http://10.10.24.173:8080/epod/callback";
        System.out.println(">>> Start forwarding to HQ");
        Client client = ClientBuilder.newBuilder()
                                    .register(MultiPartFeature.class)
                                    .build();
        
        MultiPart multiPart = new MultiPart();
        
        File imageFile = new File("image.jpg");
        FileOutputStream fos = new FileOutputStream(imageFile);
        fos.write(pod.getImage());
        fos.flush();
        fos.close();
        
        FileDataBodyPart imgPart = new FileDataBodyPart("image",
                                            imageFile,
                                            MediaType.APPLICATION_OCTET_STREAM_TYPE);
        imgPart.setContentDisposition(FormDataContentDisposition.name("image")
                .fileName(imageFile.getName())
                .build());
        System.out.println(">>> Image file" + imageFile.getAbsolutePath());
        MultiPart formPart = new FormDataMultiPart()
                                                    .field("teamId", "8db3c30e", MediaType.TEXT_PLAIN_TYPE)
                                                    .field("podId", String.valueOf(pod.getPodId()), MediaType.TEXT_PLAIN_TYPE)
                                                    .field("callback", callback, MediaType.TEXT_PLAIN_TYPE)
                                                    .field("note", pod.getNote(), MediaType.TEXT_PLAIN_TYPE)
                                                    .bodyPart(imgPart);
//                                                    .bodyPart(new BodyPart(pod.getImage(), MediaType.APPLICATION_OCTET_STREAM_TYPE)
//                                                            .setContentDisposition(FormDataContentDisposition.name("image")
//                                                                    .fileName("image.png")
//                                                                    .build()))
        formPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        multiPart.bodyPart(formPart);
        
        System.out.println(">>> BodyPart form: " + formPart.toString());

        WebTarget target = client.target(url);
        
        Invocation.Builder inv = target.request();
        
        Response resp = inv.post(Entity.entity(formPart, formPart.getMediaType()));
        
        System.out.println(">> call Resp" + resp.getStatus());
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
//=======
//    @EJB PodBean podBean;
//    
//    public void sendFoward(Pod pod) {
//        System.out.println("Send foward is called");
//        System.out.println("Timer ticks");        
//
//        Optional<Pod> optional = podBean.find(pod.getPodId());
//        if (optional.isPresent()){
//            pod = optional.get();
//        }
//        String url = "10.10.0.50:8080/epod";
//        String callback = "http://10.10.24.173:8080/epod/callback";
//        WebTarget target = client.target(url);
//        MultiPart part = new MultiPart();
//        FileDataBodyPart file = new FileDataBodyPart();
//        
//        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
//        
//        form.add("teamId", "hellohihi");
//        form.add("podId", String.valueOf(pod.getPodId()));
//        form.add("callback", callback);
//        form.add("note", pod.getNote());
//        form.add("image", pod.getImage().toString());
//                target.request(MediaType.MULTIPART_FORM_DATA_TYPE)
//                .post(Entity.form(form));
//>>>>>>> 2f91d4b0bb777b4db0ab3f4977689be770106f30
    }

}

