/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.product;

/**
 * @author faisalrahman
 * @version $Id: CustomException.java, v 0.1 20210113 17.42 faisalrahman Exp $$
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}