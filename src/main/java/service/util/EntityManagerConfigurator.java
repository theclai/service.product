/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.util;

import service.product.DatabaseParams;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

/**
 * @author faisalrahman
 * @version $Id: EntityManagerConfigurator.java, v 0.1 20210112 17.55 faisalrahman Exp $$
 */
public class EntityManagerConfigurator {

    private static EntityManagerConfigurator entityManagerConfigurator = null;
    public EntityManager entityManager;

    private EntityManagerConfigurator(String unitName, Map properties) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName, properties);
        entityManager = emf.createEntityManager();
    }

    public static EntityManagerConfigurator getInstance() {

        if (entityManagerConfigurator == null) {
            throw new AssertionError("You have to call init first");
        }

        return entityManagerConfigurator;
    }

    public synchronized static EntityManagerConfigurator init(String unitName, Map properties) {

        if (entityManagerConfigurator != null) {
            throw new AssertionError("You already initialized");
        }

        entityManagerConfigurator = new EntityManagerConfigurator(unitName, properties);

        return entityManagerConfigurator;
    }
}