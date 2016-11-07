package iss.ft02.business;

import iss.ft02.entity.Delivery;
import iss.ft02.entity.Pod;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerBean {
    @EJB
    private DeliveryBean deliveryBean;
    
    @EJB
    private PodBean podBean;
    
    public void add(Delivery delivery, Pod pod){
        deliveryBean.add(delivery);
        podBean.add(pod);
    }
}
