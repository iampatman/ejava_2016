
import iss.ft02.business.Forwarding;
import iss.ft02.business.HQBean;
import iss.ft02.business.PodBean;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
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
    
    @EJB
    PodBean podBean;
    @EJB
    HQBean hqBean;
    
    @Resource(lookup = "concurrent/myThreadPool") private ManagedScheduledExecutorService executor;
    private Forwarding fwd = null; 
    @Override 
    public void contextInitialized(ServletContextEvent sce) {  
        fwd = new Forwarding(podBean, hqBean);
        System.out.println("contextInitialized");
        executor.scheduleAtFixedRate(fwd,0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        executor.shutdownNow();
        System.out.println("contextDestroyed");
    }
}
