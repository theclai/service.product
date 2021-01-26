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

        provider.begin();
        Query q1 = provider.em().createNativeQuery("DELETE FROM variant_option");
        Query q2 = provider.em().createNativeQuery("DELETE FROM variant_option_tx");
        Query q3 = provider.em().createNativeQuery("DELETE FROM variant_property");
        Query q4 = provider.em().createNativeQuery("DELETE FROM variant_property_tx");
        Query q5 = provider.em().createNativeQuery("DELETE FROM product_variant");
        Query q6 = provider.em().createNativeQuery("DELETE FROM product_variant_tx");
        Query q7 = provider.em().createNativeQuery("DELETE FROM product");
        Query q8 = provider.em().createNativeQuery("DELETE FROM product_tx");
        Query q9 = provider.em().createNativeQuery("DELETE FROM category");
        Query q10 = provider.em().createNativeQuery("DELETE FROM category_tx");
        Query q11 = provider.em().createNativeQuery("DELETE FROM log");

        q1.executeUpdate();
        q2.executeUpdate();
        q3.executeUpdate();
        q4.executeUpdate();
        q5.executeUpdate();
        q6.executeUpdate();
        q7.executeUpdate();
        q8.executeUpdate();
        q9.executeUpdate();
        q10.executeUpdate();
        q11.executeUpdate();
        provider.commit();
        entityManager.close();
    }

    @Test
    public void getVariantOptions_WithProductVariantId_returnListVariantOption() throws ServiceException {

        UUID fakeProductId =  UUID.fromString("9ef5602c-d67e-492c-9b91-aaf4a0fb08d3");
        UUID fakeIdCategory =  UUID.fromString("43bcc9ec-c5f0-433b-8a08-3874d0806eef");
        UUID fakeProductVariantId1 = UUID.fromString("d481b2e0-b152-436c-84e5-ee806239e190");
        UUID fakeImageId1 = UUID.fromString("768139f3-50a3-470b-a1c8-063b5dbcf0a6");

        Log logObj = new Log(10, new Date());
        CategoryTx categoryTxObj = new CategoryTx(fakeIdCategory, 10, new Date());
        Category categoryObj = new Category(fakeIdCategory, 10, new Date(),  false, "Electronics", null, null, null, fakeImageId1);
        ProductTx productTxObj = new ProductTx(fakeProductId, 10, new Date());
        Product productObj = new Product(fakeProductId, 10, new Date(),  false, fakeIdCategory, 30);
        ProductVariantTx productVariantTxObj = new ProductVariantTx(fakeProductVariantId1, 10, new Date());
        ProductVariant productVariantObj = new ProductVariant(
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
                ProductVariant.Form.physical, 10, 10, 20, 20, 20);
        VariantOptionTx variantOptionTxObj = new VariantOptionTx(fakeProductVariantId1, "colour", 10,  new Date());
        VariantOption variantOptionObj = new VariantOption(fakeProductVariantId1, "colour", 10,  new Date(), false, "Blue");

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

        provider.begin();
        provider.em().persist(productVariantTxObj);
        provider.commit();

        saveToProductVariant(productVariantObj);

        provider.begin();
        provider.em().persist(variantOptionTxObj);
        provider.commit();

        provider.begin();
        provider.em().persist(variantOptionObj);
        provider.commit();

        VariantOptionDao variantOptionDao = new VariantOptionDao(entityManager);
        List<VariantOption> variantOptionList = variantOptionDao.getVariantOptions(fakeProductVariantId1);

        String actualValue = variantOptionList.stream().findFirst().get().getValue();
        String expectedValue = "Blue";

        Assert.assertEquals(actualValue, expectedValue);
        Assert.assertEquals(variantOptionList.stream().findFirst().get().getVariant(), variantOptionObj.getVariant());
        Assert.assertEquals(variantOptionList.stream().findFirst().get().getId(), variantOptionObj.getId());
        Assert.assertEquals(variantOptionList.stream().findFirst().get().getTx(), variantOptionObj.getTx());
        Assert.assertEquals(variantOptionList.stream().findFirst().get().getValidTime(), variantOptionObj.getValidTime());
        Assert.assertEquals(variantOptionList.stream().findFirst().get().isDeleted(), variantOptionObj.isDeleted());
        Assert.assertEquals(variantOptionList.stream().findFirst().get().getValue(), variantOptionObj.getValue());
    }

    private void saveToProductVariant(ProductVariant productVariant) {

        EntityManager em = provider.em();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createNativeQuery("INSERT INTO product_variant (id, tx, valid_time, deleted, product, title, subtitle, sku, description, quantity, price_currency_code, price_units, price_nanos,form, width, length, height, weight, order_weight) VALUES (?, ?, ?, ?, CAST(? AS UUID), ?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS product_variant_form), ?, ?, ?, ?, ?)");
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
        query.setParameter(19, productVariant.getOrderWeight());
        query.executeUpdate();
        et.commit();
    }
}