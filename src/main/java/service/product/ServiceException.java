/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

/**
 * @author faisalrahman
 * @version $Id: ServiceException.java, v 0.1 20201228 15.37 faisalrahman Exp $$
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 6296801746437496685L;

    private final io.grpc.Status code;

    public ServiceException(io.grpc.Status code) {
        super();
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, io.grpc.Status code) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(String message, io.grpc.Status code) {
        super(message);
        this.code = code;
    }

    public ServiceException(Throwable cause, io.grpc.Status code) {
        super(cause);
        this.code = code;
    }

    public io.grpc.Status getCode() {
        return this.code;
    }
}