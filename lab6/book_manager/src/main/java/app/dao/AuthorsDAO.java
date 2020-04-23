package app.dao;

import app.model.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("AuthorsDAO")
@ApplicationScoped
public class AuthorsDAO extends GenericEntityDAO<Author> {

    public AuthorsDAO() {
        super();
    }
}
