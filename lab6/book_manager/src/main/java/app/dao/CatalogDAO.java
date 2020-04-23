package app.dao;

import app.model.Catalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Named("CatalogDAO")
@ApplicationScoped
public class CatalogDAO extends EntityDAO {
    public CatalogDAO() {
        super();
    }

    public List<Catalog> getCatalogs() {
        List<Catalog> catalogs = new ArrayList<>();
        try {
            Query q = em.createQuery("FROM Catalog ", Catalog.class);
            catalogs = q.getResultList();
        }
        catch(Exception e) {
            System.err.println("Error while fetching data: " + e);
        }
        return catalogs;
    }

    public void addCatalog(Catalog catalog) {
        try {
            em.getTransaction().begin();
            em.persist(catalog);
            em.getTransaction().commit();
            System.out.println("Catalog with id: " + catalog.getId() + " written into database");
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error during record insert: " + e);
        }
    }

    public Catalog getCatalogWithBookId(int bookId) {
        String jpql = "SELECT c FROM Catalog c WHERE c.book.id = :bookId";
        TypedQuery<Catalog> query = em.createQuery(jpql, Catalog.class);
        query.setParameter("bookId", bookId);
        return query.getSingleResult();
    }

    public void removeCatalog(Catalog catalog) {
        try {
            em.getTransaction().begin();
            em.remove(catalog);
            em.getTransaction().commit();
            System.out.println("Catalog with id: " + catalog.getId() + " removed from database");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while removing catalog with id: " + catalog.getId());
        }

    }

    public void editCatalog(Catalog catalog) {
        try {
            em.getTransaction().begin();
            em.merge(catalog);
            em.getTransaction().commit();
            System.out.println("Successfully updated catalog with id: " + catalog.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while updating catalog with id: " + catalog.getId());
        }
    }

    public Catalog findCatalog(int id) {
        return em.find(Catalog.class, id);
    }
}
