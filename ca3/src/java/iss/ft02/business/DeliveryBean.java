package iss.ft02.business;

import iss.ft02.entity.Delivery;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DeliveryBean {
    @PersistenceContext EntityManager em;
    
    public void add(Delivery delivery){
        em.persist(delivery);
    }    
    
    public Delivery findByName(String name){
        Delivery result = em.createQuery("Select d from Delivery d where :name = d.name", Delivery.class)
                .setParameter("name", name)
                .getSingleResult();
        return result;
    }
}
