package sample.login;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import sample.log.Logged;

@Named("user")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 2911662111731625006L;
    private String name;
    private String password;

    @Logged
    public String doLogin() {
    	// IDとパスワードをDBに照会し認証を行う。
        // 現時点では認証は行なっていない。
        if(name.equals("") || password.equals("")) {
            return "operation_error.xhtml";
        }
    	return "search.xhtml";
    }

    @Logged
    public String doLogout() {
        //invalidate http session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
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
