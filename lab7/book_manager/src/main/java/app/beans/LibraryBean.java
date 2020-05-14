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
import java.util.Optional;

@Named("library")
@SessionScoped
public class LibraryBean implements Serializable {

    @Inject
    ReadersDAO readersDAO;
    
    private Reader reader;

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logIn() {
        List<Reader> readers = readersDAO.getAll();

        Optional<Reader> findReader = readers.stream().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password)).findFirst();

        if ( !findReader.isPresent() ) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Login", new FacesMessage("Wrong credentials"));
            return "";
        } else {
            this.reader = findReader.get();
        }
         return "loans.xhtml";
    }

    public String logOut() {
        this.reader = null;
        return "index.xhtml";
    }

    public String redirect() {
        if ( this.reader == null )
            return "index.xhtml";
        else
            return "loans.xhtml";
    }
}
