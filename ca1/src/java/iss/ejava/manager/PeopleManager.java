package iss.ejava.manager;

import iss.ejava.model.People;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PeopleManager {
    
    @PersistenceContext EntityManager em;
    
    public void add(final People people){
        em.persist(people);
    }
    
    public Optional<People> findByEmail(final String email){
//        TypedQuery<People> query = em.createNamedQuery("People.findByEmail", People.class);
        return em.createQuery("select p from People p where p.email = :email", People.class)
                 .setParameter("email", email)
                 .getResultList()
                 .stream().findFirst();
    }
}
