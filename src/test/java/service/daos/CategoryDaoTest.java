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
import javax.persistence.Query;

import org.testcontainers.containers.PostgreSQLContainer;
import service.product.DatabaseParams;
import service.product.ServiceException;

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

    private static final String PERSISTENCE_UNIT_NAME = "service_product";

    private EntityManager entityManager;
    private EntityManagerProvider provider;

    @Before
    public void Setup() {

        provider = EntityManagerProvider.withUnit(PERSISTENCE_UNIT_NAME);
        entityManager = provider.em();
    }

    @After
    public void tearDown() throws Exception {

        provider.begin();
        Query q1 = provider.em().createNativeQuery("DELETE FROM category");
        Query q2 = provider.em().createNativeQuery("DELETE FROM category_tx");
        Query q3 = provider.em().createNativeQuery("DELETE FROM log");

        q1.executeUpdate();
        q2.executeUpdate();
        q3.executeUpdate();

        provider.commit();
        entityManager.close();
    }

    @Test
    public void getCategory_withUUId_returnCategory() throws ServiceException {

        UUID fakeId = UUID.fromString("58addfe9-d87d-4ea0-8c88-f4561aa72607");
        provider.begin();
        provider.em().persist(new Log(0, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeId, 0, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeId, 0, new Date(), false, "Physical Goods", null, null, null));
        provider.commit();

        CategoryDao categoryDao = new CategoryDao(entityManager);
        UUID id = fakeId;
        Optional<Category> categoryValue = categoryDao.get(id);
        String actualValue = categoryValue.get().getTitle();
        String expectedValue = "Physical Goods";

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getCategoryList_withListOfParentUUID_returnCategory() throws ServiceException {

        UUID fakeCategoryId1 = UUID.fromString("d7b137f3-9d81-4d41-9169-7040d0adf0bb");
        UUID fakeCategoryId2 = UUID.fromString("ff46a914-c61b-4e2e-aecb-3d4f1889774f");
        UUID fakeCategoryId3 = UUID.fromString("265132e6-2b63-4034-9c80-569f5c7d327b");
        UUID fakeCategoryId4 = UUID.fromString("68ec07e3-1052-4781-9916-ae649bae120d");
        UUID fakeCategoryId5 = UUID.fromString("2b8f70c4-99ea-45c0-b6dd-398e937ad8d6");

        provider.begin();
        provider.em().persist(new Log(6, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId1, 6, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId1, 6, new Date(), false, "Physical Goods", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId2, 6, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId2, 6, new Date(), false, "Electronics", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId3, 6, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId3, 6, new Date(), false, "Phone Accessories", null, null, fakeCategoryId1));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId4, 6, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId4, 6, new Date(), false, "Yachts", null, null, fakeCategoryId1));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId5, 6, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId5, 6, new Date(), false, "Physical Goods", null, null, fakeCategoryId1));
        provider.commit();

        List<UUID> categoryParentList = new ArrayList<>();
        categoryParentList.add(fakeCategoryId1);

        CategoryDao categoryDao = new CategoryDao(entityManager);
        List<Category> categoryList = categoryDao.getCategoryList(categoryParentList);

        long expectedValue = 3;
        long actualValue = categoryList.stream().count();

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getCategoryList_withEmptyParentIds_returnAllCategory() throws ServiceException {

        UUID fakeCategoryId1 = UUID.fromString("7867408e-185a-45a9-9882-9fa5b6f87d24");
        UUID fakeCategoryId2 = UUID.fromString("19a6c5b8-5d30-4719-8531-4e5faf1027a8");
        UUID fakeCategoryId3 = UUID.fromString("943a558b-2fb4-490a-afdc-79ffff28a523");
        UUID fakeCategoryId4 = UUID.fromString("1bc6476e-de89-474f-bdd1-9c6b0bf6812c");
        UUID fakeCategoryId5 = UUID.fromString("737d4454-504e-4454-93d5-554bdc4c1da8");

        provider.begin();
        provider.em().persist(new Log(7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId1, 7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId1, 7, new Date(), false, "Physical Goods", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId2, 7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId2, 7, new Date(), false, "Electronics", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId3, 7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId3, 7, new Date(), false, "Phone Accessories", null, null, fakeCategoryId1));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId4, 7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId4, 7, new Date(), false, "Yachts", null, null, fakeCategoryId1));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeCategoryId5, 7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeCategoryId5, 7, new Date(), false, "Physical Goods", null, null, fakeCategoryId1));
        provider.commit();

        List<UUID> categoryParentList = new ArrayList<>();

        CategoryDao categoryDao = new CategoryDao(entityManager);
        List<Category> categoryList = categoryDao.getCategoryList(categoryParentList);

        long expectedValue = 5;
        long actualValue = categoryList.stream().count();
        
        Assert.assertEquals(actualValue, expectedValue);
    }
}