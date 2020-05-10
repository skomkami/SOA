package app;

import app.dao.UsersDAO;
import app.model.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("register")
@SessionScoped
public class RegisterBean implements Serializable {

    @Inject
    UsersDAO usersDAO;

    private String login;
    private String pass1;
    private String pass2;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String register() {

        List<User> users = usersDAO.getAll();

        if ( users.stream().filter(u -> u.getLogin().equals(login)).findFirst().isPresent() ) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Register", new FacesMessage("User with given login already exists. Please choose another one"));
            return "";
        }

        if ( !this.pass1.equals(this.pass2) ) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Register", new FacesMessage("Passwords don't match."));
            return "";
        }

        User registeredUser = new User();
        registeredUser.setLogin(login);
        registeredUser.setPassword(pass1);
        registeredUser.setSubscribedThemeLists(new ArrayList<>());

        usersDAO.add(registeredUser);

        return "index.xhtml";
    }
}
