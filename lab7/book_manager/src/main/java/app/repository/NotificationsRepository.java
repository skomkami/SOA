package app.repository;

import app.model.Notification;
import app.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationsRepository {

    private NotificationsRepository() {
    }

    private static List<Notification> notifications = new ArrayList<>();

    private static NotificationsRepository notificationsRepository;

    public static NotificationsRepository getInstance() {
        if(notificationsRepository == null) {
            notificationsRepository = new NotificationsRepository();
        }
        return notificationsRepository;
    }

    public void saveNotification(Notification n) {
        notifications.add(n);
    }

    public List<Notification> getNotificationsForReader(Reader reader) {
        return notifications.stream().filter(n -> n.getReader().getId() == reader.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeNotificationsForReader(Reader reader) {
        notifications = notifications
                .stream()
                .filter(n -> n.getReader().equals(reader.getLogin()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
