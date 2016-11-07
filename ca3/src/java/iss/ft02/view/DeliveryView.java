package iss.ft02.view;

import iss.ft02.business.ManagerBean;
import iss.ft02.entity.Delivery;
import iss.ft02.entity.Pod;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class DeliveryView {
    @EJB
    private ManagerBean managerBean;
    
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
        
        Pod pod = new Pod();
        pod.setDeliver(delivery);
        managerBean.add(delivery, pod);
        
        name="";
        address="";
        phone="";
    }
}
