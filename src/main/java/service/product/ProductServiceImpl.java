/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

import AWS.ALBGrpc;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tapp.product.*;

/**
 * @author faisalrahman
 * @version $Id: ProductServiceImpl.java, v 0.1 20201223 10.35 faisalrahman Exp $$
 */
public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public synchronized void getCategory(ID request, StreamObserver<Category> responseObserver) {

        logger.debug("getCategory");

        Category category = Category.newBuilder().build();
        responseObserver.onNext(category);
        responseObserver.onCompleted();
    }

    @Override
    public synchronized void listCategories(ListCategoriesRequest request, StreamObserver<Categories> responseObserver) {

        logger.debug("listCategories");

        Categories categories = Categories.newBuilder().build();
        responseObserver.onNext(categories);
        responseObserver.onCompleted();
    }

    @Override
    public synchronized void getProduct(ID request, StreamObserver<Product> responseObserver) {

        logger.debug("getProduct");

        Product product = Product.newBuilder().build();
        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }

    @Override
    public synchronized void listProducts(ListProductsRequest request, StreamObserver<Products> responseObserver) {

        logger.debug("listProducts");

        Products products = Products.newBuilder().build();
        responseObserver.onNext(products);
        responseObserver.onCompleted();
    }
}