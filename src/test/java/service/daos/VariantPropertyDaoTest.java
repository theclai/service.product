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

        UUID fakeProductVariantId1 = UUID.fromString("5d123387-6d0f-48f4-aad4-21ffdd1a02c4");
        UUID fakeProductVariantId2  = UUID.fromString("fa3dcc02-9e11-4628-98b2-632b17a37969");

        provider.begin();
        provider.em().persist(new Log(11, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantPropertyTx(fakeProductVariantId1, "1", 11,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantProperty(fakeProductVariantId1, "1", 11,  new Date(), false, "Operating System", "Android Wear"));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantPropertyTx(fakeProductVariantId1, "2", 11,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantProperty(fakeProductVariantId1, "2", 11,  new Date(), false, "Compatible Operating System", "Android, iOS - Apple"));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantPropertyTx(fakeProductVariantId2, "1", 11,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantProperty(fakeProductVariantId2, "1", 11,  new Date(), false, "Type", "Type-C /Micro USB"));
        provider.commit();

        VariantPropertyDao variantPropertyDao = new VariantPropertyDao(entityManager);
        List<VariantProperty> variantPropertyList = variantPropertyDao.getVariantProperties(fakeProductVariantId1);

        String actualValue = variantPropertyList.stream().findFirst().get().getValue();
        String expectedValue = "Android Wear";

        Assert.assertEquals(actualValue, expectedValue);
        Assert.assertEquals(2, variantPropertyList.size());
    }
}