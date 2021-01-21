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
import service.product.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

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

        provider.begin();
        Query q1 = provider.em().createNativeQuery("DELETE FROM product");
        Query q2 = provider.em().createNativeQuery("DELETE FROM product_tx");
        Query q3 = provider.em().createNativeQuery("DELETE FROM category");
        Query q4 = provider.em().createNativeQuery("DELETE FROM category_tx");
        Query q5 = provider.em().createNativeQuery("DELETE FROM log");

        q1.executeUpdate();
        q2.executeUpdate();
        q3.executeUpdate();
        q4.executeUpdate();
        q5.executeUpdate();
        provider.commit();
        entityManager.close();
    }

    @Test
    public void getProduct_withUUId_returnProduct() throws ServiceException {

        UUID fakeProductId =  UUID.fromString("59e1ae6c-45a2-41b6-a2f2-fce36b541b05");
        UUID fakeIdCategory =  UUID.fromString("feb246cb-cf3f-40f2-b3a0-6e84ce27396d");

        Log logObj = new Log(1, new Date());
        CategoryTx categoryTxObj = new CategoryTx(fakeIdCategory, 1, new Date());
        Category categoryObj = new Category(fakeIdCategory, 1, new Date(),  false, "Physical Goods", null, null, null);
        ProductTx productTxObj = new ProductTx(fakeProductId, 1, new Date());
        Product productObj = new Product(fakeProductId, 1, new Date(),  false, fakeIdCategory, 20);

        provider.begin();
        provider.em().persist(logObj);
        provider.commit();

        provider.begin();
        provider.em().persist(categoryTxObj);
        provider.commit();

        provider.begin();
        provider.em().persist(categoryObj);
        provider.commit();

        provider.begin();
        provider.em().persist(productTxObj);
        provider.commit();

        provider.begin();
        provider.em().persist(productObj);
        provider.commit();

        ProductDao productDao = new ProductDao(entityManager);
        UUID id = fakeProductId;

        Optional<Product> productValue = productDao.get(id);
        int actualValue = productValue.get().getQuantity();
        int expectedValue = 20;

        Assert.assertEquals(actualValue, expectedValue);
        Assert.assertEquals(productValue.get().getId(), productObj.getId());
        Assert.assertEquals(productValue.get().getTx(), productObj.getTx());
        Assert.assertEquals(productValue.get().getValidTime(), productObj.getValidTime());
        Assert.assertEquals(productValue.get().isDeleted(), productObj.isDeleted());
        Assert.assertEquals(productValue.get().getCategory(), productObj.getCategory());
        Assert.assertEquals(productValue.get().getQuantity(), productObj.getQuantity());
    }

    @Test
    public void getListOfProduct_categoryList_isNull_returnAllProduct() throws ServiceException {

        UUID fakeProductId1 =  UUID.fromString("0450df08-2245-41e8-8345-3918fd5db926");
        UUID fakeProductId2 =  UUID.fromString("04365df2-07eb-4c55-b756-db989d9b99ec");
        UUID fakeProductId3 =  UUID.fromString("cfa30360-6c13-4bbf-bbfe-3de5499cc383");
        UUID fakeProductId4 =  UUID.fromString("ca4caf23-e8e1-46bd-bd10-e16e3c7d8b18");
        UUID fakeIdCategory1 =  UUID.fromString("90296533-1d43-47ce-920a-6cb119dde1bd");
        UUID fakeIdCategory2 =  UUID.fromString("13924fb7-6591-436a-afba-6b67593c5d7c");

        provider.begin();
        provider.em().persist(new Log(4, new Date()));
        provider.commit();

        //category1
        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory1, 4, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory1, 4, new Date(),  false, "Phone Accessories", null, null, null));
        provider.commit();

        //category2
        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory2, 4, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory2, 4, new Date(),  false, "Yachts", null, null, null));
        provider.commit();

        //product1
        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId1, 4, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId1, 4, new Date(),  false, fakeIdCategory1, 20));
        provider.commit();

        //product2
        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId2, 4, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId2, 4, new Date(),  false, fakeIdCategory1, 30));
        provider.commit();

        //product3
        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId3, 4, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId3, 4, new Date(),  false, fakeIdCategory2, 40));
        provider.commit();

        //product4
        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId4, 4, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId4, 4, new Date(),  false, fakeIdCategory2, 50));
        provider.commit();

        ProductDao productDao = new ProductDao(entityManager);
        List<UUID> categoryIdList = new ArrayList<>();

        List<Product> productList = productDao.getProductList(categoryIdList);

        long expectedValue = 4;
        long actualValue = productList.stream().count();
        
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getListOfProduct_categoryList_isNot_Null_returnProductByCategory() throws ServiceException {

        UUID fakeProductId1 =  UUID.fromString("8dbfcaa0-6eec-46e2-b883-941ff858c7cd");
        UUID fakeProductId2 =  UUID.fromString("be90abfc-4319-4008-bd9c-94e8f5696d02");
        UUID fakeProductId3 =  UUID.fromString("07e2d164-a82c-4a32-81e2-776b5e919990");
        UUID fakeProductId4 =  UUID.fromString("120adedd-1e58-4360-980c-459dfdda28f3");
        UUID fakeIdCategory1 =  UUID.fromString("65247ab7-1423-402e-b624-04ee89cfc313");
        UUID fakeIdCategory2 =  UUID.fromString("9408699c-49c9-4857-8519-3cec6907407c");

        provider.begin();
        provider.em().persist(new Log(5, new Date()));
        provider.commit();

        //category1
        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory1, 5, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory1, 5, new Date(),  false, "Phone Accessories", null, null, null));
        provider.commit();

        //category2
        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory2, 5, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory2, 5, new Date(),  false, "Yachts", null, null, null));
        provider.commit();

        //product1
        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId1, 5, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId1, 5, new Date(),  false, fakeIdCategory1, 20));
        provider.commit();

        //product2
        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId2, 5, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId2, 5, new Date(),  false, fakeIdCategory1, 30));
        provider.commit();

        //product3
        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId3, 5, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId3, 5, new Date(),  false, fakeIdCategory2, 40));
        provider.commit();

        //product4
        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId4, 5, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId4, 5, new Date(),  false, fakeIdCategory2, 50));
        provider.commit();

        ProductDao productDao = new ProductDao(entityManager);
        List<UUID> categoryIdList = new ArrayList<>();
        categoryIdList.add(fakeIdCategory1);

        List<Product> productList = productDao.getProductList(categoryIdList);

        long actualValue = productList.stream().count();
        long expectedValue = 2;

        Assert.assertEquals(actualValue, expectedValue);
    }
}