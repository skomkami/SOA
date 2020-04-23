package app.dao;

import app.model.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Named("ReadersDAO")
@ApplicationScoped
public class ReadersDAO extends EntityDAO {
    public ReadersDAO() {
        super();
    }

    public List<Reader> getReaders() {
        List<Reader> readers = new ArrayList<>();
        try {
            Query q = em.createQuery("FROM Reader ", Reader.class);
            readers = q.getResultList();
        }
        catch(Exception e) {
            System.err.println("Error while fetching data: " + e);
        }
        return readers;
    }

    public void addReader(Reader reader) {
        try {
            em.getTransaction().begin();
            em.persist(reader);
            em.getTransaction().commit();
            System.out.println("Reader with id: " + reader.getId() + " written into database");
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error during record insert: " + e);
        }
    }

    public void removeReader(Reader reader) {
        try {
            em.getTransaction().begin();
            em.remove(reader);
            em.getTransaction().commit();
            System.out.println("Reader with id: " + reader.getId() + " removed from database");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while removing reader with id: " + reader.getId());
        }

    }

    public void editReader(Reader reader) {
        try {
            em.getTransaction().begin();
            em.merge(reader);
            em.getTransaction().commit();
            System.out.println("Successfully updated reader with id: " + reader.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while updating reader with id: " + reader.getId());
        }
    }

    public Reader findReader(int id) {
        return em.find(Reader.class, id);
    }
}
