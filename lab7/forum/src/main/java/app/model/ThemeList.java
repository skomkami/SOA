package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "theme_lists")
public class ThemeList extends IdentifiableVersionedEntity {

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "subscribedThemeLists")
    @Column(name = "subscribed_theme_lists")
    List<User> subscribedThemeLists;
}
