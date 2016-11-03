/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.manger;

import iss.ft02.entity.Note;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author NguyenTrung
 */
@RequestScoped
@ServerEndpoint("/display")
public class NoteDisplaySocket {

    private static Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void open(Session session) {
        System.out.println(">>> new session: " + session.getId());
        sessions.add(session);

    }

    @OnMessage
    public void message(final Session session, final String msg) {
        System.out.println(">>> message: " + msg);
        System.out.println(">>> exiting message");
    }

    public static void broadcastNotes(List<Note> notes) {
        synchronized (sessions) {
            System.out.println("Sending to clients in thread: " + sessions.size());
            if (sessions.isEmpty()) {
                return;
            }
            JsonArrayBuilder builder = Json.createArrayBuilder();
            notes.stream()
                    .forEach((note) -> {
                        builder.add(note.toJson());
                    });
            JsonArray array = builder.build();
            sessions.stream().
                    forEach(session -> {
                        try {
                            session.getBasicRemote().sendText(array.toString());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
        }
    }
}
