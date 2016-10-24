package iss.ejava.manager;

import iss.ejava.model.Appointment;
import iss.ejava.model.People;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AppointmentManager {
    
    @PersistenceContext EntityManager em;
    
    public List<Appointment> findByPeople(People people){
        List<Appointment> result = em.createQuery("select a from People p join Appoitment a where a.people.pid = :personId ", Appointment.class)
                 .setParameter("personId", people.getPersonId())
                 .getResultList();
        result.stream().forEach(e -> {System.out.println(e.getDescription());});
        return result;
    }
}
