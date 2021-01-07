/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import service.entities.Product;
import tapp.product.ProductVariant;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: ProductVariantDao.java, v 0.1 20210106 18.20 faisalrahman Exp $$
 */
public class ProductVariantDao implements Dao<ProductVariant> {

    private final EntityManager entityManager;

    public ProductVariantDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Optional<ProductVariant> get(UUID id) {

        Query query = entityManager.createNativeQuery("SELECT * FROM product_variant_tx pvx JOIN log l using(tx) JOIN product_variant pv using(id, tx) WHERE pvx.id = ? AND pv.deleted = 'f'", ProductVariant.class);
        query.setParameter(1, id);
        ProductVariant productVariant = (ProductVariant) query.getResultList().stream().findFirst().orElse(null);

        return Optional.ofNullable(productVariant);
    }

    @Override
    public List<ProductVariant> getAll() {
        return null;
    }

    @Override
    public void save(ProductVariant productVariant) {

    }

    @Override
    public void update(ProductVariant productVariant, String[] params) {

    }

    @Override
    public void delete(ProductVariant productVariant) {

    }
}