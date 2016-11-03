package iss.ft02.manger;

import iss.ft02.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RegisterBean {
    
    @PersistenceContext 
    EntityManager em;
    
    public void register(User user){
        em.persist(user);
    }
    
    public User findUserById(String id){
        System.out.println(" >>>>>>> id = " + id);
        User user = em.find(User.class, id);
        return user;
    }
}
