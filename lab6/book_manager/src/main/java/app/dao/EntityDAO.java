package app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract public class EntityDAO {
    protected EntityManagerFactory factory;
    protected EntityManager em;

    public EntityDAO()
    {
        factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = factory.createEntityManager();
    }
}
