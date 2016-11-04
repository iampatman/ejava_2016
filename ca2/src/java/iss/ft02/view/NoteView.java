/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.view;

import iss.ft02.entity.Note;
import iss.ft02.entity.User;
import iss.ft02.manger.NotesBean;
import iss.ft02.manger.RegisterBean;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NguyenTrung
 */
@SessionScoped
@Named
public class NoteView implements Serializable {

    @EJB
    private RegisterBean rb;
    
    @EJB
    private NotesBean nb;
    
    

    private List<Note> notes = new ArrayList<>();
    private String title;
    private String content;
    private List<String> categories = Arrays.asList("Social", "ForSale","Jobs","Tuitions");
    private String category;
    private User user;
    
    
    @PostConstruct
    public void NoteView() {
        String name = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        System.out.println(" >>>>>>>>>> " + name);
        user = rb.findUserById(name);
        System.out.println(user.toString());
        notes = nb.findAll();
    }

    public void postNote() { 
        Note note = new Note();
        note.setTitle(getTitle());
        note.setUser(user);
        note.setCategory(getCategory());
        note.setContent(getContent());
        java.util.Date date= new java.util.Date();
        note.setDatetime(new Timestamp(date.getTime()));
        nb.add(note);
        notes = nb.findAll();
        title = "";
        category = "";
        content = "";
    }

    /**
     * @return the nb
     */
    public NotesBean getNb() {
        return nb;
    }

    /**
     * @param nb the nb to set
     */
    public void setNb(NotesBean nb) {
        this.nb = nb;
    }

    /**
     * @return the rb
     */
    public RegisterBean getRb() {
        return rb;
    }

    /**
     * @param rb the rb to set
     */
    public void setRb(RegisterBean rb) {
        this.rb = rb;
    }

    /**
     * @return the notes
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
