package iss.ejava.manager;

import iss.ejava.model.Appointment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AppointmentManager {

    @PersistenceContext
    EntityManager em;

    public List<Appointment> findByPeople(String email) {
        List<Appointment> result = em.createNamedQuery("Appointment.findByPeople", Appointment.class)
                .setParameter("email", email).getResultList();
        result.stream().forEach(e -> {
            System.out.println(e.toString());
        });
        return result;
    }

    public void add(Appointment app) {
        em.persist(app);

    }
}
