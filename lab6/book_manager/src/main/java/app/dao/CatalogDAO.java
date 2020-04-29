package app.dao;

import app.model.Catalog;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
@Named("CatalogDAO")
@SessionScoped
public class CatalogDAO extends GenericEntityDAO<Catalog> {
    public CatalogDAO() {
        super();
    }

    public Catalog getCatalogWithBookId(int bookId) {
        beginTransaction();
        String jpql = "SELECT c FROM Catalog c WHERE c.book.id = :bookId";
        TypedQuery<Catalog> query = em.createQuery(jpql, Catalog.class);
        Catalog c = query.setParameter("bookId", bookId).getSingleResult();
        em.lock(c, LockModeType.OPTIMISTIC);
        return c;
    }
}
