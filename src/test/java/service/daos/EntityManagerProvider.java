/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Map;

/**
 * @author faisalrahman
 * @version $Id: EntityManagerProvider.java, v 0.1 20210104 20.58 faisalrahman Exp $$
 */
public class EntityManagerProvider implements TestRule {

    private EntityManager em;
    private EntityTransaction tx;

    private EntityManagerProvider(String unitName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    public EntityManagerProvider(String unitName, Map properties) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName, properties);
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    public static EntityManagerProvider withUnit(String unitName) {
        return new EntityManagerProvider(unitName);
    }

    public static EntityManagerProvider withUnit(String unitName, Map properties) {
        return new EntityManagerProvider(unitName, properties);
    }

    public void begin() {
        this.tx.begin();
    }

    public void commit() {
        this.tx.commit();
    }

    public EntityTransaction tx() {
        return this.tx;
    }

    public EntityManager em() {
        return this.em;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
                em.clear();
            }

        };
    }

}