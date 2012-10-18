package sample.login;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("user")
@SessionScoped
public class LoginBean implements Serializable {

    private static long serialVersionUID = 6455930392684901518L;

    private String name;
    private String password;

    // TODO Logs
    public String doLogin() {
    	// ç°ÇÕîFèÿÇÕçsÇÌÇ∏ëfí ÇË
    	return "search.xhtml";
    }

    // TODO Logs
    public String doLogout() {
        //invalidate user session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        name = "";
        password = "";
        session.invalidate();
        return "login.xhtml";
    }

    //--- getter/setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
