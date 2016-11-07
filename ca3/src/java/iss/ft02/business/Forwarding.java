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

    @EJB
    PodBean podBean;
    @EJB
    HQBean hqBean;

    @Override
    public void run() {
        while (true) {
            System.out.println("New round check" + System.currentTimeMillis()/1000);
            List<Pod> pods = podBean.findAllPod();
            pods.stream().
                    filter((t) -> {
                        System.out.println("result:"+t.getNote() != null && t.getAckId() == null);
                        return t.getNote() != null && t.getAckId() == null; //To change body of generated lambdas, choose Tools | Templates.
                    }).forEach(pod -> {
//                hqBean.sendFoward(pod);
            }
            );
            try {
                Thread.sleep(10000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
