package app.dao;

import app.model.ThemeList;
import app.model.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.List;

@Named("UsersDAO")
@SessionScoped
public class UsersDAO extends GenericEntityDAO<User> {

    public List<User> getUsersSubscribingThemeList(ThemeList tl) {
        String jpql = "SELECT u FROM User u WHERE :tl MEMBER OF u.subscribedThemeLists";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("tl", tl);

        return query.getResultList();
    }
}
