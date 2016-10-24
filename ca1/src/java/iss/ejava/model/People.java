package iss.ejava.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "People.findByEmail", 
            query = "select p from People p where p.email = :email")
})
@Entity
@Table(name = "people")
public class People {

    @Id
    @Column(name = "pid")
    private String personId;

    @Basic
    private String name;
    private String email;
    
//    @OneToMany(mappedBy = "people")
//    private List<Appointment> appointments;

    public People() {
        this.personId = UUID.randomUUID().toString().substring(28);
    }

    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
//    public List<Appointment> getAppointments() {
//        return appointments;
//    }
//    public void setAppointments(List<Appointment> appointments) {
//        this.appointments = appointments;
//    }

    @Override
    public int hashCode() {
        return personId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof People)) {
            return false;
        }
        People other = (People) obj;
        return getPersonId().equals(other.getPersonId());
    }
}
