/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.util;

import service.product.DatabaseParams;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * @author faisalrahman
 * @version $Id: EntityManagerConfigurator.java, v 0.1 20210112 17.55 faisalrahman Exp $$
 */
public class EntityManagerConfigurator {

    private static EntityManagerConfigurator entityManagerConfigurator = null;
    private EntityManager entityManager;

    private EntityManagerConfigurator(DatabaseParams databaseParams) {

        Map properties = new HashMap();
        properties.put("javax.persistence.jdbc.url", databaseParams.getDatabaseHost());
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("javax.persistence.jdbc.user", databaseParams.getDatabaseUsername());
        properties.put("javax.persistence.jdbc.password", databaseParams.getDatabasePassword());
        properties.put("eclipselink.allow-zero-id", "true");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(databaseParams.getDatabaseName(), properties);
        entityManager = emf.createEntityManager();
    }

    public static EntityManagerConfigurator getInstance() {

        if (entityManagerConfigurator == null) {
            throw new AssertionError("You have to call init first");
        }

        return entityManagerConfigurator;
    }

    public synchronized static EntityManagerConfigurator init(DatabaseParams databaseParams) {

        if (entityManagerConfigurator != null) {
            throw new AssertionError("You already initialized");
        }

        entityManagerConfigurator = new EntityManagerConfigurator(databaseParams);

        return entityManagerConfigurator;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}