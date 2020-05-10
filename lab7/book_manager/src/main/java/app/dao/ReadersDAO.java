package app.dao;

import app.model.Reader;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.List;

@Named("ReadersDAO")
@SessionScoped
public class ReadersDAO extends GenericEntityDAO<Reader> {
    public ReadersDAO() {
        super();
    }

    public List<Reader> getAllReadersWithNewBookNotification() {
        String jpql = "SELECT r FROM Reader r WHERE r.notifyOnBookCreation = true";
        TypedQuery<Reader> query = em.createQuery(jpql, Reader.class);
        return query.getResultList();
    }
}
