package app.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends IdentifiableVersionedEntity {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "theme_list_subscribe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_list_id"))
    @Column(name = "subscribed_theme_lists")
    List<ThemeList> subscribedThemeLists;

    public List<ThemeList> getSubscribedThemeLists() {
        return subscribedThemeLists;
    }

    public void setSubscribedThemeLists(List<ThemeList> subscribedThemeLists) {
        this.subscribedThemeLists = subscribedThemeLists;
    }

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
}
