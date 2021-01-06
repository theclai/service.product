/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import service.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: ProductDao.java, v 0.1 20210106 16.38 faisalrahman Exp $$
 */
public class ProductDao implements Dao<Product>{

    private final EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Product> get(UUID id) {

        Query query = entityManager.createNativeQuery("SELECT * FROM product_tx px JOIN log l using(tx) JOIN product p using(id, tx) WHERE px.id = ? AND c.deleted = 'f'", Product.class);
        query.setParameter(1, id);
        Product product = (Product) query.getResultList().stream().findFirst().orElse(null);

        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void update(Product product, String[] params) {

    }

    @Override
    public void delete(Product product) {

    }
}