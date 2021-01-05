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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author faisalrahman
 * @version $Id: CategoryDaoTest.java, v 0.1 20210104 17.43 faisalrahman Exp $$
 */

@RunWith(JUnit4.class)
public class CategoryDaoTest {

    private static final String PERSISTENCE_UNIT_NAME = "service_product";

    private EntityManager entityManager;

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.withUnit(PERSISTENCE_UNIT_NAME);

    @Before
    public void Setup() {

        entityManager = provider.em();
    }

    @After
    public void tearDown() throws Exception {

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("truncate table log").executeUpdate();
        entityManager.createNativeQuery("truncate table category_tx").executeUpdate();
        entityManager.createNativeQuery("truncate table category").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.close();
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