/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import service.entities.VariantOption;
import service.entities.VariantProperty;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: VariantPropertyDao.java, v 0.1 20210112 11.43 faisalrahman Exp $$
 */
public class VariantPropertyDao implements Dao<VariantProperty> {

    private final EntityManager entityManager;

    public VariantPropertyDao(EntityManager entityManager) { this.entityManager = entityManager; }

    @Override
    public Optional<VariantProperty> get(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<VariantProperty> getAll() {
        return null;
    }

    @Override
    public void save(VariantProperty variantProperty) {

    }

    @Override
    public void update(VariantProperty variantProperty, String[] params) {

    }

    @Override
    public void delete(VariantProperty variantProperty) {

    }

    public List<VariantProperty> getVariantProperties(UUID productVariantId){

        Query query = entityManager.createNativeQuery("SELECT * FROM variant_property vp LEFT OUTER JOIN variant_property_tx vpt ON (vp.variant = vpt.variant and vp.id = vpt.id) LEFT OUTER JOIN log l ON (vp.tx = l.tx) WHERE vp.deleted = 'f' and vp.variant = ?", VariantProperty.class);
        query.setParameter(1, productVariantId);
        List<VariantProperty> variantPropertyList = query.getResultList();

        return variantPropertyList;
    }
}