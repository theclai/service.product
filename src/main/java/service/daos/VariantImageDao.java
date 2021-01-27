/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import io.grpc.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.entities.ProductVariant;
import service.entities.ProductVariantTx;
import service.entities.VariantImage;
import service.entities.VariantImageTx;
import service.product.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

        VariantImage variantImage;

        try {

            Query query = entityManager.createNativeQuery("SELECT * FROM variant_image_tx vit JOIN log l using(tx) JOIN variant_image vi using(id, tx) WHERE vit.id = ? AND vi.deleted = 'f'", VariantImage.class);
            query.setParameter(1, id);
            variantImage = (VariantImage) query.getResultList().stream().findFirst().orElse(null);

        }catch (Exception e){
            logger.error("Id {} error message: {}",id,  e.getMessage());
            throw new ServiceException(e.getMessage(), Status.INTERNAL);
        }

        return Optional.ofNullable(variantImage);
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

    public List<VariantImage> getVariantImages(List<UUID> productVariantIdList) throws ServiceException {

        List<VariantImage> variantImageList;

        try {

            Query query;

            if (productVariantIdList == null || productVariantIdList.isEmpty()) {

                query = entityManager.createNativeQuery("SELECT * FROM variant_image vi LEFT JOIN variant_image_tx vit ON (vi.id = vit.id) LEFT JOIN log l ON (vi.tx = l.tx AND vit.tx = l.tx) WHERE vi.deleted = 'f'", ProductVariant.class);

            } else {

                StringBuilder sb = new StringBuilder("SELECT * FROM variant_image vi LEFT JOIN variant_image_tx vit ON (vi.id = vit.id) LEFT JOIN log l ON (vi.tx = l.tx AND vit.tx = l.tx) WHERE vi.deleted = 'f' AND vi.variant IN ");
                sb.append("(");

                if (productVariantIdList.size() > 0) {

                    for (int i = 0; i < productVariantIdList.size(); i++) {
                        sb.append("?");
                        sb.append(",");
                    }

                    if (sb.length() > 0) {
                        sb.setLength(sb.length() - 1);
                    }

                    sb.append(")");
                }

                query = entityManager.createNativeQuery(sb.toString(), ProductVariant.class);

                for (int i = 0; i < productVariantIdList.size(); i++) {
                    query.setParameter(i + 1, productVariantIdList.get(i));
                }
            }

            variantImageList = query.getResultList();

        }catch (Exception e){
            logger.error("Error message: {}", e.getMessage());
            throw new ServiceException(e.getMessage(), Status.INTERNAL);
        }

        return variantImageList;
    }

    public Optional<VariantImageTx> getVariantImageTx(UUID id) throws ServiceException {

        VariantImageTx variantImageTx;

        try {

            Query query = entityManager.createNativeQuery("SELECT * FROM variant_image_tx vit JOIN log l using(tx) JOIN variant_image vi using(id, tx) WHERE vit.id = ? AND vi.deleted = 'f'", VariantImageTx.class);
            query.setParameter(1, id);
            variantImageTx = (VariantImageTx) query.getResultList().stream().findFirst().orElse(null);

        }catch (Exception e){
            logger.error("Error message: {}", e.getMessage());
            throw new ServiceException(e.getMessage(), Status.INTERNAL);
        }

        return Optional.ofNullable(variantImageTx);
    }
}