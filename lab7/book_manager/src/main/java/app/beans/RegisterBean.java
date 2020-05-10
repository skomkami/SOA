package app.beans;

import app.dao.ReadersDAO;
import app.model.Reader;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("register")
@SessionScoped
public class RegisterBean implements Serializable {

    @Inject
    ReadersDAO readersDAO;

    private String login;
    private String firstName;
    private String lastName;
    private String pass1;
    private String pass2;
    private Boolean notifyOnBookCreation;

    public Boolean getNotifyOnBookCreation() {
        return notifyOnBookCreation;
    }

    public void setNotifyOnBookCreation(Boolean notifyOnBookCreation) {
        this.notifyOnBookCreation = notifyOnBookCreation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

        List<Reader> readers = readersDAO.getAll();

        if ( readers.stream().filter(u -> u.getLogin().equals(login)).findFirst().isPresent() ) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Register", new FacesMessage("Reader with given login already exists. Please choose another one"));
            return "";
        }

        if ( !this.pass1.equals(this.pass2) ) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Register", new FacesMessage("Passwords don't match."));
            return "";
        }

        Reader registeredReader = new Reader();
        registeredReader.setLogin(login);
        registeredReader.setPassword(pass1);
        registeredReader.setFirstName(firstName);
        registeredReader.setLastName(lastName);
        registeredReader.setNotifyOnBookCreation(notifyOnBookCreation);

        readersDAO.add(registeredReader);

        return "index.xhtml";
    }
}
