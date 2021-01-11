/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import service.entities.Product;
import service.entities.ProductVariant;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import service.entities.ProductVariant;

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

    public List<ProductVariant> getProductVariant(List<UUID> productIdList) {

        List<ProductVariant> productVariantList = new ArrayList<>();
        Query query;

        if (productIdList == null || productIdList.isEmpty()) {

            query = entityManager.createNativeQuery("SELECT * FROM product_variant pv LEFT JOIN  product_variant_tx pvt using(id,tx) JOIN log l using(tx) WHERE pv.deleted = 'f'", ProductVariant.class);

        } else {

            StringBuilder sb = new StringBuilder("SELECT * FROM product_variant pv LEFT JOIN  product_variant_tx pvt using(id,tx) JOIN log l using(tx) WHERE pv.deleted = 'f' AND pv.product IN ");
            sb.append("(");

            if (productIdList.size() > 0) {

                for (int i = 0; i < productIdList.size(); i++) {
                    sb.append("?");
                    sb.append(",");
                }

                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                }

                sb.append(")");
            }

            query = entityManager.createNativeQuery(sb.toString(), ProductVariant.class);

            for (int i = 0; i < productIdList.size(); i++) {
                query.setParameter(i + 1, productIdList.get(i));
            }
        }

        productVariantList = query.getResultList();

        return productVariantList;
    }
}