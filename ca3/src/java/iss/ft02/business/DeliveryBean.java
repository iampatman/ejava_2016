package iss.ft02.business;

import iss.ft02.entity.Delivery;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DeliveryBean {
    @PersistenceContext EntityManager em;
    
    public void add(Delivery delivery){
        em.persist(delivery);
    }
}
