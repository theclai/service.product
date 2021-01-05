/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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
        entityManager.close();
    }

    @Test
    public void getCategory_withUUId_returnCategory() {

        UUID fakeId =  UUID.randomUUID();

        provider.begin();
        provider.em().persist(new Log(0, new Date()));
        provider.em().persist(new CategoryTx(fakeId, 0, new Date()));
        provider.em().persist(new Category(fakeId, 0, new Date(),  false, "Physical Goods", null, null, null));

        CategoryDao categoryDao = new CategoryDao(entityManager);
        UUID id = fakeId;

        try {
            Optional<Category> actualValue = categoryDao.get(id);
        }catch (Exception e){
            String t = e.getMessage();
        }

        //assertEquals(actualValue.get().getId(), id);
    }
}