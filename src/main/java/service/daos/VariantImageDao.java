/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.entities.ProductVariant;
import service.entities.VariantImage;
import service.product.ServiceException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: VariantImageDao.java, v 0.1 20210126 09.07 faisalrahman Exp $$
 */
public class VariantImageDao implements Dao<VariantImage>{

    private static final Logger logger = LoggerFactory.getLogger(ProductVariantDao.class);
    private final EntityManager entityManager;

    public VariantImageDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<VariantImage> get(UUID id) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public List<VariantImage> getAll() {
        return null;
    }

    @Override
    public void save(VariantImage variantImage) {

    }

    @Override
    public void update(VariantImage variantImage, String[] params) {

    }

    @Override
    public void delete(VariantImage variantImage) {

    }
}