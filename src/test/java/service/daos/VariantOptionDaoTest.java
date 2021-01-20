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
import service.product.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: VariantOptionDaoTest.java, v 0.1 20210112 09.33 faisalrahman Exp $$
 */

@RunWith(JUnit4.class)
public class VariantOptionDaoTest {

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
    public void getVariantOptions_WithProductVariantId_returnListVariantOption() throws ServiceException {

        UUID fakeProductId =  UUID.fromString("9ef5602c-d67e-492c-9b91-aaf4a0fb08d3");
        UUID fakeIdCategory =  UUID.fromString("43bcc9ec-c5f0-433b-8a08-3874d0806eef");
        UUID fakeProductVariantId1 = UUID.fromString("d481b2e0-b152-436c-84e5-ee806239e190");

        provider.begin();
        provider.em().persist(new Log(10, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory, 10, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory, 10, new Date(),  false, "Electronics", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId, 10, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId, 10, new Date(),  false, fakeIdCategory, 30));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId1, 10, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId1,
                10,
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
        provider.em().persist(new VariantOptionTx(fakeProductVariantId1, "colour", 10,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantOption(fakeProductVariantId1, "colour", 10,  new Date(), false, "Blue"));
        provider.commit();

        VariantOptionDao variantOptionDao = new VariantOptionDao(entityManager);
        List<VariantOption> variantOptionList = variantOptionDao.getVariantOptions(fakeProductVariantId1);

        String actualValue = variantOptionList.stream().findFirst().get().getValue();
        String expectedValue = "Blue";

        Assert.assertEquals(actualValue, expectedValue);
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