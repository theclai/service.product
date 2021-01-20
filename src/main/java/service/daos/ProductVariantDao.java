/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import io.grpc.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import service.entities.ProductVariant;
import service.product.ServiceException;

/**
 * @author faisalrahman
 * @version $Id: ProductVariantDao.java, v 0.1 20210106 18.20 faisalrahman Exp $$
 */
public class ProductVariantDao implements Dao<ProductVariant> {

    private static final Logger logger = LoggerFactory.getLogger(ProductVariantDao.class);
    private final EntityManager entityManager;

    public ProductVariantDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Optional<ProductVariant> get(UUID id) throws ServiceException {

        ProductVariant productVariant;

        try {

            Query query = entityManager.createNativeQuery("SELECT * FROM product_variant_tx pvx JOIN log l using(tx) JOIN product_variant pv using(id, tx) WHERE pvx.id = ? AND pv.deleted = 'f'", ProductVariant.class);
            query.setParameter(1, id);
            productVariant = (ProductVariant) query.getResultList().stream().findFirst().orElse(null);

        }catch (Exception e){
            logger.error("Id {} error message: {}",id,  e.getMessage());
            throw new ServiceException(e.getMessage(), Status.INTERNAL);
        }

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

    public List<ProductVariant> getProductVariants(List<UUID> productIdList) throws ServiceException {

        List<ProductVariant> productVariantList;

        try {

            Query query;

            if (productIdList == null || productIdList.isEmpty()) {

                query = entityManager.createNativeQuery("SELECT * FROM product_variant pv LEFT JOIN product_variant_tx pvt ON (pv.id = pvt.id) LEFT JOIN log l ON (pv.tx = l.tx AND pvt.tx = l.tx) WHERE pv.deleted = 'f'", ProductVariant.class);

            } else {

                StringBuilder sb = new StringBuilder("SELECT * FROM product_variant pv LEFT JOIN product_variant_tx pvt ON (pv.id = pvt.id) LEFT JOIN log l ON (pv.tx = l.tx AND pvt.tx = l.tx) WHERE pv.deleted = 'f' AND pv.product IN ");
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

        }catch (Exception e){
            logger.error("Error message: {}", e.getMessage());
            throw new ServiceException(e.getMessage(), Status.INTERNAL);
        }

        return productVariantList;
    }

    public Optional<ProductVariantTx> getProductVariantTx(UUID id) throws ServiceException {

        ProductVariantTx productVariantTx;

        try {

            Query query = entityManager.createNativeQuery("SELECT * FROM product_variant_tx pvx JOIN log l using(tx) JOIN product_variant pv using(id, tx) WHERE pvx.id = ? AND pv.deleted = 'f'", ProductVariantTx.class);
            query.setParameter(1, id);
            productVariantTx = (ProductVariantTx) query.getResultList().stream().findFirst().orElse(null);

        }catch (Exception e){
            logger.error("Error message: {}", e.getMessage());
            throw new ServiceException(e.getMessage(), Status.INTERNAL);
        }

        return Optional.ofNullable(productVariantTx);
    }
}