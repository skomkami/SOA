package app.dao;

import app.model.Notification;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("NotificationsDAO")
@SessionScoped
public class NotificationsDAO extends GenericEntityDAO<Notification> {
}
