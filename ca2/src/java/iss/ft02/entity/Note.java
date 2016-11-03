/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author NguyenTrung
 */
@Entity
@Table(name = "notes")
@NamedQueries({
    @NamedQuery(name = "Note.findAllNotes", query = "Select n from Note n")
    ,
@NamedQuery(name = "Note.findAllNotesByUser", query = "Select n from Note n where n.user.userid = :userid")})
public class Note implements Serializable {

    @Id
    private int id;

    private String title;
    private Timestamp datetime;
    private String content;
    private String category;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "userid")
    private User user;

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("title", title)
                .add("content", "content")
                .add("category", category)
                .add("userid", user.getUserid()).build();

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the datetime
     */
    public Timestamp getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
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
