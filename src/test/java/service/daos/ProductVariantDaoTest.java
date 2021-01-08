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
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
                new ProductMoney("IDR",1068051),
                ProductVariant.Form.physical));

        provider.commit();

        ProductVariantDao productVariantDao = new ProductVariantDao(entityManager);
        UUID id = fakeProductVariantId;

        Optional<ProductVariant> productVariantValue = productVariantDao.get(id);
        String actualValue = productVariantValue.get().getTitle();
        String expectedValue = "Fitness bracelet";

        Assert.assertEquals(actualValue, expectedValue);

    }
}