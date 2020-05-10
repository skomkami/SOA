package app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

abstract public class EntityDAO implements Serializable {
    protected EntityManagerFactory factory;
    protected EntityManager em;

    public EntityDAO()
    {
        factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = factory.createEntityManager();
    }
}
