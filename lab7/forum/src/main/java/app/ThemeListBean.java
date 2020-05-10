package app;


import app.dao.ThemesDAO;
import app.model.Theme;
import app.model.ThemeList;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("themeList")
@SessionScoped
public class ThemeListBean implements Serializable {

    @Inject
    ThemesDAO themesDAO;

    private ThemeList themeList;

    public ThemeList getThemeList() {
        return themeList;
    }

    public void setThemeList(ThemeList themeList) {
        this.themeList = themeList;
    }

    public String goTo(ThemeList tl) {
        themeList = tl;
        return "theme-list.xhtml";
    }

    public void createTheme() {
        if ( themesDAO.getAll().stream().filter( t -> t.getName().equals(createThemeName)).findFirst().isPresent() ) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("CreateTheme", new FacesMessage("Theme with given name already exists"));
            return;
        }

        Theme newTheme = new Theme();
        newTheme.setName(createThemeName);
        newTheme.setThemeList(this.themeList);
        themesDAO.add(newTheme);
        createThemeName = null;
    }

    private String createThemeName;

    public String getCreateThemeName() {
        return createThemeName;
    }

    public void setCreateThemeName(String createThemeName) {
        this.createThemeName = createThemeName;
    }

    public List<Theme> getThemes() {
        return themesDAO.getThemesBelongingToList(this.getThemeList());
    }

    public String redirectIfThemeListEmpty() {
        if ( this.themeList == null )
            return "main.xhtml";
        else
            return "";
    }
}
