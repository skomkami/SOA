package app;

import app.message.NotifyMessage;
import app.model.Notification;
import app.model.User;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("notifications")
@RequestScoped
public class NotificationsBean {
    @Resource(mappedName = "java:/jms/queue/SOA_test")
    private Queue queue;

    @Inject
    JMSContext context;

    public void sendMessage(NotifyMessage nm) {
        try {
            context.createProducer().send(queue, nm);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public NotifyMessage receiveMessage() {
        NotifyMessage nm = context.createConsumer(queue).receiveBodyNoWait(NotifyMessage.class);
        return nm;
    }

    public List<NotifyMessage> getMessages() {
        ArrayList<NotifyMessage> messages = new ArrayList<>();

        NotifyMessage nm = receiveMessage();
        for(; nm != null; nm=receiveMessage()) {
            messages.add(nm);
        }

        return messages;
    }

    public List<Notification> getNotificationsForUser(User user) {
        ArrayList<Notification> notifications = new ArrayList<>();
        ArrayList<NotifyMessage> leftMessages = new ArrayList<>();
        for(NotifyMessage nm: getMessages()) {
            if(nm.getUsers().stream().filter(userLogin -> userLogin.equals(user.getLogin())).findFirst().isPresent()) {
                notifications.add(new Notification(user, nm.getThemeList(), nm.getTheme()));
            }

            ArrayList<String> leftUsers = nm
                    .getUsers()
                    .stream()
                    .filter(userLogin -> !userLogin.equals(user.getLogin()))
                    .collect(Collectors.toCollection(ArrayList::new));

            if (!leftUsers.isEmpty())
                leftMessages.add(new NotifyMessage(nm.getThemeList(), nm.getTheme(), leftUsers));
        }

//        for( NotifyMessage nm: leftMessages)
//            sendMessage(nm);
        leftMessages.forEach(this::sendMessage);

        return notifications;
    }
}