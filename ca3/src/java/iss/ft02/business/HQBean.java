/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.business;

import iss.ft02.entity.Pod;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;


/**
 *
 * @author NguyenTrung
 */
@Stateless
public class HQBean {

    private Client client = ClientBuilder.newClient();
    @EJB PodBean podBean;
    
    public void sendFoward(Pod pod) {
        System.out.println("Send foward is called");
        System.out.println("Timer ticks");        

        Optional<Pod> optional = podBean.find(pod.getPodId());
        if (optional.isPresent()){
            pod = optional.get();
        }
        String url = "10.10.0.50:8080/epod";
        String callback = "http://10.10.24.173:8080/epod/callback";
        WebTarget target = client.target(url);
        MultiPart part = new MultiPart();
        FileDataBodyPart file = new FileDataBodyPart();
        
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        
        form.add("teamId", "hellohihi");
        form.add("podId", String.valueOf(pod.getPodId()));
        form.add("callback", callback);
        form.add("note", pod.getNote());
        form.add("image", pod.getImage().toString());
                target.request(MediaType.MULTIPART_FORM_DATA_TYPE)
                .post(Entity.form(form));
    }

}

