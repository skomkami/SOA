package app;

import app.dao.BooksDAO;
import app.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("books")
@ApplicationScoped
public class Books implements Serializable {

    private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();

    @Inject
    private BooksDAO booksDAO;

    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    public List<Book> getBooksList() {
        return booksDAO.getBooks();
    }

    public List<Integer> getBooksIds() {
        return getBooksList().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void delete() {
        try {
            for ( Book b: getSelectedBooks() )
                booksDAO.removeBook(b);
        } catch (Exception e) {

        }
    }

    public List<Book> getSelectedBooks() {
        List<Book> selectedBooks = new ArrayList<>();
        for(Book book : getBooksList())
            if(checked.containsKey(book.getId()) && checked.get(book.getId()))
                selectedBooks.add(book);

        return selectedBooks;
    }

    private Book addBook = new Book();
    private Book editBook;

    public Book getEditBook() {
        return editBook;
    }

    public void setEditBook(Book editBook) {
        this.editBook = editBook;
    }

    public Integer getEditBookId() {
        return editBookId;
    }

    public void setEditBookId(Integer editBookId) {
        if ( editBookId != null ) {
            editBook = booksDAO.findBook(editBookId);
        } else {
            editBook = null;
        }

        this.editBookId = editBookId;
    }

    private Integer editBookId;

    public Book getAddBook() {
        return addBook;
    }

    public void insertBook() {
        if ( this.addBook == null ) {
            System.err.println( "Book to insert is null");
        } else {
            booksDAO.addBook(this.addBook);
            this.addBook = new Book();
        }
    }

    public void setAddBook(Book addBook) {
        this.addBook = addBook;
    }

    public boolean isInEditMode() {
        return editBookId != null;
    }

    public void edit() {
        booksDAO.editBook(this.editBook);
        this.editBook = null;
        this.editBookId = null;
    }
}
