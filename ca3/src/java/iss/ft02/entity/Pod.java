/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author NguyenTrung
 */
@Entity
@Table(name = "pod")
public class Pod implements Serializable{
    @Id
    @Column(name = "pod_id")
    private int podId;
    
    @OneToOne
    @JoinColumn(columnDefinition = "pkg_id", referencedColumnName = "pkg_id")
    private Delivery deliver;
    
    private String note;
    private byte[] image;
    @Column(name="delivery_date")
    private Timestamp deliveryDate;
    @Column(name="ack_id")
    private String ackId;

    /**
     * @return the podId
     */
    public int getPodId() {
        return podId;
    }

    /**
     * @param podId the podId to set
     */
    public void setPodId(int podId) {
        this.podId = podId;
    }

    /**
     * @return the deliver
     */
    public Delivery getDeliver() {
        return deliver;
    }

    /**
     * @param deliver the deliver to set
     */
    public void setDeliver(Delivery deliver) {
        this.deliver = deliver;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * @return the deliveryDate
     */
    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return the ackId
     */
    public String getAckId() {
        return ackId;
    }

    /**
     * @param ackId the ackId to set
     */
    public void setAckId(String ackId) {
        this.ackId = ackId;
    }
    
    
    
}
