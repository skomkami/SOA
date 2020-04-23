package app.dao;

import app.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Named("CategoriesDAO")
@ApplicationScoped
public class CategoriesDAO extends EntityDAO {

    public CategoriesDAO() {
        super();
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            Query q = em.createQuery("FROM Category ", Category.class);
            categories = q.getResultList();
        }
        catch(Exception e) {
            System.err.println("Error while fetching data: " + e);
        }
        return categories;
    }

    public void addCategory(Category category) {
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            System.out.println("Category with id: " + category.getId() + " written into database");
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error during record insert: " + e);
        }
    }

    public void removeCategory(Category category) {
        try {
            em.getTransaction().begin();
            em.remove(category);
            em.getTransaction().commit();
            System.out.println("Category with id: " + category.getId() + " removed from database");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while removing category with id: " + category.getId());
        }

    }

    public void editCategory(Category category) {
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
            System.out.println("Successfully updated category with id: " + category.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while updating category with id: " + category.getId());
        }
    }

    public Category findCategory(int id) {
        return em.find(Category.class, id);
    }
}
