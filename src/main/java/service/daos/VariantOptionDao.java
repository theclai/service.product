/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import service.entities.VariantOption;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: ProductVariantOption.java, v 0.1 20210111 15.30 faisalrahman Exp $$
 */
public class VariantOptionDao implements Dao<VariantOption> {

    private final EntityManager entityManager;

    public VariantOptionDao(EntityManager entityManager) { this.entityManager = entityManager; }

    @Override
    public Optional<VariantOption> get(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<VariantOption> getAll() {
        return null;
    }

    @Override
    public void save(VariantOption variantOption) {

    }

    @Override
    public void update(VariantOption variantOption, String[] params) {

    }

    @Override
    public void delete(VariantOption variantOption) {

    }

    public List<VariantOption> getVariantOptions(UUID productVariantId){

        Query query = entityManager.createNativeQuery("SELECT * FROM variant_option vo LEFT OUTER JOIN variant_option_tx vot ON (vo.variant = vot.variant) LEFT OUTER JOIN log l ON (vo.tx = l.tx) WHERE vo.deleted = 'f' and vo.variant = ?", VariantOption.class);
        query.setParameter(1, productVariantId);

        List<VariantOption> variantOptionList = query.getResultList();

        return variantOptionList;
    }
}