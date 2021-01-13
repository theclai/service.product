/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.util;

import service.product.DatabaseParams;

import java.util.HashMap;
import java.util.Map;

/**
 * @author faisalrahman
 * @version $Id: EntityManagerProperties.java, v 0.1 20210112 17.20 faisalrahman Exp $$
 */
public final class EntityManagerPropertiesFactory {

    private static EntityManagerPropertiesFactory entityManagerPropertiesFactory = null;
    private DatabaseParams databaseParams;
    public Map properties;

    private EntityManagerPropertiesFactory(DatabaseParams databaseParams){

        this.databaseParams = databaseParams;
        properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.url", databaseParams.getDatabaseHost());
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("javax.persistence.jdbc.user", databaseParams.getDatabaseUsername());
        properties.put("javax.persistence.jdbc.password", databaseParams.getDatabasePassword());
        properties.put("eclipselink.allow-zero-id", "true");
    }

    public static EntityManagerPropertiesFactory getInstance(){

        if(entityManagerPropertiesFactory == null){
            throw new AssertionError("You have to call init first");
        }

        return entityManagerPropertiesFactory;
    }

    public synchronized static EntityManagerPropertiesFactory init(DatabaseParams databaseParams){

        if(entityManagerPropertiesFactory != null){
            throw new AssertionError("You already initialized");
        }

        entityManagerPropertiesFactory = new EntityManagerPropertiesFactory(databaseParams);

        return entityManagerPropertiesFactory;
    }
}