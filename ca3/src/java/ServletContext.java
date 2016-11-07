
import iss.ft02.business.Forwarding;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenTrung
 */
@WebListener
public class ServletContext implements ServletContextListener{
    @Resource private ManagedExecutorService executor;
    private Forwarding fwd = new Forwarding();
    @Override 
    public void contextInitialized(ServletContextEvent sce) {        
        System.out.println("contextInitialized");
        executor.execute(fwd);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        executor.shutdownNow();
        System.out.println("contextDestroyed");
    }
}
