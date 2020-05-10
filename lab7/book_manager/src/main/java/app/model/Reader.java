package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "readers")
public class Reader extends IdentifiableVersionedEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
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

    @Column(name = "notify_on_book_creation", nullable = false)
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

    public Reader() {
        super();
    }
}
