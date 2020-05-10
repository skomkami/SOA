package app.beans;

import app.dao.NotificationsDAO;
import app.model.Notification;
import app.model.Reader;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("notifications")
@SessionScoped
@MessageDriven(
        name = "notifications",
        activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/SOA_test")
})
public class NotificationsBean implements Serializable, MessageListener {

    @Resource
    private MessageDrivenContext mdctx;

    @Inject
    private NotificationsDAO notificationsDAO;

    public NotificationsBean() {
    }

    private List<Notification> notifications = new ArrayList<>();

    @Override
    public void onMessage(Message message) {
        System.out.println(message.toString());

        try {
            Notification n = message.getBody(Notification.class);
            notificationsDAO.add(n);
            notifications.add(n);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public List<Notification> getNotificationsForReader(Reader reader) {
        return notifications
                .stream()
                .filter(n -> n.getReader().equals(reader.getLogin()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeNotificationsForReader(Reader reader) {
        notifications = notifications
                .stream()
                .filter( n -> n.getReader().equals(reader.getLogin()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
