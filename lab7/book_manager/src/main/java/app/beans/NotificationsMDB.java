package app.beans;

import app.repository.NotificationsRepository;
import app.model.Notification;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.Serializable;

@MessageDriven(
        name = "notifications",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/SOA_test"),
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
        }
)
public class NotificationsMDB implements Serializable, MessageDrivenBean, MessageListener {

    @Resource
    private MessageDrivenContext mdctx;

    public NotificationsMDB() {
    }

    @Override
    public void onMessage(Message message) {
        System.out.println(message.toString());

        try {
            Notification n = message.getBody(Notification.class);
            NotificationsRepository.getInstance().saveNotification(n);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
        this.mdctx = ctx;
        System.out.println("TextMDB.setMessageDrivenContext, this=" +
                hashCode());
    }

    @Override
    public void ejbRemove() throws EJBException {
        mdctx = null;
    }
}
