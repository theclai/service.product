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
import service.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: VariantPropertyDaoTest.java, v 0.1 20210112 13.24 faisalrahman Exp $$
 */

@RunWith(JUnit4.class)
public class VariantPropertyDaoTest {

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
    public void getVariantProperties_WithProductVariantId_ReturnListVariantProperty(){

        UUID fakeProductId =  UUID.fromString("b09713e5-72a3-4e58-9227-b36b0b847e48");
        UUID fakeIdCategory =  UUID.fromString("a7e2b9eb-c48a-4fc3-9dec-c6b73d390d54");
        UUID fakeProductVariantId1 = UUID.fromString("5d123387-6d0f-48f4-aad4-21ffdd1a02c4");
        UUID fakeProductVariantId2  = UUID.fromString("fa3dcc02-9e11-4628-98b2-632b17a37969");

        provider.begin();
        provider.em().persist(new Log(11, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory, 11, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory, 11, new Date(),  false, "Electronics", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId, 11, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId, 11, new Date(),  false, fakeIdCategory, 30));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId1, 11, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId1,
                11,
                new Date(),
                false,
                "Fitness bracelet",
                null,
                "fitness-1",
                null,
                fakeProductId,
                10,
                "IDR",
                1068051,
                0,
                ProductVariant.Form.physical, 10, 10, 20, 20));

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId2, 11, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantPropertyTx(fakeProductVariantId1, 1, 11,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantPropertyTx(fakeProductVariantId1, 2, 11,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantProperty(fakeProductVariantId1, 1, 11,  new Date(), false, "Operating System", "Android Wear"));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantProperty(fakeProductVariantId1, 2, 11,  new Date(), false, "Compatible Operating System", "Android, iOS - Apple"));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantPropertyTx(fakeProductVariantId2, 1, 11,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantProperty(fakeProductVariantId2, 1, 11,  new Date(), false, "Type", "Type-C /Micro USB"));
        provider.commit();

        VariantPropertyDao variantPropertyDao = new VariantPropertyDao(entityManager);
        List<VariantProperty> variantPropertyList = variantPropertyDao.getVariantProperties(fakeProductVariantId1);

        String actualValue = variantPropertyList.stream().findFirst().get().getValue();
        String expectedValue = "Android Wear";

        Assert.assertEquals(actualValue, expectedValue);
        Assert.assertEquals(2, variantPropertyList.size());
    }

    private void saveToProductVariant(ProductVariant productVariant) {

        EntityManager em = provider.em();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createNativeQuery("INSERT INTO product_variant (id, tx, valid_time, deleted, product, title, subtitle, sku, description, quantity, price_currency_code, price_units, price_nanos,form, width, length, height, weight) VALUES (?, ?, ?, ?, CAST(? AS UUID), ?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS product_variant_form), ?, ?, ?, ?)");
        query.setParameter(1, productVariant.getId());
        query.setParameter(2, productVariant.getTx());
        query.setParameter(3, productVariant.getValidTime());
        query.setParameter(4, productVariant.isDeleted());
        query.setParameter(5, productVariant.getProduct());
        query.setParameter(6, productVariant.getTitle());
        query.setParameter(7, productVariant.getSubtitle());
        query.setParameter(8, productVariant.getSku());
        query.setParameter(9, productVariant.getDescription());
        query.setParameter(10, productVariant.getQuantity());
        query.setParameter(11, productVariant.getPriceCurrencyCode());
        query.setParameter(12, productVariant.getPriceUnits());
        query.setParameter(13, productVariant.getPriceNanos());
        query.setParameter(14, productVariant.getProductVariantForm().toString());
        query.setParameter(15, productVariant.getWidth());
        query.setParameter(16, productVariant.getLength());
        query.setParameter(17, productVariant.getHeight());
        query.setParameter(18, productVariant.getWeight());
        query.executeUpdate();
        et.commit();
    }
}