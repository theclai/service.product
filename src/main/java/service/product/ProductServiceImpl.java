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
    private CategoryDao categoryDao;

    public ProductServiceImpl(EntityManager entityManager){

        categoryDao = new CategoryDao(entityManager);
    }

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
                .setTitle("Fitness bracelet")
                .setSubtitle("")
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

        Map<String, String> properties1 = new HashMap<>();
        properties1.put("Operating System", "Android Wear");
        properties1.put("Compatible Operating System", "Android, iOS - Apple");
        properties1.put("Model","Smart Bracelet");
        properties1.put("Band Material","Silicone");
        properties1.put("Series","C55");
        properties1.put("Features","Blood Pressure Monitor, Bluetooth Enabled, Waterproof");
        properties1.put("Case Material","Aluminium, Ceramic, Metal");
        properties1.put("Item type","smart watch");
        properties1.put("Screen size",".1.54 HD IPS, 240*240 Touch screen 2.5D fox surface capacitive full-fit touch screen");
        properties1.put("CPU chip","MTK2502");
        properties1.put("RAM/ROM","34M/128M");
        properties1.put("Phone version require","Android 5.0 and above ,IOS 9.0 and above");
        properties1.put("Waterproof","IP67");
        properties1.put("Packing list","1 x Smart watch  1 x Charger  1 x Retail package  1 x User manual");

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
                .putAllProperties(properties1)
                .build();

        Map<String, String> properties2 = new HashMap<>();
        properties2.put("Type", "Type-C /Micro USB");
        properties2.put("Cable Length", "1m");
        properties2.put("Items Included","Charging & Data Sync Cable, Charging Cable, Data Sync Cable");
        properties2.put("Brand","Unbranded");
        properties2.put("Features","USB-C");
        properties2.put("MPN","Does Not Apply");

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
                .putAllProperties(properties2)
                .build();

        Map<String, String> properties3 = new HashMap<>();
        properties3.put("Fit Design", "In-Ear Only");
        properties3.put("Wireless Technology", "Bluetooth");
        properties3.put("Compatible Brand","Android");
        properties3.put("Connectivity","3.5mm Jack, Lightning");
        properties3.put("Earpiece Design","Earbud (In Ear) Type: Headset");
        properties3.put("Connector(s)","3.5mm Jack/Lightning Features: Built-in Microphone, Call functions, Playback Controls, Volume Control");
        properties3.put("Earpiece","Double Color: White");
        properties3.put("Brand","Unbranded");
        properties3.put("MPN","Does Not Apply");

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
                .putAllProperties(properties3)
                .build();

        Map<String, String> properties4 = new HashMap<>();
        properties4.put("Earphone style", "wired earphone");
        properties4.put("Plug diameter", "3.5MM/ Lightning");
        properties4.put("State of the fast connection","to peel");
        properties4.put("Quick connection","you device is connect");
        properties4.put("Prompt disconnect","you device is disconnect");
        properties4.put("Button function","Button function");
        properties4.put("Style","in ear");

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
                .putAllProperties(properties4)
                .build();

        Map<String, String> properties5 = new HashMap<>();
        properties5.put("Earphone style", "wired earphone");
        properties5.put("Plug diameter", "3.5MM/ Lightning");
        properties5.put("State of the fast connection","to peel");
        properties5.put("Quick connection","you device is connect");
        properties5.put("Prompt disconnect","you device is disconnect");
        properties5.put("Button function","Microphone with microphone");
        properties5.put("Style","in ear");

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
                .putAllProperties(properties5)
                .putAllOptions(options5)
                .build();

        Map<String, String> properties6 = new HashMap<>();
        properties6.put("Earphone style", "wired earphone");
        properties6.put("Plug diameter", "3.5MM/ Lightning");
        properties6.put("State of the fast connection","to peel");
        properties6.put("Quick connection","you device is connect");
        properties6.put("Prompt disconnect","you device is disconnect");
        properties6.put("Button function","Microphone with microphone");
        properties6.put("Style","in ear");

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
                .putAllProperties(properties6)
                .putAllOptions(options6)
                .build();

        Map<String, String> properties7 = new HashMap<>();
        properties7.put("Material", "Plastic +TPU");
        properties7.put("Product name", "Phone Socket");
        properties7.put("Function","Stick on the phone");
        properties7.put("Weight","10g");
        properties7.put("Compatible Brand","All phones");

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
                .putAllProperties(properties7)
                .putAllOptions(options7)
                .build();

        Map<String, String> properties8 = new HashMap<>();
        properties8.put("Material", "Plastic +TPU");
        properties8.put("Product name", "Phone Socket");
        properties8.put("Function","Stick on the phone");
        properties8.put("Weight","10g");
        properties8.put("Compatible Brand","All phones");

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
                .putAllProperties(properties8)
                .putAllOptions(options8)
                .build();

        Map<String, String> properties9 = new HashMap<>();
        properties9.put("Material", "Plastic +TPU");
        properties9.put("Product name", "Phone Socket");
        properties9.put("Function","Stick on the phone");
        properties9.put("Weight","10g");
        properties9.put("Compatible Brand","All phones");

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
                .putAllProperties(properties9)
                .putAllOptions(options9)
                .build();

        Map<String, String> properties10 = new HashMap<>();
        properties10.put("Material", "Plastic +TPU");
        properties10.put("Product name", "Phone Socket");
        properties10.put("Function","Stick on the phone");
        properties10.put("Weight","10g");
        properties10.put("Compatible Brand","All phones");

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
                .putAllProperties(properties10)
                .putAllOptions(options10)
                .build();

        Map<String, String> properties11 = new HashMap<>();
        properties11.put("Material", "Plastic +TPU");
        properties11.put("Product name", "Phone Socket");
        properties11.put("Function","Stick on the phone");
        properties11.put("Weight","10g");
        properties11.put("Compatible Brand","All phones");

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
                .putAllProperties(properties11)
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