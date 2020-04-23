package app.beans;

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

    private Map<Integer, Boolean> checkedBooks = new HashMap<Integer, Boolean>();

    @Inject
    private BooksDAO booksDAO;

    public List<Book> getBooksList() {
        return booksDAO.getAll();
    }

    public List<Integer> getBooksIds() {
        return getBooksList().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void deleteBook() {
        try {
            for ( Book b: getSelectedBooks() )
                booksDAO.remove(b);
        } catch (Exception e) {

        }
    }

    public Map<Integer, Boolean> getCheckedBooks() {
        return checkedBooks;
    }

    public List<Book> getSelectedBooks() {
        List<Book> selectedBooks = new ArrayList<>();
        for(Book book : getBooksList())
            if(checkedBooks.containsKey(book.getId()) && checkedBooks.get(book.getId()))
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
            editBook = booksDAO.find(editBookId);
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
            booksDAO.add(this.addBook);
            this.addBook = new Book();
        }
    }

    public void setAddBook(Book addBook) {
        this.addBook = addBook;
    }

    public boolean isInEditMode() {
        return editBookId != null;
    }

    public void editBookInDAO() {
        booksDAO.edit(this.editBook);
        this.editBook = null;
        this.editBookId = null;
    }

}
