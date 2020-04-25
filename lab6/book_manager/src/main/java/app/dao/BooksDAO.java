package app.dao;

import app.model.Book;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named("BooksDAO")
@SessionScoped
public class BooksDAO extends GenericEntityDAO<Book> {

    public BooksDAO()
    {
        super();
    }

}
