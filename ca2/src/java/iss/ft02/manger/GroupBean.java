/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.manger;

import iss.ft02.entity.Group;
import iss.ft02.entity.GroupPK;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NguyenTrung
 */
@Stateless
public class GroupBean {
    @PersistenceContext EntityManager em;
    
    public void add(GroupPK pk){
        Group g = new Group();
        g.setGroupPK(pk);
        em.persist(g);
    }
}
