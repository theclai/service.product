/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import service.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: CategoryDao.java, v 0.1 20210104 17.23 faisalrahman Exp $$
 */
public class CategoryDao implements Dao<Category> {

    private final EntityManager entityManager;

    public CategoryDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Category> get(UUID id) {

        Query query = entityManager.createNativeQuery("SELECT * FROM category_tx cx JOIN log l using(tx) JOIN category c using(id, tx) WHERE cx.id = ? AND c.deleted = 'f'", Category.class);
        query.setParameter(1, id);
        Category category = (Category) query.getResultList().stream().findFirst().orElse(null);

        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void update(Category category, String[] params) {

    }

    @Override
    public void delete(Category category) {

    }
}