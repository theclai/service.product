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
import service.entities.CategoryTx;
import service.entities.Log;
import service.entities.VariantOption;
import service.entities.VariantOptionTx;

import javax.persistence.EntityManager;
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
    public void getVariantOptions_WithProductVariantId_returnListVariantOption(){

        UUID fakeProductVariantId1 = UUID.fromString("6dddb60a-0586-4d5c-802a-267979532c5c");
        UUID fakeProductVariantId2  = UUID.fromString("40af77c6-ef7b-4e93-9298-e9b367b0bc97");

        provider.begin();
        provider.em().persist(new Log(10, new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantOptionTx(fakeProductVariantId1, "colour", 10,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantOption(fakeProductVariantId1, "colour", 10,  new Date(), false, "Blue"));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantOptionTx(fakeProductVariantId2, "colour", 10,  new Date()));
        provider.commit();

        provider.begin();
        provider.em().persist(new VariantOption(fakeProductVariantId2, "colour", 10,  new Date(), false, "White"));
        provider.commit();

        VariantOptionDao variantOptionDao = new VariantOptionDao(entityManager);
        List<VariantOption> variantOptionList = variantOptionDao.getVariantOptions(fakeProductVariantId1);

        String actualValue = variantOptionList.stream().findFirst().get().getValue();
        String expectedValue = "Blue";

        Assert.assertEquals(actualValue, expectedValue);
        Assert.assertEquals(1, variantOptionList.size());
    }
}