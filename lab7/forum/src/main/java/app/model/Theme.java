package app.model;

import javax.persistence.*;

@Entity
@Table(name = "themes")
public class Theme extends IdentifiableVersionedEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "theme_list_id")
    private ThemeList themeList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThemeList getThemeList() {
        return themeList;
    }

    public void setThemeList(ThemeList themeList) {
        this.themeList = themeList;
    }
}
