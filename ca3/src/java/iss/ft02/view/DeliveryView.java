package iss.ft02.view;

import iss.ft02.business.DeliveryBean;
import iss.ft02.business.PodBean;
import iss.ft02.entity.Delivery;
import iss.ft02.entity.Pod;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.UserTransaction;

@RequestScoped
@Named
public class DeliveryView {
    @Resource UserTransaction ut;

    @EJB
    private PodBean podBean;
    
    @EJB
    private DeliveryBean deliveryBean;
    
    private String name;
    private String address;
    private String phone;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void addDelivery() {
        Delivery delivery = new Delivery();
        delivery.setName(name);
        delivery.setAddress(address);
        delivery.setPhone(phone);
        deliveryBean.add(delivery);

        Pod pod = new Pod();
        pod.setDeliver(delivery);
        podBean.add(pod);      
        
        name="";
        address="";
        phone="";
    }
}
