package app.beans;

import app.dao.BooksDAO;
import app.dao.LoansDAO;
import app.dao.ReadersDAO;
import app.model.Book;
import app.model.Loan;
import app.model.Notification;
import app.model.Reader;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("loans")
@SessionScoped
public class Loans extends GenericBean<Loan> {

    @Inject
    LibraryBean libraryBean;

    @Inject
    LoansDAO loansDAO;

    @Inject
    BooksDAO booksDAO;

    @Inject
    ReadersDAO readersDAO;

    @Inject
    JMSService jmsService;

    public void insertEntity() {
        if ( this.addEntity == null ) {
            System.err.println( "Loan to insert is null");
        } else {
            Book b = addEntity.getBook();
            if (loansDAO.isBookLoaned(b)) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("Loan", new FacesMessage("Book is already loaned. We're sorry."));
                return;
            }

            addEntity.setReader(libraryBean.getReader());

            dao.add(this.addEntity);
            this.addEntity = new Loan();
            Notification notification = new Notification("You have borrowed book: " + b.getTitle(), libraryBean.getReader());
            jmsService.sendMessage(notification);
        }
    }

    public void delete() {
        try {
            for ( Loan l: getSelectedList() ) {
                Notification notification = new Notification("You have return book: " + l.getBook().getTitle(), libraryBean.getReader());
                createNotificationsForAwaitingUsers(l.getBook());
                dao.remove(l);
                jmsService.sendMessage(notification);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Loan> getCurrentUserLoans() {
        return getListOfAll()
                .stream()
                .filter(loan -> loan.getReader().getId() == libraryBean.getReader().getId())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Book> getCurrentlyUnavailableBooks() {
        return getListOfAll().stream().map(loan -> loan.getBook()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Book> getCurrentlyAvailableBooks() {
        List<Integer> unavaliableBooksIds = getCurrentlyUnavailableBooks().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
        return booksDAO.getAll().stream().filter(b -> !unavaliableBooksIds.contains(b.getId())).collect(Collectors.toCollection(ArrayList::new));
    }

    private Book awaitBook;

    public Book getAwaitBook() {
        return awaitBook;
    }

    public void setAwaitBook(Book awaitBook) {
        this.awaitBook = awaitBook;
    }

    public void addAwaitBook() {
        if(awaitBook == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Loan", new FacesMessage("Something went wrong. Please contact administrator."));
            return;
        }

        Reader r = libraryBean.getReader();
        r.addBookToAwaitFor(awaitBook);
        readersDAO.edit(r);
        System.out.println("add await book for " + r.getLogin());
        awaitBook = null;
    }

    private void createNotificationsForAwaitingUsers(Book book) {
        List<Reader> readerAwaitingForBook = readersDAO
                .getAll()
                .stream()
                .filter(reader -> reader.getAwaitingBooks().contains(book))
                .collect(Collectors.toCollection(ArrayList::new));

        readerAwaitingForBook.forEach(reader -> {
            reader.removeBookToAwait(book);
            readersDAO.edit(reader);
            Notification n = new Notification("Book: " + book.getTitle() + " is now available", reader);
            jmsService.sendMessage(n);
        });
    }
}
