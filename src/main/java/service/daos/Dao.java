/**
 * Alipay.com Inc.
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.daos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: Dao.java, v 0.1 20210104 17.16 faisalrahman Exp $$
 */
public interface Dao<T> {

    Optional<T> get(UUID id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}