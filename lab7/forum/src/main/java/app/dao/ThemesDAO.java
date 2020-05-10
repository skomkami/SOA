package app.dao;

import app.model.Theme;
import app.model.ThemeList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.List;

@Named("ThemesDAO")
@SessionScoped
public class ThemesDAO extends GenericEntityDAO<Theme> {

    public List<Theme> getThemesBelongingToList(ThemeList themeList) {
        String jpql = "SELECT t FROM Theme t WHERE t.themeList = :tl";
        TypedQuery<Theme> query = em.createQuery(jpql, Theme.class);
        query.setParameter("tl", themeList);

        return query.getResultList();
    }
}
