package app.dao;

import app.model.Reader;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named("ReadersDAO")
@SessionScoped
public class ReadersDAO extends GenericEntityDAO<Reader> {
    public ReadersDAO() {
        super();
    }
}
