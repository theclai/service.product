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
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: VariantImageDaoTest.java, v 0.1 20210126 09.45 faisalrahman Exp $$
 */
@RunWith(JUnit4.class)
public class VariantImageDaoTest {

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
        Query q1 = provider.em().createNativeQuery("DELETE FROM variant_image");
        Query q2 = provider.em().createNativeQuery("DELETE FROM variant_image_tx");
        Query q3 = provider.em().createNativeQuery("DELETE FROM log");

        q1.executeUpdate();
        q2.executeUpdate();
        q3.executeUpdate();

        provider.commit();
        entityManager.close();
    }

    @Test
    public void getVariantImage_withUUId_returnVariantImage() throws ServiceException {

        UUID fakeImageId = UUID.fromString("26c8f90c-35d7-4d18-99c1-ef6b6a4bd703");
//        UUID fakeProductVariantId1 =  UUID.fromString("a5e64526-7363-45bc-9ece-29d0f72a00e3");
        Log logObj = new Log(0, new Date());
        VariantImageTx variantImageTxObj = new VariantImageTx(fakeImageId, 0, new Date());
        VariantImage variantImageObj = new VariantImage(fakeImageId, 0, new Date(), false, null, 10);

       provider.begin();
       provider.em().persist(logObj);
       provider.commit();

//        provider.begin();
//        provider.em().persist(new ProductVariantTx(fakeProductVariantId1, 9, new Date()));
//        provider.commit();
//
//        saveToProductVariant(new ProductVariant(
//                fakeProductVariantId1,
//                9,
//                new Date(),
//                false,
//                "Fitness bracelet",
//                null,
//                "fitness-1",
//                null,
//                fakeProductId,
//                10,
//                "IDR",
//                1068051,
//                0,
//                ProductVariant.Form.physical, 10, 10, 20, 20, 10));

       provider.begin();
       provider.em().persist(variantImageTxObj);
       provider.commit();

       provider.begin();
       provider.em().persist(variantImageObj);
       provider.commit();

       VariantImageDao variantImageDao = new VariantImageDao(entityManager);
       Optional<VariantImage> variantImageValue = variantImageDao.get(fakeImageId);
       UUID actualValue = variantImageValue.get().getId();
       UUID expectedValue = fakeImageId;

        Assert.assertEquals(actualValue, expectedValue);
        Assert.assertEquals(variantImageValue.get().getId(), variantImageObj.getId());
        Assert.assertEquals(variantImageValue.get().getTx(), variantImageObj.getTx());
        Assert.assertEquals(variantImageValue.get().getValidTime(), variantImageObj.getValidTime());
        Assert.assertEquals(variantImageValue.get().isDeleted(), variantImageObj.isDeleted());
        Assert.assertEquals(variantImageValue.get().getVariant(), variantImageObj.getVariant());
        Assert.assertEquals(variantImageValue.get().getOrderWeight(), variantImageObj.getOrderWeight());
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

