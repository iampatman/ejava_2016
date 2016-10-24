package iss.ejava.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(
        query = "select a from Appointment a where a.people.email = :email",
        name = "Appointment.findByPeople")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appt_id")
    private int apppointmentId;

    private String description;

    @Column(name = "appt_date")
    private Date apptDate;

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private People people;

    public int getApppointmentId() {
        return apppointmentId;
    }

    public void setApppointmentId(int apppointmentId) {
        this.apppointmentId = apppointmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

}
