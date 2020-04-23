package app.dao;

import app.model.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("ReadersDAO")
@ApplicationScoped
public class ReadersDAO extends GenericEntityDAO<Reader> {
    public ReadersDAO() {
        super();
    }
}
