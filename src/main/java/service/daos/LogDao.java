/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import io.grpc.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.entities.Category;
import service.entities.Log;
import service.product.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: LogDao.java, v 0.1 20210120 11.30 faisalrahman Exp $$
 */
public class LogDao {

    private static final Logger logger = LoggerFactory.getLogger(LogDao.class);
    private final EntityManager entityManager;

    public LogDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Log> getLog(int tx) throws ServiceException {

        Log log = new Log();

        try{

            Query query = entityManager.createNativeQuery("SELECT * FROM log l WHERE l.tx = ? ", Log.class);
            query.setParameter(1, tx);
            log = (Log) query.getResultList().stream().findFirst().orElse(null);

        }catch (Exception e){
            logger.error("Id {} error message: {}",tx,  e.getMessage());
            throw new ServiceException(e.getMessage(), Status.INTERNAL);
        }

        return Optional.ofNullable(log);
    }
}