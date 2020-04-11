package app.dao;

import app.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Named("BooksDAO")
@ApplicationScoped
public class BooksDAO {

    private EntityManagerFactory factory;
    private EntityManager em;

    public BooksDAO()
    {
        factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = factory.createEntityManager();
    }

    public  List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Query q = em.createQuery("FROM Book ", Book.class);
            books = q.getResultList();
        }
        catch(Exception e) {
            System.err.println("Error while fetching data: " + e);
        }
        return books;
    }

    public void addBook(Book book) {
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            System.out.println("Book with id: " + book.getId() + " written into database");
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error during record insert: " + e);
        }
    }

    public void removeBook(Book book) {
        try {
            em.getTransaction().begin();
            em.remove(book);
            em.getTransaction().commit();
            System.out.println("Book with id: " + book.getId() + " removed from database");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while removing book with id: " + book.getId());
        }

    }

    public void editBook(Book book) {
        try {
            em.getTransaction().begin();
            em.merge(book);
            em.getTransaction().commit();
            System.out.println("Successfully updated book with id: " + book.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while updating book with id: " + book.getId());
        }
    }

    public Book findBook(int id) {
        return em.find(Book.class, id);
    }

}
