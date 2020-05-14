package app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "readers")
public class Reader extends IdentifiableVersionedEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "reader_awaiting_books",
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    List<Book> awaitingBooks;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "notify_on_book_creation", nullable = false)
    private Boolean notifyOnBookCreation;

    public Boolean getNotifyOnBookCreation() {
        return notifyOnBookCreation;
    }

    public void setNotifyOnBookCreation(Boolean notifyOnBookCreation) {
        this.notifyOnBookCreation = notifyOnBookCreation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Reader() {
        super();
    }

    public List<Book> getAwaitingBooks() {
        return awaitingBooks;
    }

    public void setAwaitingBooks(List<Book> awaitingBooks) {
        this.awaitingBooks = awaitingBooks;
    }

    public void addBookToAwaitFor(Book book) {
        awaitingBooks.add(book);
    }

    public void removeBookToAwait(Book book) {
        awaitingBooks = awaitingBooks.stream().filter(b -> b.getId() != book.getId()).collect(Collectors.toCollection(ArrayList::new));
    }
}
