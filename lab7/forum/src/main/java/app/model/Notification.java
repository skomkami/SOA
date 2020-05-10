package app.model;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification extends IdentifiableVersionedEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "theme_list", nullable = false)
    private String themeList;

    @Column(name = "theme", nullable = false)
    private String theme;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getThemeList() {
        return themeList;
    }

    public void setThemeList(String themeList) {
        this.themeList = themeList;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Notification(User user, String themeList, String theme) {
        this.user = user;
        this.themeList = themeList;
        this.theme = theme;
    }

    public Notification() {
    }
}
