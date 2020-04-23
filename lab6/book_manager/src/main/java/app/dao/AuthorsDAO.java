package app.dao;

import app.model.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Named("AuthorsDAO")
@ApplicationScoped
public class AuthorsDAO extends EntityDAO {
    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        try {
            Query q = em.createQuery("FROM Author ", Author.class);
            authors = q.getResultList();
        }
        catch(Exception e) {
            System.err.println("Error while fetching data: " + e);
        }
        return authors;
    }

    public void addAuthor(Author author) {
        try {
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
            System.out.println("Author with id: " + author.getId() + " written into database");
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error during record insert: " + e);
        }
    }

    public void removeAuthor(Author author) {
        try {
            em.getTransaction().begin();
            em.remove(author);
            em.getTransaction().commit();
            System.out.println("Author with id: " + author.getId() + " removed from database");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while removing author with id: " + author.getId());
        }

    }

    public void editAuthor(Author author) {
        try {
            em.getTransaction().begin();
            em.merge(author);
            em.getTransaction().commit();
            System.out.println("Successfully updated author with id: " + author.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while updating author with id: " + author.getId());
        }
    }

    public Author findAuthor(int id) {
        return em.find(Author.class, id);
    }    
}
