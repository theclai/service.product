/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.testcontainers.containers.PostgreSQLContainer;
import service.entities.Product;
import service.entities.ProductTx;
import service.entities.Category;
import service.entities.CategoryTx;
import service.entities.Log;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author faisalrahman
 * @version $Id: ProductDaoTest.java, v 0.1 20210106 17.03 faisalrahman Exp $$
 */

@RunWith(JUnit4.class)
public class ProductDaoTest {

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

        entityManager.close();
    }

    @Test
    public void getProduct_withUUId_returnProduct() {

        UUID fakeProductId =  UUID.fromString("59e1ae6c-45a2-41b6-a2f2-fce36b541b05");
        UUID fakeIdCategory =  UUID.fromString("feb246cb-cf3f-40f2-b3a0-6e84ce27396d");

        provider.begin();
        provider.em().persist(new Log(1, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory, 1, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory, 1, new Date(),  false, "Physical Goods", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new Log(2, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId, 2, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId, 2, new Date(),  false, fakeIdCategory, 20));
        provider.commit();

        ProductDao productDao = new ProductDao(entityManager);
        UUID id = fakeProductId;

        Optional<Product> productValue = productDao.get(id);
        int actualValue = productValue.get().getQuantity();
        int expectedValue = 20;

        Assert.assertEquals(actualValue, expectedValue);
    }
}