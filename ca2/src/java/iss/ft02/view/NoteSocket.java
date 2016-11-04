package iss.ft02.view;

import iss.ft02.manger.SocketBean;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@RequestScoped
@ServerEndpoint("/note")
public class NoteSocket {

    @EJB
    private SocketBean socketBean;

    @Resource(lookup = "concurrent/myThreadPool")
    private ManagedScheduledExecutorService executor;

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void open(Session session) {
        System.out.println(">>> new session: " + session.getId());
        sessions.add(session);
    }

    @OnClose
    public void close(Session session) {
        System.out.println(">>> Close session: " + session.getId());
        sessions.remove(session);
    }

    @OnMessage
    public void message(final Session session, final String message) {
        System.out.println(">>> message: " + message);
        socketBean.findAllNote();
    }

    public void broadcastMessage(@Observes JsonObject event) {
        System.out.println(">>> Event received: " + event.toString());

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>> In thread");
                synchronized (sessions) {
                    for (Session s : sessions) {
                        try {
                            s.getBasicRemote().sendText(event.toString());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            try {
                                s.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        System.out.println(">>> Exit event ");
    }
}
