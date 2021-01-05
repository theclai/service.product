/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.entities.Category;
import service.entities.CategoryTx;
import service.entities.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT;

import java.time.LocalDateTime;
import java.util.*;
import static java.lang.String.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author faisalrahman
 * @version $Id: CategoryDaoTest.java, v 0.1 20210104 17.43 faisalrahman Exp $$
 */

@RunWith(JUnit4.class)
public class CategoryDaoTest {

    private static final String USER = "test";
    private static final String PASSWORD = "test";
    private static final String DATABASE = "service_product_db";
    private static final String PERSISTENCE_UNIT_NAME = "service_product";
    private static final int POSTGRESQL_PORT = 5433;

    private EntityManager entityManager;
    private EntityManagerProvider provider;
    private PostgreSQLContainer postgreSQLContainer;

    @Before
    public void Setup() {

        postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
                .withDatabaseName(DATABASE)
                .withUsername(USER)
                .withPassword(PASSWORD);

        postgreSQLContainer.start();

        Map properties = new HashMap<>();
        properties.put("eclipselink.ddl-generation", "create-tables");
        properties.put("javax.persistence.jdbc.url", getJdbcUrl());
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("javax.persistence.jdbc.user", USER);
        properties.put("javax.persistence.jdbc.password", PASSWORD);
        properties.put("eclipselink.allow-zero-id", "true");

        //entityManager = provider.em();
        provider = EntityManagerProvider.withUnit(PERSISTENCE_UNIT_NAME, properties);
        entityManager = provider.em();
    }

    public String getJdbcUrl() {

        return format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
                postgreSQLContainer.getContainerIpAddress(),
                postgreSQLContainer.getMappedPort(POSTGRESQL_PORT),
                DATABASE, USER, PASSWORD);
    }

    @After
    public void tearDown() throws Exception {

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("truncate table category_tx").executeUpdate();
        entityManager.createNativeQuery("truncate table category").executeUpdate();
        entityManager.createNativeQuery("truncate table log").executeUpdate();
        entityManager.getTransaction().commit();

        postgreSQLContainer.close();
    }

    @Test
    public void getCategory_withUUId_returnCategory() {

        UUID fakeId =  UUID.fromString("58addfe9-d87d-4ea0-8c88-f4561aa72607");
        provider.begin();
        provider.em().persist(new Log(0, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeId, 0, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeId, 0, new Date(),  false, "Physical Goods", null, null, null));
        provider.commit();

        CategoryDao categoryDao = new CategoryDao(entityManager);
        UUID id = fakeId;
        Optional<Category> categoryValue = categoryDao.get(id);
        String actualValue = categoryValue.get().getTitle();
        String expectedValue = "Physical Goods";

        Assert.assertEquals(actualValue, expectedValue);
    }
}