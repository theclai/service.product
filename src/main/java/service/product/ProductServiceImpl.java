/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

import AWS.ALBGrpc;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tapp.product.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author faisalrahman
 * @version $Id: ProductServiceImpl.java, v 0.1 20201223 10.35 faisalrahman Exp $$
 */
public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public synchronized void getCategory(ID request, StreamObserver<Category> responseObserver) throws ParseException {

        logger.debug("getCategory");

        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        Instant time = date.toInstant();

        Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();

        Date dateValid = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        Instant instantValid = dateValid.toInstant();

        Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                .setNanos(instantValid.getNano()).build();

        Category category = Category
                .newBuilder()
                .setId("//product.tapp/Category/9a0e4932-44be-11eb-b378-0242ac130002")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setTitle("Physical Goods")
                .setSubtitle("")
                .setDescription("Electronics and accessories delivered to your door")
                .setParent("")
                .setImage("//image.tapp/Image/4e2e94d7-016a-4fd6-812d-3baa544e4e17")
                .build();
        responseObserver.onNext(category);
        responseObserver.onCompleted();
    }

    @Override
    public synchronized void listCategories(ListCategoriesRequest request, StreamObserver<Categories> responseObserver) throws ParseException {

        logger.debug("listCategories");
        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        Instant time = date.toInstant();

        Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();

        Date dateValid = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        Instant instantValid = dateValid.toInstant();

        Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                .setNanos(instantValid.getNano()).build();

        Category category1 = Category
                .newBuilder()
                .setId("//product.tapp/Category/9a0e4932-44be-11eb-b378-0242ac130002")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setTitle("Physical Goods")
                .setSubtitle("")
                .setDescription("Electronics and accessories delivered to your door")
                .setParent("")
                .setImage("//image.tapp/Image/4e2e94d7-016a-4fd6-812d-3baa544e4e17")
                .build();

        Category category2 = Category
                .newBuilder()
                .setId("//product.tapp/Category/3dcd405f-d0b5-4841-b9f2-c1ef6394d989")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setTitle("Electronics")
                .setSubtitle("")
                .setDescription("")
                .setParent("//product.tapp/Category/9a0e4932-44be-11eb-b378-0242ac130002")
                .setImage("//image.tapp/Image/713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd")
                .build();

        Category category3 = Category
                .newBuilder()
                .setId("//product.tapp/Category/713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setTitle("Phone Accessories")
                .setSubtitle("")
                .setDescription("")
                .setParent("//product.tapp/Category/9a0e4932-44be-11eb-b378-0242ac130002")
                .setImage("//image.tapp/Image/82977ed3-0077-4d0f-b6a1-bdfc018693b0")
                .build();

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);

        Categories categories = Categories
                .newBuilder()
                .addAllNodes(categoryList)
                .build();

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

    @Override
    public void getProductVariant(ID request, StreamObserver<ProductVariant> responseObserver) {

        logger.debug("getProductVariant");

        ProductVariant productVariant = ProductVariant.newBuilder().build();
        responseObserver.onNext(productVariant);
        responseObserver.onCompleted();
    }

    @Override
    public void listProductVariants(ListProductVariantsRequest request, StreamObserver<ProductVariants> responseObserver) {

        logger.debug("listProductVariants");

        ProductVariants productVariants = ProductVariants.newBuilder().build();
        responseObserver.onNext(productVariants);
        responseObserver.onCompleted();
    }
}