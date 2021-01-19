/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

import AWS.ALBGrpc;
import com.google.protobuf.Empty;
import com.google.protobuf.NullValue;
import com.google.protobuf.Timestamp;
import com.google.type.Money;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.daos.CategoryDao;
import tapp.product.*;

import javax.persistence.EntityManager;
import java.io.IOException;
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
    private CategoryDao categoryDao;

    public ProductServiceImpl(EntityManager entityManager){

        categoryDao = new CategoryDao(entityManager);
    }

    @Override
    public synchronized void getCategory(ID request, StreamObserver<Category> responseObserver) {

        logger.debug("getCategory");
        Category category = null;

        if (request == null || request.getId().isEmpty()) {

            logger.debug("Category id is null");
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Category id is null")
                    .asRuntimeException());
        }

        try {
            Optional<service.entities.Category> categoryValue = categoryDao.get(UUID.fromString(request.getId()));

            if(categoryValue.isPresent()) {

                Instant time = Instant.now();
                Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                        .setNanos(time.getNano()).build();

                Instant instantValid = categoryValue.get().getValidTime().toInstant();
                Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                        .setNanos(instantValid.getNano()).build();

                category = Category
                        .newBuilder()
                        .setId(categoryValue.get().getId().toString())
                        .setTransactionTime(transactionTime)
                        .setValidTime(validTime)
                        .setCreatedTime(transactionTime)
                        .setTitle(categoryValue.get().getTitle() != null ? categoryValue.get().getTitle() : "")
                        .setSubtitle(categoryValue.get().getSubtitle() != null ? categoryValue.get().getSubtitle() : "")
                        .setDescription(categoryValue.get().getDescription() != null ? categoryValue.get().getDescription() : "")
                        .setParent(categoryValue.get().getParent() != null ? categoryValue.get().getParent().toString() : String.valueOf(NullValue.NULL_VALUE))
                        //.setImage("//image.tapp/Image/4e2e94d7-016a-4fd6-812d-3baa544e4e17")
                        .build();
            }
        } catch (CustomException | ServiceException e) {
            logger.error("id {} error message: {}", request.getId(), e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }

        responseObserver.onNext(category);
        responseObserver.onCompleted();
    }

    @Override
    public void listCategories(CategoriesList request, StreamObserver<Categories> responseObserver) {

        logger.debug("listCategories");
        Categories categories = null;

        if(request == null || request.getParent().isEmpty()){

            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Category parent id is null")
                    .asRuntimeException());
        }

        try {

            List<UUID> categoryParentIds = new ArrayList<>();
            categoryParentIds.add(UUID.fromString(request.getParent()));
            List<service.entities.Category> categoryListEntity = categoryDao.getCategoryList(categoryParentIds);
            List<Category> categoryList = new ArrayList<>();

            if(categoryListEntity.size() > 0){

                for (service.entities.Category categoryValue : categoryListEntity) {

                    Instant time = Instant.now();
                    Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                            .setNanos(time.getNano()).build();

                    Instant instantValid = categoryValue.getValidTime().toInstant();
                    Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                            .setNanos(instantValid.getNano()).build();

                    Category category = Category
                            .newBuilder()
                            .setId(categoryValue.getId().toString())
                            .setTransactionTime(transactionTime)
                            .setValidTime(validTime)
                            .setCreatedTime(transactionTime)
                            .setTitle(categoryValue.getTitle() != null ? categoryValue.getTitle() : "")
                            .setSubtitle(categoryValue.getSubtitle() != null ? categoryValue.getSubtitle() : "")
                            .setDescription(categoryValue.getDescription() != null ? categoryValue.getDescription() : "")
                            .setParent(categoryValue.getParent() != null ? categoryValue.getParent().toString() : String.valueOf(NullValue.NULL_VALUE))
                            //.setImage("//image.tapp/Image/4e2e94d7-016a-4fd6-812d-3baa544e4e17")
                            .build();

                    categoryList.add(category);
                }

                categories = Categories
                        .newBuilder()
                        .addAllNodes(categoryList)
                        .build();
            }

        }catch (CustomException | ServiceException e){

            logger.error("Category parent id {} error message: {}", request.getParent(), e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }

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
                .setCategory("//product.tapp/Category/3dcd405f-d0b5-4841-b9f2-c1ef6394d989")
                .build();

        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }



    @Override
    public void listProducts(ProductsList request, StreamObserver<Products> responseObserver) {

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
                .setCategory("//product.tapp/Category/3dcd405f-d0b5-4841-b9f2-c1ef6394d989")
                .build();

        Product product2 = Product
                .newBuilder()
                .setId("//product.tapp/Product/df659673-dc85-49bc-8af6-f7497275a064")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setQuantity(0)
                .setCategory("//product.tapp/Category/713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd")
                .build();

        Product product3 = Product
                .newBuilder()
                .setId("//product.tapp/Product/19ef2f61-7ceb-4a69-a423-cdd513688e94")
                .setTransactionTime(transactionTime)
                .setValidTime(validTime)
                .setCreatedTime(transactionTime)
                .setQuantity(3)
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

        List<ProductVariant.Property> mapList = new ArrayList<>();
        mapList.add(ProductVariant.Property.newBuilder().setKey("Operating System").setValue("Android Wear").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Compatible Operating System").setValue("Android, iOS - Apple").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Model").setValue("Smart Bracelet").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Band Material").setValue("Silicone").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Series").setValue("C55").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Features").setValue("Blood Pressure Monitor, Bluetooth Enabled, Waterproof").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Case Material").setValue("Aluminium, Ceramic, Metal").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Item type").setValue("smart watch").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Screen size").setValue(".1.54 HD IPS, 240*240 Touch screen 2.5D fox surface capacitive full-fit touch screen").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("CPU chip").setValue("MTK2502").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("RAM/ROM").setValue("34M/128M").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Operating SystemPhone version require").setValue("Android 5.0 and above ,IOS 9.0 and above").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Waterproof").setValue("IP67").build());
        mapList.add(ProductVariant.Property.newBuilder().setKey("Packing list").setValue("1 x Smart watch  1 x Charger  1 x Retail package  1 x User manual").build());

        ProductVariant productVariant = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/9a0e4932-44be-11eb-b378-0242ac130002")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Fitness bracelet")
                .setSubtitle("")
                .setDescription("Heart rate monitor ,sleep monitor,blood pressure,bluetooth call and message reminder,remote music/camera.Alarm clock, calendar, stopwatch. Language:English, German, Spanish, Italian, French, Portuguese (Brazil), Russian, Indonesian, Czech, Arabic, Thai, Dutch, Polish, Turkish, Persian, Hebrew,Malaysian, Vietnamese, Greek language.")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("fitness-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(1068051).build())
                .setQuantity(10)
                .addAllProperties(mapList)
                .build();

        responseObserver.onNext(productVariant);
        responseObserver.onCompleted();
    }

    @Override
    public void listProductVariants(ProductVariantsList request, StreamObserver<ProductVariants> responseObserver) {

        logger.debug("listProductVariants");

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

        List<ProductVariant.Property> mapList1 = new ArrayList<>();
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Operating System").setValue("Android Wear").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Compatible Operating System").setValue("Android, iOS - Apple").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Model").setValue("Smart Bracelet").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Band Material").setValue("Silicone").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Series").setValue("C55").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Features").setValue("Blood Pressure Monitor, Bluetooth Enabled, Waterproof").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Case Material").setValue("Aluminium, Ceramic, Metal").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Item type").setValue("smart watch").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Screen size").setValue(".1.54 HD IPS, 240*240 Touch screen 2.5D fox surface capacitive full-fit touch screen").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("CPU chip").setValue("MTK2502").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("RAM/ROM").setValue("34M/128M").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Operating SystemPhone version require").setValue("Android 5.0 and above ,IOS 9.0 and above").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Waterproof").setValue("IP67").build());
        mapList1.add(ProductVariant.Property.newBuilder().setKey("Packing list").setValue("1 x Smart watch  1 x Charger  1 x Retail package  1 x User manual").build());

        ProductVariant productVariant1 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/9a0e4932-44be-11eb-b378-0242ac130002")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Fitness bracelet")
                .setSubtitle("")
                .setDescription("Heart rate monitor ,sleep monitor,blood pressure,bluetooth call and message reminder,remote music/camera.Alarm clock, calendar, stopwatch. Language:English, German, Spanish, Italian, French, Portuguese (Brazil), Russian, Indonesian, Czech, Arabic, Thai, Dutch, Polish, Turkish, Persian, Hebrew,Malaysian, Vietnamese, Greek language.")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("fitness-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(1068051).build())
                .setQuantity(10)
                .addAllProperties(mapList1)
                .build();

        List<ProductVariant.Property> mapList2 = new ArrayList<>();
        mapList2.add(ProductVariant.Property.newBuilder().setKey("Type").setValue("Type-C /Micro USB").build());
        mapList2.add(ProductVariant.Property.newBuilder().setKey("Cable Length").setValue("1m").build());
        mapList2.add(ProductVariant.Property.newBuilder().setKey("Items Included").setValue("Charging & Data Sync Cable, Charging Cable, Data Sync Cable").build());
        mapList2.add(ProductVariant.Property.newBuilder().setKey("Brand").setValue("Unbranded").build());
        mapList2.add(ProductVariant.Property.newBuilder().setKey("Features").setValue("USB-C").build());
        mapList2.add(ProductVariant.Property.newBuilder().setKey("MPN").setValue("Does Not Apply").build());

        ProductVariant productVariant2 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/df659673-dc85-49bc-8af6-f7497275a064")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("USB Type-C Cable")
                .setSubtitle("")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("usb-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(24500).build())
                .setQuantity(10)
                .addAllProperties(mapList2)
                .build();

        List<ProductVariant.Property> mapList3 = new ArrayList<>();
        mapList3.add(ProductVariant.Property.newBuilder().setKey("Fit Design").setValue("In-Ear Only").build());
        mapList3.add(ProductVariant.Property.newBuilder().setKey("Wireless Technology").setValue("Bluetooth").build());
        mapList3.add(ProductVariant.Property.newBuilder().setKey("Compatible Brand").setValue("Android").build());
        mapList3.add(ProductVariant.Property.newBuilder().setKey("Connectivity").setValue("3.5mm Jack, Lightning").build());
        mapList3.add(ProductVariant.Property.newBuilder().setKey("Earpiece Design").setValue("Earbud (In Ear) Type: Headset").build());
        mapList3.add(ProductVariant.Property.newBuilder().setKey("Connector(s)").setValue("3.5mm Jack/Lightning Features: Built-in Microphone, Call functions, Playback Controls, Volume Control").build());
        mapList3.add(ProductVariant.Property.newBuilder().setKey("Earpiece").setValue("Double Color: White").build());
        mapList3.add(ProductVariant.Property.newBuilder().setKey("Brand").setValue("Unbranded").build());
        mapList3.add(ProductVariant.Property.newBuilder().setKey("MPN").setValue("Does Not Apply").build());

        ProductVariant productVariant3 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/ProductVariant/e55305f0-5722-4634-b48f-a8ced6d17b4e")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Headset with box")
                .setSubtitle("")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("headset-box-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(1068051).build())
                .setQuantity(10)
                .addAllProperties(mapList3)
                .build();

        List<ProductVariant.Property> mapList4 = new ArrayList<>();
        mapList4.add(ProductVariant.Property.newBuilder().setKey("Earphone style").setValue("wired earphone").build());
        mapList4.add(ProductVariant.Property.newBuilder().setKey("Plug diameter").setValue("3.5MM/ Lightning").build());
        mapList4.add(ProductVariant.Property.newBuilder().setKey("State of the fast connection").setValue("to peel").build());
        mapList4.add(ProductVariant.Property.newBuilder().setKey("Quick connection").setValue("you device is connect").build());
        mapList4.add(ProductVariant.Property.newBuilder().setKey("Prompt disconnect").setValue("you device is disconnect").build());
        mapList4.add(ProductVariant.Property.newBuilder().setKey("Button function").setValue("Button function").build());
        mapList4.add(ProductVariant.Property.newBuilder().setKey("Style").setValue("in ear").build());

        ProductVariant productVariant4 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/ProductVariant/e55305f0-5722-4634-b48f-a8ced6d17b4e")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Headset (Blue)")
                .setSubtitle("")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("headset-blue-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(26000).build())
                .setQuantity(4)
                .addAllProperties(mapList4)
                .build();

        List<ProductVariant.Property> mapList5 = new ArrayList<>();
        mapList5.add(ProductVariant.Property.newBuilder().setKey("Earphone style").setValue("wired earphone").build());
        mapList5.add(ProductVariant.Property.newBuilder().setKey("Plug diameter").setValue("3.5MM/ Lightning").build());
        mapList5.add(ProductVariant.Property.newBuilder().setKey("State of the fast connection").setValue("to peel").build());
        mapList5.add(ProductVariant.Property.newBuilder().setKey("Quick connection").setValue("you device is connect").build());
        mapList5.add(ProductVariant.Property.newBuilder().setKey("Prompt disconnect").setValue("you device is disconnect").build());
        mapList5.add(ProductVariant.Property.newBuilder().setKey("Button function").setValue("Button function").build());
        mapList5.add(ProductVariant.Property.newBuilder().setKey("Style").setValue("in ear").build());

        Map<String, String> options5 = new HashMap<>();
        options5.put("white","white");

        ProductVariant productVariant5 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Headset (White)")
                .setSubtitle("")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("headset-white-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(22000).build())
                .setQuantity(47)
                .addAllProperties(mapList5)
                .putAllOptions(options5)
                .build();

        List<ProductVariant.Property> mapList6 = new ArrayList<>();
        mapList6.add(ProductVariant.Property.newBuilder().setKey("Earphone style").setValue("wired earphone").build());
        mapList6.add(ProductVariant.Property.newBuilder().setKey("Plug diameter").setValue("3.5MM/ Lightning").build());
        mapList6.add(ProductVariant.Property.newBuilder().setKey("State of the fast connection").setValue("to peel").build());
        mapList6.add(ProductVariant.Property.newBuilder().setKey("Quick connection").setValue("you device is connect").build());
        mapList6.add(ProductVariant.Property.newBuilder().setKey("Prompt disconnect").setValue("you device is disconnect").build());
        mapList6.add(ProductVariant.Property.newBuilder().setKey("Button function").setValue("Button function").build());
        mapList6.add(ProductVariant.Property.newBuilder().setKey("Style").setValue("in ear").build());

        Map<String, String> options6 = new HashMap<>();
        options6.put("red","red");

        ProductVariant productVariant6 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Headset (Red)")
                .setSubtitle("")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("headset-red-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(26000).build())
                .setQuantity(47)
                .addAllProperties(mapList6)
                .putAllOptions(options6)
                .build();

        List<ProductVariant.Property> mapList7 = new ArrayList<>();
        mapList7.add(ProductVariant.Property.newBuilder().setKey("Material").setValue("Plastic +TPU").build());
        mapList7.add(ProductVariant.Property.newBuilder().setKey("Product name").setValue("Phone Socket").build());
        mapList7.add(ProductVariant.Property.newBuilder().setKey("Function").setValue("Stick on the phone").build());
        mapList7.add(ProductVariant.Property.newBuilder().setKey("Weight").setValue("10g").build());
        mapList7.add(ProductVariant.Property.newBuilder().setKey("Compatible Brand").setValue("All phones").build());

        Map<String, String> options7 = new HashMap<>();
        options7.put("black","black");

        ProductVariant productVariant7 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Phone stent (Black)")
                .setSubtitle("Pop socket / Phone stent")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("phone-stent-black-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(4000).build())
                .setQuantity(43)
                .addAllProperties(mapList7)
                .putAllOptions(options7)
                .build();

        List<ProductVariant.Property> mapList8 = new ArrayList<>();
        mapList8.add(ProductVariant.Property.newBuilder().setKey("Material").setValue("Plastic +TPU").build());
        mapList8.add(ProductVariant.Property.newBuilder().setKey("Product name").setValue("Phone Socket").build());
        mapList8.add(ProductVariant.Property.newBuilder().setKey("Function").setValue("Stick on the phone").build());
        mapList8.add(ProductVariant.Property.newBuilder().setKey("Weight").setValue("10g").build());
        mapList8.add(ProductVariant.Property.newBuilder().setKey("Compatible Brand").setValue("All phones").build());

        Map<String, String> options8 = new HashMap<>();
        options8.put("pink","pink");

        ProductVariant productVariant8 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Phone stent (Pink)")
                .setSubtitle("Pop socket / Phone stent")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("phone-ping-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(4000).build())
                .setQuantity(20)
                .addAllProperties(mapList8)
                .putAllOptions(options8)
                .build();

        List<ProductVariant.Property> mapList9 = new ArrayList<>();
        mapList9.add(ProductVariant.Property.newBuilder().setKey("Material").setValue("Plastic +TPU").build());
        mapList9.add(ProductVariant.Property.newBuilder().setKey("Product name").setValue("Phone Socket").build());
        mapList9.add(ProductVariant.Property.newBuilder().setKey("Function").setValue("Stick on the phone").build());
        mapList9.add(ProductVariant.Property.newBuilder().setKey("Weight").setValue("10g").build());
        mapList9.add(ProductVariant.Property.newBuilder().setKey("Compatible Brand").setValue("All phones").build());

        Map<String, String> options9 = new HashMap<>();
        options9.put("white","white");

        ProductVariant productVariant9 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Phone stent (White)")
                .setSubtitle("Pop socket / Phone stent")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("phone-stent-white-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(4000).build())
                .setQuantity(20)
                .addAllProperties(mapList9)
                .putAllOptions(options9)
                .build();

        List<ProductVariant.Property> mapList10 = new ArrayList<>();
        mapList10.add(ProductVariant.Property.newBuilder().setKey("Material").setValue("Plastic +TPU").build());
        mapList10.add(ProductVariant.Property.newBuilder().setKey("Product name").setValue("Phone Socket").build());
        mapList10.add(ProductVariant.Property.newBuilder().setKey("Function").setValue("Stick on the phone").build());
        mapList10.add(ProductVariant.Property.newBuilder().setKey("Weight").setValue("10g").build());
        mapList10.add(ProductVariant.Property.newBuilder().setKey("Compatible Brand").setValue("All phones").build());

        Map<String, String> options10 = new HashMap<>();
        options10.put("blue","blue");

        ProductVariant productVariant10 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Phone stent (Blue)")
                .setSubtitle("Pop socket / Phone stent")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("phone-stent-blue-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(4000).build())
                .setQuantity(20)
                .addAllProperties(mapList10)
                .putAllOptions(options10)
                .build();

        List<ProductVariant.Property> mapList11 = new ArrayList<>();
        mapList11.add(ProductVariant.Property.newBuilder().setKey("Material").setValue("Plastic +TPU").build());
        mapList11.add(ProductVariant.Property.newBuilder().setKey("Product name").setValue("Phone Socket").build());
        mapList11.add(ProductVariant.Property.newBuilder().setKey("Function").setValue("Stick on the phone").build());
        mapList11.add(ProductVariant.Property.newBuilder().setKey("Weight").setValue("10g").build());
        mapList11.add(ProductVariant.Property.newBuilder().setKey("Compatible Brand").setValue("All phones").build());

        Map<String, String> options11 = new HashMap<>();
        options11.put("cartoon","cartoon");

        ProductVariant productVariant11 = ProductVariant
                .newBuilder()
                .setProduct("//product.tapp/Product/ec0cb10f-f275-4861-9d04-cde3c4f5b868")
                .setId("//product.tapp/ProductVariant/")
                .setTransactionTime(transactionTime)
                .setCreatedTime(transactionTime)
                .setValidTime(validTime)
                .setTitle("Phone stent (cartoon)")
                .setSubtitle("Pop socket / Phone stent")
                .setDescription("")
                .setImages(0, "")
                .setForm(ProductVariant.Form.PHYSICAL)
                .setSku("phone-stent-cartoon-1")
                .setPrice(Money.newBuilder().setCurrencyCode("IDR").setUnits(4000).build())
                .setQuantity(20)
                .addAllProperties(mapList11)
                .putAllOptions(options11)
                .build();

        List<ProductVariant> productVariantList = new ArrayList<>();
        productVariantList.add(productVariant1);
        productVariantList.add(productVariant2);
        productVariantList.add(productVariant3);
        productVariantList.add(productVariant4);
        productVariantList.add(productVariant5);
        productVariantList.add(productVariant6);
        productVariantList.add(productVariant7);
        productVariantList.add(productVariant8);
        productVariantList.add(productVariant9);
        productVariantList.add(productVariant10);
        productVariantList.add(productVariant11);

        ProductVariants productVariants = ProductVariants
                .newBuilder()
                .addAllNodes(productVariantList)
                .build();

        responseObserver.onNext(productVariants);
        responseObserver.onCompleted();
    }
}