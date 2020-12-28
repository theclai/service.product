/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

import AWS.ALBGrpc;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import com.google.type.Money;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tapp.product.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * @author faisalrahman
 * @version $Id: ProductServiceImpl.java, v 0.1 20201223 10.35 faisalrahman Exp $$
 */
public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public synchronized void getCategory(ID request, StreamObserver<Category> responseObserver) {

        logger.debug("getCategory");

        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
        Instant time = date.toInstant();

        Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();

        Date dateValid = null;
        try {
            dateValid = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
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
    public synchronized void listCategories(ListCategoriesRequest request, StreamObserver<Categories> responseObserver)  {

        logger.debug("listCategories");
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
        Instant time = date.toInstant();

        Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();

        Date dateValid = null;
        try {
            dateValid = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
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

        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
        Instant time = date.toInstant();

        Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();

        Date dateValid = null;
        try {
            dateValid = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
        Instant instantValid = dateValid.toInstant();

        Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                .setNanos(instantValid.getNano()).build();

        Product product = Product
                .newBuilder()
                .setId("//product.tapp/Product/9a0e4932-44be-11eb-b378-0242ac130002")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setQuantity(10)
                .setDefaultVariant(ProductVariant.newBuilder().build())
                .setCategory("//product.tapp/Category/3dcd405f-d0b5-4841-b9f2-c1ef6394d989")
                .build();

        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }

    @Override
    public synchronized void listProducts(ListProductsRequest request, StreamObserver<Products> responseObserver) {

        logger.debug("listProducts");

        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
        Instant time = date.toInstant();

        Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();

        Date dateValid = null;
        try {
            dateValid = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
        Instant instantValid = dateValid.toInstant();

        Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                .setNanos(instantValid.getNano()).build();

        Product product1 = Product
                .newBuilder()
                .setId("//product.tapp/Product/9a0e4932-44be-11eb-b378-0242ac130002")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setQuantity(10)
                .setDefaultVariant(ProductVariant.newBuilder().build())
                .setCategory("//product.tapp/Category/3dcd405f-d0b5-4841-b9f2-c1ef6394d989")
                .build();

        Product product2 = Product
                .newBuilder()
                .setId("//product.tapp/Product/df659673-dc85-49bc-8af6-f7497275a064")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setQuantity(0)
                .setDefaultVariant(ProductVariant.newBuilder().build())
                .setCategory("//product.tapp/Category/713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd")
                .build();

        Product product3 = Product
                .newBuilder()
                .setId("//product.tapp/Product/19ef2f61-7ceb-4a69-a423-cdd513688e94")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setQuantity(3)
                .setDefaultVariant(ProductVariant.newBuilder().build())
                .setCategory("//product.tapp/Category/3dcd405f-d0b5-4841-b9f2-c1ef6394d989")
                .build();


        Map<String,String> colors4 = new HashMap<>();
        colors4.put("blue","blue");
        colors4.put("white","white");
        colors4.put("red","red");

        ProductVariant productVariant4 = ProductVariant.newBuilder().putAllOptions(colors4).build();
        Product product4 = Product
                .newBuilder()
                .setId("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setQuantity(56)
                .setDefaultVariant(productVariant4)
                .setCategory("//product.tapp/Category/3dcd405f-d0b5-4841-b9f2-c1ef6394d989")
                .build();

        Map<String,String> colors5 = new HashMap<>();
        colors4.put("black","black");
        colors4.put("pink","pink");
        colors4.put("white","white");
        colors4.put("red","red");
        colors4.put("blue","blue");
        colors4.put("cartoon","cartoon");

        ProductVariant productVariant5 = ProductVariant.newBuilder().putAllOptions(colors5).build();

        Product product5 = Product
                .newBuilder()
                .setId("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setQuantity(143)
                .setDefaultVariant(productVariant5)
                .setCategory("//product.tapp/Category/713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd")
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);

        Products products = Products
                .newBuilder()
                .addAllNodes(productList)
                .build();

        responseObserver.onNext(products);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductVariant(ID request, StreamObserver<ProductVariant> responseObserver) {

        logger.debug("getProductVariant");

        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
        Instant time = date.toInstant();

        Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();

        Date dateValid = null;
        try {
            dateValid = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2020-12-28T02:46:18Z");
        } catch (ParseException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
        Instant instantValid = dateValid.toInstant();

        Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                .setNanos(instantValid.getNano()).build();

        Map<String, String> properties = new HashMap<>();
        properties.put("Operating System", "Android Wear");
        properties.put("Compatible Operating System", "Android, iOS - Apple");
        properties.put("Model","Smart Bracelet");
        properties.put("Band Material","Silicone");
        properties.put("Series","C55");
        properties.put("Features","Blood Pressure Monitor, Bluetooth Enabled, Waterproof");
        properties.put("Case Material","Aluminium, Ceramic, Metal");
        properties.put("Item type","smart watch");
        properties.put("Screen size",".1.54 HD IPS, 240*240 Touch screen 2.5D fox surface capacitive full-fit touch screen");
        properties.put("CPU chip","MTK2502");
        properties.put("RAM/ROM","34M/128M");
        properties.put("Phone version require","Android 5.0 and above ,IOS 9.0 and above");
        properties.put("Waterproof","IP67");
        properties.put("Packing list","1 x Smart watch  1 x Charger  1 x Retail package  1 x User manual");

        ProductVariant productVariant = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/9a0e4932-44be-11eb-b378-0242ac130002")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("")
                .setDescription("Heart rate monitor ,sleep monitor,blood pressure,bluetooth call and message reminder,remote music/camera.Alarm clock, calendar, stopwatch. Language:English, German, Spanish, Italian, French, Portuguese (Brazil), Russian, Indonesian, Czech, Arabic, Thai, Dutch, Polish, Turkish, Persian, Hebrew,Malaysian, Vietnamese, Greek language.")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("fitness-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(1068051).build())
                .setQuantity(10)
                .putAllProperties(properties)
                .build();
        
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