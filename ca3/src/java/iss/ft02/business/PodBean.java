/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.business;

import iss.ft02.entity.Pod;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NguyenTrung
 */
@Stateless
public class PodBean {
    @PersistenceContext EntityManager em;
    
    public void add(Pod pod){
        em.persist(pod);

    }
    public void update(Pod pod){
        em.merge(pod);

    }
    
    public Optional<Pod> find(int podId){
        return Optional.ofNullable(em.find(Pod.class, podId));
    }
    
    public List<Pod> findAllPod() {
        return em.createQuery("select p from Pod p", Pod.class)
                 .getResultList();
    }
}
