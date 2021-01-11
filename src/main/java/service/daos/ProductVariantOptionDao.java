/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import service.entities.ProductVariant;
import service.entities.ProductVariantOption;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: ProductVariantOption.java, v 0.1 20210111 15.30 faisalrahman Exp $$
 */
public class ProductVariantOptionDao implements Dao<ProductVariantOption> {

    private final EntityManager entityManager;

    public ProductVariantOptionDao(EntityManager entityManager) { this.entityManager = entityManager; }

    @Override
    public Optional<ProductVariantOption> get(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<ProductVariantOption> getAll() {
        return null;
    }

    @Override
    public void save(ProductVariantOption productVariantOption) {

    }

    @Override
    public void update(ProductVariantOption productVariantOption, String[] params) {

    }

    @Override
    public void delete(ProductVariantOption productVariantOption) {

    }
}