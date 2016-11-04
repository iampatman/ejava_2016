package iss.ft02.view;

import iss.ft02.entity.GroupPK;
import iss.ft02.entity.User;
import iss.ft02.manger.GroupBean;
import iss.ft02.manger.RegisterBean;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class RegisterView {
    private String userid;
    private String password;
    
    @EJB
    RegisterBean registerBean;
    
    @EJB 
    private GroupBean groupBean;

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String register() {
        
        if (registerBean.findUserById(userid) != null){
            FacesMessage msg = new FacesMessage("Username exists");
            FacesContext.getCurrentInstance().addMessage("", msg);
            return "";
        }
        
        String pwd = "";
         try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            pwd = bigInt.toString(16);
            System.out.println(">>> Encrypted password " + pwd);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            System.out.println(">>> Exception in digest password" + ex.getMessage());
        }
        //Add new user
        User user = new User();
        user.setUserid(userid);
        user.setPassword(pwd);
        registerBean.register(user);
        //Add new user with group to Groups table
        GroupPK pk = new GroupPK();
        pk.setGroupid("user");
        pk.setUserid(userid);
        groupBean.add(pk);
        userid = "";
        password = "";
        return "login";
    } 
}
