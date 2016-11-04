/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.manger;

import iss.ft02.entity.Note;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NguyenTrung
 */
@Stateless
public class NotesBean {
    @PersistenceContext EntityManager em;
    
    @EJB SocketBean socket;
    
    public void add(Note note){
        em.persist(note);
        socket.sendMessage(note);
    }
    

    public List<Note> findAll(){
        List<Note> list = em.createNamedQuery("Note.findAllNotes",Note.class).getResultList();
        return list;
    }
    
    public List<Note> findAllByUser(String user){
        List<Note> list = em.createNamedQuery("Note.findAllNotesByUser",Note.class)
                .setParameter("userid", user)
                .getResultList();
        return list;
    }
}
