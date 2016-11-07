/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.business;

import iss.ft02.entity.Pod;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author NguyenTrung
 */
public class Forwarding implements Runnable {

    PodBean podBean;
    HQBean hqBean;
    
    public Forwarding(PodBean podBean, HQBean hqBean){
        this.podBean = podBean;
        this.hqBean = hqBean;
    }
    @Override
    public void run() {
        System.out.println("Thread started");
        System.out.println("New round check" + System.currentTimeMillis() / 1000);
        List<Pod> pods = podBean.findAllPod();
        pods.stream().
                filter((t) -> {
                    System.out.println("result:" + t.getDeliver().getName() + " " + t.getNote() != null && t.getAckId() == null);
                    return t.getAckId() == null; //To change body of generated lambdas, choose Tools | Templates.
                }).forEach(pod -> {
            try {
                hqBean.foward(pod);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        );
        System.out.println("Thread stop");

    }

}
