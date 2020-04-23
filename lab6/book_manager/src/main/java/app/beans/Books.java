package app.beans;

import app.dao.AuthorsDAO;
import app.dao.BooksDAO;
import app.dao.CategoriesDAO;
import app.model.Author;
import app.model.Book;
import app.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private CategoriesDAO categoriesDAO;

    public List<Integer> getAuthorsIds() {
        return authorsDAO.getAuthors().stream().map(a -> a.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Integer> getCategoriesIds() {
        return categoriesDAO.getCategories().stream().map(c -> c.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Book> getBooksList() {
        return booksDAO.getBooks();
    }

    public List<Integer> getBooksIds() {
        return getBooksList().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void deleteBook() {
        try {
            for ( Book b: getSelectedBooks() )
                booksDAO.removeBook(b);
        } catch (Exception e) {

        }
    }

    private Integer addBookAuthorId;
    private Integer addBookCategoryId;

    public Integer getAddBookAuthorId() {
        return addBookAuthorId;
    }

    public void setAddBookAuthorId(Integer addBookAuthorId) {
        this.addBookAuthorId = addBookAuthorId;
    }

    public Integer getAddBookCategoryId() {
        return addBookCategoryId;
    }

    public void setAddBookCategoryId(Integer addBookCategoryId) {
        this.addBookCategoryId = addBookCategoryId;
    }


    private Integer editBookAuthorId;
    private Integer editBookCategoryId;

    public Integer getEditBookAuthorId() {
        return editBookAuthorId;
    }

    public void setEditBookAuthorId(Integer editBookAuthorId) {
        this.editBookAuthorId = editBookAuthorId;
    }

    public Integer getEditBookCategoryId() {
        return editBookCategoryId;
    }

    public void setEditBookCategoryId(Integer editBookCategoryId) {
        this.editBookCategoryId = editBookCategoryId;
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
            editBook = booksDAO.findBook(editBookId);
            editBookAuthorId = editBook.getAuthor().getId();
            editBookCategoryId = editBook.getCategory().getId();
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

            if (addBookAuthorId == null || addBookCategoryId == null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("addBook", new FacesMessage("Set authorId and categoryId"));
            } else {
                Author author = authorsDAO.findAuthor(addBookAuthorId);
                Category category = categoriesDAO.findCategory(addBookCategoryId);
                this.addBook.setAuthor(author);
                this.addBook.setCategory(category);
                booksDAO.addBook(this.addBook);
                this.addBook = new Book();
            }
        }
    }

    public void setAddBook(Book addBook) {
        this.addBook = addBook;
    }

    public boolean isInEditMode() {
        return editBookId != null;
    }

    public void editBookInDAO() {

        if (this.editBookAuthorId != editBook.getAuthor().getId()) {
            Author author = authorsDAO.findAuthor(editBookAuthorId);
            editBook.setAuthor(author);
        }
        if (this.editBookCategoryId != editBook.getCategory().getId()) {
            Category category = categoriesDAO.findCategory(editBookCategoryId);
            editBook.setCategory(category);
        }

        booksDAO.editBook(this.editBook);
        this.editBook = null;
        this.editBookId = null;
    }

}
