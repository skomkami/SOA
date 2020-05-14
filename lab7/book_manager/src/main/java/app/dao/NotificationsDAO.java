package app.dao;

import app.model.Notification;
import app.model.Reader;
import app.repository.NotificationsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@ApplicationScoped
@Named("NotificationsDAO")
public class NotificationsDAO extends GenericEntityDAO<Notification> {

    public List<Notification> getNotificationsForReader(Reader reader) {
        return NotificationsRepository.getInstance().getNotificationsForReader(reader);
    }

    public void removeNotificationsForReader(Reader reader) {
        NotificationsRepository.getInstance().removeNotificationsForReader(reader);
    }
}
