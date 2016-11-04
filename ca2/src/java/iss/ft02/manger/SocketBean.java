package iss.ft02.manger;

import iss.ft02.entity.Note;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;

@Stateless
public class SocketBean {
    @EJB
    NotesBean noteBean;
    
    @Inject
    Event<JsonObject> newNoteEvent;
    
    public void findAllNote() {
        List<Note> list = noteBean.findAll();
        list.stream()
                .forEach(e -> sendMessage(e));
    }
    
    public void sendMessage(final Note note){
        System.out.println(">>> New Note added: " + toJson(note).toString());
        JsonObject json = toJson(note);
        newNoteEvent.fire(json);
    }
    
    public JsonObject toJson(final Note note) {
        return Json.createObjectBuilder()
                    .add("title", note.getTitle())
                    .add("category", note.getCategory())
                    .add("datetime", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(note.getDatetime()))
                    .add("content", note.getContent())
                    .add("user", note.getUser().getUserid())
                    .build();
    }
    
}
