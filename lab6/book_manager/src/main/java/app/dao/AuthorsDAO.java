package app.dao;

import app.model.Author;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named("AuthorsDAO")
@SessionScoped
public class AuthorsDAO extends GenericEntityDAO<Author> {

    public AuthorsDAO() {
        super();
    }
}
