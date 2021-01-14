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
import java.util.*;

/**
 * @author faisalrahman
 * @version $Id: ProductVariantDaoTest.java, v 0.1 20210107 17.06 faisalrahman Exp $$
 */

@RunWith(JUnit4.class)
public class ProductVariantDaoTest {

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
    public void getProductVariant_withUUid_returnProductVariant(){

        UUID fakeProductVariantId =  UUID.fromString("e10389d4-3273-4d35-86e2-95a44b829bd7");
        UUID fakeProductId =  UUID.fromString("fab45351-7380-49a7-902d-5cf5adf7f4b6");
        UUID fakeIdCategory =  UUID.fromString("4d8224e6-872a-46ae-b27d-9c4997a917db");

        provider.begin();
        provider.em().persist(new Log(3, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory, 3, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory, 3, new Date(),  false, "Electronics", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId, 3, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId, 3, new Date(),  false, fakeIdCategory, 30));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId, 3, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId,
                3,
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
                ProductVariant.Form.physical,
                10,10,20,20));

        ProductVariantDao productVariantDao = new ProductVariantDao(entityManager);
        UUID id = fakeProductVariantId;

        Optional<ProductVariant> productVariantValue = productVariantDao.get(id);
        String actualValue = productVariantValue.get().getTitle();
        String expectedValue = "Fitness bracelet";

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getProductVariants_withEmptyProductIdList_returnAllProductVariantList(){

        UUID fakeProductVariantId1 =  UUID.fromString("effdebe5-14f5-4cea-982d-d4b0618baa55");
        UUID fakeProductVariantId2 =  UUID.fromString("a920f722-3c42-40a6-8aee-d799e827be19");
        UUID fakeProductVariantId3 =  UUID.fromString("d554bb08-4d5a-4058-9624-8eef40e917f3");
        UUID fakeProductId =  UUID.fromString("58aae1ff-1625-4482-ab11-3efce4113106");
        UUID fakeIdCategory =  UUID.fromString("a0b3830f-477d-411f-9705-604de7700676");

        provider.begin();
        provider.em().persist(new Log(8, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory, 8, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory, 8, new Date(),  false, "Electronics", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId, 8, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId, 8, new Date(),  false, fakeIdCategory, 30));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId1, 8, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId1,
                8,
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
        provider.em().persist(new ProductVariantTx(fakeProductVariantId2, 8, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId2,
                8,
                new Date(),
                false,
                "Headset with box",
                null,
                "Headset with box",
                null,
                fakeProductId,
                20,
                "IDR",
                24500,
                0,
                ProductVariant.Form.physical, 20, 20, 15, 15));

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId3, 8, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId3,
                8,
                new Date(),
                false,
                "USB Type-C Cable",
                null,
                "usb-1",
                null,
                fakeProductId,
                30,
                "IDR",
                26000,
                0,
                ProductVariant.Form.physical, 5, 5, 10, 10));

        List<UUID> productIdList = new ArrayList<>();
        ProductVariantDao productVariantDao = new ProductVariantDao(entityManager);
        List<ProductVariant> productVariantList = productVariantDao.getProductVariants(productIdList);

        long actualValue = productVariantList.stream().count();

        Assert.assertNotEquals(actualValue, 0);
    }

    @Test
    public void getProductVariants_withProductIdList_returnProductVariantListByProductId(){

        UUID fakeProductVariantId1 =  UUID.fromString("a5e64526-7363-45bc-9ece-29d0f72a00e3");
        UUID fakeProductVariantId2 =  UUID.fromString("c747c907-0058-4769-8377-d2aae4ad178c");
        UUID fakeProductVariantId3 =  UUID.fromString("b23cb307-2a92-45a1-95a7-3293e6371073");
        UUID fakeProductId =  UUID.fromString("dc50f255-39c8-4e7f-9044-1030068a50d6");
        UUID fakeIdCategory =  UUID.fromString("fca8f771-c2b9-4371-ac9b-f258016cd21a");

        provider.begin();
        provider.em().persist(new Log(9, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new CategoryTx(fakeIdCategory, 9, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Category(fakeIdCategory, 9, new Date(),  false, "Electronics", null, null, null));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductTx(fakeProductId, 9, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new Product(fakeProductId, 9, new Date(),  false, fakeIdCategory, 30));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId1, 9, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId1,
                9,
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
        provider.em().persist(new ProductVariantTx(fakeProductVariantId2, 9, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId2,
                9,
                new Date(),
                false,
                "Headset with box",
                null,
                "Headset with box",
                null,
                fakeProductId,
                20,
                "IDR",
                24500,
                0,
                ProductVariant.Form.physical, 2, 2, 5, 5));

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId3, 9, new Date()));
        provider.commit();

        saveToProductVariant(new ProductVariant(
                fakeProductVariantId3,
                9,
                new Date(),
                false,
                "USB Type-C Cable",
                null,
                "usb-1",
                null,
                null,
                30,
                "IDR",
                26000,
                0,
                ProductVariant.Form.physical, 15, 15, 20, 20));

        List<UUID> productIdList = new ArrayList<>();
        productIdList.add(fakeProductId);

        ProductVariantDao productVariantDao = new ProductVariantDao(entityManager);
        List<ProductVariant> productVariantList = productVariantDao.getProductVariants(productIdList);

        long expectedValue = 2;
        long actualValue = productVariantList.stream().count();

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