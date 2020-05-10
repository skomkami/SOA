package app.dao;

import app.model.Notification;
import app.model.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Named("NotificationsDAO")
@SessionScoped
public class NotificationsDAO extends GenericEntityDAO<Notification> {

    public List<Notification> getNotificationsForUser(User user) {
        String jpql = "SELECT n FROM Notification n WHERE n.user = :user";
        TypedQuery<Notification> query = em.createQuery(jpql, Notification.class);
        query.setParameter("user", user);

        return query.getResultList();
    }

    public void deleteNotificationsForUser(User user) {
        String jpql = "DELETE FROM Notification n WHERE n.user = :user";
        Query query = em.createQuery(jpql);
        query.setParameter("user", user);
        query.executeUpdate();
    }
}
