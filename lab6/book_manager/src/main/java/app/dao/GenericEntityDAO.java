package app.dao;

import app.model.IdentifiableEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericEntityDAO<T extends IdentifiableEntity> {

    protected EntityManagerFactory factory;
    protected EntityManager em;

    public GenericEntityDAO() {
        factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = factory.createEntityManager();
        try {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (ClassCastException e) {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getSuperclass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    private Class<T> persistentClass;

    public List<T> getAll() {
        List<T> all = new ArrayList<>();
        try {
            Query q = em.createQuery("FROM " + this.persistentClass.getName(), this.getPersistentClass());
            all = q.getResultList();
        }
        catch(Exception e) {
            System.err.println("Error while fetching data: " + e);
        }
        return all;
    }

    public void add(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            System.out.println(getPersistentClass().getName() + " with id: " + entity.getId() + " written into database");
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error during record insert: " + e);
        }
    }

    public void remove(T entity) {
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            System.out.println(getPersistentClass().getName() + " with id: " + entity.getId() + " removed from database");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while removing " + getPersistentClass().getName().toLowerCase() + " with id: " + entity.getId());
        }
    }

    public void edit(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            System.out.println("Successfully updated " + getPersistentClass().getName().toLowerCase() + " with id: " + entity.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while updating " + getPersistentClass().getName().toLowerCase() +" with id: " + entity.getId());
        }
    }

    public T find(int id) {
        return em.find(getPersistentClass(), id);
    }

}
