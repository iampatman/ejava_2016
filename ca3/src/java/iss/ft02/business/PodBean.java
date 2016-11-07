/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.business;

import iss.ft02.entity.Pod;
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
    
}
