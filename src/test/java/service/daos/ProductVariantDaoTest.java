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

        provider.begin();
        provider.em().persist(new ProductVariant(
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

        provider.commit();

        ProductVariantDao productVariantDao = new ProductVariantDao(entityManager);
        UUID id = fakeProductVariantId;

        Optional<ProductVariant> productVariantValue = productVariantDao.get(id);
        String actualValue = productVariantValue.get().getTitle();
        String expectedValue = "Fitness bracelet";

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getProductVariants_withEmptyProductIdList_returnAllProductVariantList(){

        UUID fakeProductVariantId1 =  UUID.fromString("8fd5371f-55ea-49e6-bb5d-69745a0e464b");
        UUID fakeProductVariantId2 =  UUID.fromString("1c93a06e-dd43-4278-9cd4-f941593624ad");
        UUID fakeProductVariantId3 =  UUID.fromString("574064b6-ec66-432c-91d7-479dc8b1506b");
        UUID fakeProductId =  UUID.fromString("059c653c-bf8f-45ab-84f2-149688aff2f2");
        UUID fakeIdCategory =  UUID.fromString("0602eb59-43ec-4616-a25f-dc910e04b363");

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

        provider.begin();
        provider.em().persist(new ProductVariant(
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

        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId2, 8, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariant(
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

        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId3, 7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariant(
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

        provider.commit();

        List<UUID> productIdList = new ArrayList<>();

        ProductVariantDao productVariantDao = new ProductVariantDao(entityManager);
        List<ProductVariant> productVariantList = productVariantDao.getProductVariants(productIdList);

        long expectedValue = 3;
        long actualValue = productVariantList.stream().count();

        Assert.assertEquals(actualValue, expectedValue);
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

        provider.begin();
        provider.em().persist(new ProductVariant(
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

        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId2, 7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariant(
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

        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariantTx(fakeProductVariantId3, 7, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new ProductVariant(
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

        provider.commit();

        List<UUID> productIdList = new ArrayList<>();
        productIdList.add(fakeProductId);

        ProductVariantDao productVariantDao = new ProductVariantDao(entityManager);
        List<ProductVariant> productVariantList = productVariantDao.getProductVariants(productIdList);

        long expectedValue = 2;
        long actualValue = productVariantList.stream().count();

        Assert.assertEquals(actualValue, expectedValue);
    }
}