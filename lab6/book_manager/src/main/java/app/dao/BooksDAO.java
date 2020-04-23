package app.dao;

import app.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("BooksDAO")
@ApplicationScoped
public class BooksDAO extends GenericEntityDAO<Book> {

    public BooksDAO()
    {
        super();
    }

}
