package app.dao;

import app.model.IdentifiableVersionedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericEntityDAO<T extends IdentifiableVersionedEntity> implements Serializable {

    protected EntityManagerFactory factory;
    protected EntityManager em;

    public GenericEntityDAO() {
        factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = factory.createEntityManager();

        if ( getClass().getGenericSuperclass() instanceof ParameterizedType) {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        } else if ( getClass().getSuperclass().getGenericSuperclass() instanceof ParameterizedType) {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getSuperclass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            throw new ClassCastException("Count cast entity DAO to ParameterizedType");
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
            beginTransaction();
            em.persist(entity);
            commitTransaction();
            System.out.println(getPersistentClass().getName() + " with id: " + entity.getId() + " written into database");
        }
        catch(Exception e) {
            rollbackTransaction();
            System.err.println("Error during record insert: " + e);
        }
    }

    public void remove(T entity) {
        try {
            beginTransaction();
            em.remove(entity);
            commitTransaction();
            System.out.println(getPersistentClass().getName() + " with id: " + entity.getId() + " removed from database");
        } catch (Exception e) {
            rollbackTransaction();
            System.err.println("Error while removing " + getPersistentClass().getName().toLowerCase() + " with id: " + entity.getId());
        }
    }

    public void edit(T entity) {
        try {
            beginTransaction();
            em.merge(entity);
            commitTransaction();
            System.out.println("Successfully updated " + getPersistentClass().getName() + " with id: " + entity.getId());
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void beginTransaction() {
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    public void commitTransaction() {
        if( em.getTransaction().isActive()) {
            em.getTransaction().commit();
            em.getTransaction().begin();
        }
    }

    public void rollbackTransaction() {
        if(em.getTransaction().isActive()) {
            em.getTransaction().rollback();
            em.getTransaction().begin();
        }
    }

    public T find(int id) {
        beginTransaction();
        T entity = em.find(getPersistentClass(), id);
        em.lock(entity, LockModeType.OPTIMISTIC);
        return entity;
    }

}