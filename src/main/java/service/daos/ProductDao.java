/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import service.entities.Product;
import service.entities.Category;
import service.entities.ProductTx;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

/**
 * @author faisalrahman
 * @version $Id: ProductDao.java, v 0.1 20210106 16.38 faisalrahman Exp $$
 */
public class ProductDao implements Dao<Product> {

    private final EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Product> get(UUID id) {

        Query query = entityManager.createNativeQuery("SELECT * FROM product_tx px JOIN log l using(tx) JOIN product p using(id, tx) WHERE px.id = ? AND p.deleted = 'f'", Product.class);
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

    public List<Product> getProductList(List<UUID> categoryList) {

        Query query;
        List<Product> productList = new ArrayList<>();

        if (categoryList == null || categoryList.isEmpty()) {

            query = entityManager.createNativeQuery("SELECT * FROM product_tx px JOIN log l using(tx) JOIN product p using(id, tx) WHERE p.deleted = 'f'", Product.class);

        } else {

            StringBuilder sb = new StringBuilder("SELECT * FROM product_tx px JOIN log l using(tx) JOIN product p using(id, tx) WHERE p.deleted = 'f' AND p.category IN ");
            sb.append("(");

            if (categoryList.size() > 0) {

                for (int i = 0; i < categoryList.size(); i++) {
                    sb.append("?");
                    sb.append(",");
                }

                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                }

                sb.append(")");
            }

            query = entityManager.createNativeQuery(sb.toString(), Product.class);

            for (int i = 0; i < categoryList.size(); i++) {
                query.setParameter(i + 1, categoryList.get(i));
            }
        }

        productList = query.getResultList();

        return productList;
    }

    public Optional<ProductTx> getProductTx(UUID id) {

        Query query = entityManager.createNativeQuery("SELECT * FROM product_tx px JOIN log l using(tx) JOIN product p using(id, tx) WHERE px.id = ? AND p.deleted = 'f'", ProductTx.class);
        query.setParameter(1, id);
        ProductTx productTx = (ProductTx) query.getResultList().stream().findFirst().orElse(null);

        return Optional.ofNullable(productTx);
    }
}