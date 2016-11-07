package iss.ft02.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable{
    @Id
    private int pkg_id;
    
    @Basic
    private String name;
    private String address;
    private String phone;
    private Timestamp create_date;

    
//    @OneToOne(mappedBy = "deliver")
//    private Pod pod;
    

    public int getPkg_id() {
        return pkg_id;
    }
    public void setPkg_id(int pkg_id) {
        this.pkg_id = pkg_id;
    }

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

    public Timestamp getCreate_date() {
        return create_date;
    }
    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }
    
    
}
