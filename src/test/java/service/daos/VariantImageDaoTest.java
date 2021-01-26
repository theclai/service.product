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
        Log logObj = new Log(0, new Date());
        VariantImageTx variantImageTxObj = new VariantImageTx(fakeImageId, 0, new Date());
        VariantImage variantImageObj = new VariantImage(fakeImageId, 0, new Date(), false, null, 10);

       provider.begin();
       provider.em().persist(logObj);
       provider.commit();

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
}

