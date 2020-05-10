package app.beans;

import app.dao.ReadersDAO;
import app.model.Book;
import app.model.Notification;

import javax.inject.Inject;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("books")
@SessionScoped
public class Books extends GenericBean<Book> {

    @Inject
    ReadersDAO readersDAO;

    @Inject
    JMSService jmsService;

    @Override
    public void insertEntity() {

        Book b = addEntity;
        super.insertEntity();

        readersDAO.getAllReadersWithNewBookNotification().forEach( reader -> {
            StringBuilder message = new StringBuilder();
            message .append(b.getTitle())
                    .append(" from author: ")
                    .append(b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName())
                    .append(", category: " + b.getCategory());

            Notification notification = new Notification(message.toString(), reader.getLogin());
            jmsService.sendMessage(notification);
        });

    }
}
