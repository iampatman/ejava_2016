/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author NguyenTrung
 */
@Entity
@Table(name = "groups")
public class Group {
    
    @EmbeddedId private GroupPK groupPK;

    /**
     * @return the groupPK
     */
    public GroupPK getGroupPK() {
        return groupPK;
    }

    /**
     * @param groupPK the groupPK to set
     */
    public void setGroupPK(GroupPK groupPK) {
        this.groupPK = groupPK;
    }
    
    
}
