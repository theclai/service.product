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
import service.daos.ProductVariantDao;
import service.daos.VariantOptionDao;
import service.daos.VariantPropertyDao;
import service.entities.VariantOption;
import service.entities.VariantProperty;
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
    private ProductVariantDao productVariantDao;
    private VariantPropertyDao variantPropertyDao;
    private VariantOptionDao variantOptionDao;

    public ProductServiceImpl(EntityManager entityManager){

        categoryDao = new CategoryDao(entityManager);
        productVariantDao = new ProductVariantDao(entityManager);
        variantPropertyDao = new VariantPropertyDao(entityManager);
        variantOptionDao = new VariantOptionDao(entityManager);
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
        ProductVariant productVariant = null;

        if (request == null || request.getId().isEmpty()) {

            logger.debug("Product variant id is null");
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Product variant id is null")
                    .asRuntimeException());
        }

        try {

            Optional<service.entities.ProductVariant> productVariantValue = productVariantDao.get(UUID.fromString(request.getId()));

            if (productVariantValue.isPresent()) {

                Instant time = Instant.now();
                Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                        .setNanos(time.getNano()).build();

                Instant instantValid = productVariantValue.get().getValidTime().toInstant();
                Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                        .setNanos(instantValid.getNano()).build();

                Money money = Money.newBuilder()
                        .setCurrencyCode(productVariantValue.get().getPriceCurrencyCode())
                        .setUnits(productVariantValue.get().getPriceUnits())
                        .setNanos(productVariantValue.get().getPriceNanos())
                        .build();

                List<ProductVariant.Property> properties = new ArrayList<>();
                List<VariantProperty> variantPropertyEntities = variantPropertyDao.getVariantProperties(productVariantValue.get().getId());

                for (VariantProperty vp : variantPropertyEntities) {
                    ProductVariant.Property property = ProductVariant.Property.newBuilder()
                            .setKey(vp.getKey())
                            .setValue(vp.getValue())
                            .build();

                    properties.add(property);
                }

                List<VariantOption> variantOptions = variantOptionDao.getVariantOptions(productVariantValue.get().getId());
                Map<String, String> options = new HashMap<>();

                for (VariantOption v : variantOptions) {
                    options.put(v.getId(), v.getValue());
                }

                productVariant = ProductVariant
                        .newBuilder()
                        .setId(productVariantValue.get().getId().toString())
                        .setTransactionTime(transactionTime)
                        .setValidTime(validTime)
                        .setCreatedTime(transactionTime)
                        .setTitle(productVariantValue.get().getTitle() != null ? productVariantValue.get().getTitle() : "")
                        .setSubtitle(productVariantValue.get().getSubtitle() != null ? productVariantValue.get().getSubtitle() : "")
                        .setDescription(productVariantValue.get().getDescription() != null ? productVariantValue.get().getDescription() : "")
                        .setForm(productVariantValue.get().getProductVariantForm() != null ? mapProductVariantForm(productVariantValue.get().getProductVariantForm().toString()) : null)
                        .setSku(productVariantValue.get().getSku() != null ? productVariantValue.get().getSku() : "")
                        .setPrice(money)
                        .setProduct(productVariantValue.get().getProduct().toString())
                        .setQuantity(productVariantValue.get().getQuantity() != 0 ? productVariantValue.get().getQuantity() : 0)
                        .addAllProperties(properties)
                        .putAllOptions(options)
                        .setPackageMeasurements(
                                Measurements
                                        .newBuilder()
                                        .setWidth(productVariantValue.get().getWidth() != 0 ? productVariantValue.get().getWidth() : 0)
                                        .setLength(productVariantValue.get().getLength() != 0 ? productVariantValue.get().getLength() : 0)
                                        .setHeight(productVariantValue.get().getHeight() != 0 ? productVariantValue.get().getHeight() : 0)
                                        .setWeight(productVariantValue.get().getWeight() != 0 ? productVariantValue.get().getWeight() : 0)
                                        .build()
                        )
                        //.setImages()
                        .build();
            }

        } catch (CustomException e) {

            logger.error("Product variant id {} error message: {}", request.getId(), e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }

        responseObserver.onNext(productVariant);
        responseObserver.onCompleted();
    }

    @Override
    public void listProductVariants(ProductVariantsList request, StreamObserver<ProductVariants> responseObserver) {

        logger.debug("listProductVariants");
        ProductVariants productVariants = null;

        if (request == null || request.getProduct().isEmpty()) {

            logger.debug("Product id is null");
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Product id is null")
                    .asRuntimeException());
        }

        try{

            List<UUID> productIds = new ArrayList<>();
            productIds.add(UUID.fromString(request.getProduct()));
            List<service.entities.ProductVariant> productVariantListEntity = productVariantDao.getProductVariants(productIds);
            List<ProductVariant> productVariantList = new ArrayList<>();

            if(productVariantListEntity.size() > 0){

                for (service.entities.ProductVariant productVariantValue : productVariantListEntity) {

                    Instant time = Instant.now();
                    Timestamp transactionTime = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                            .setNanos(time.getNano()).build();

                    Instant instantValid = productVariantValue.getValidTime().toInstant();
                    Timestamp validTime = Timestamp.newBuilder().setSeconds(instantValid.getEpochSecond())
                            .setNanos(instantValid.getNano()).build();

                    Money money = Money.newBuilder()
                            .setCurrencyCode(productVariantValue.getPriceCurrencyCode())
                            .setUnits(productVariantValue.getPriceUnits())
                            .setNanos(productVariantValue.getPriceNanos())
                            .build();

                    List<ProductVariant.Property> properties = new ArrayList<>();
                    List<VariantProperty> variantPropertyEntities = variantPropertyDao.getVariantProperties(productVariantValue.getId());

                    for (VariantProperty vp : variantPropertyEntities) {
                        ProductVariant.Property property = ProductVariant.Property.newBuilder()
                                .setKey(vp.getKey())
                                .setValue(vp.getValue())
                                .build();

                        properties.add(property);
                    }

                    List<VariantOption> variantOptions = variantOptionDao.getVariantOptions(productVariantValue.getId());
                    Map<String, String> options = new HashMap<>();

                    for (VariantOption v : variantOptions) {
                        options.put(v.getId(), v.getValue());
                    }

                    ProductVariant productVariant = ProductVariant
                            .newBuilder()
                            .setId(productVariantValue.getId().toString())
                            .setTransactionTime(transactionTime)
                            .setValidTime(validTime)
                            .setCreatedTime(transactionTime)
                            .setTitle(productVariantValue.getTitle() != null ? productVariantValue.getTitle() : "")
                            .setSubtitle(productVariantValue.getSubtitle() != null ? productVariantValue.getSubtitle() : "")
                            .setDescription(productVariantValue.getDescription() != null ? productVariantValue.getDescription() : "")
                            .setForm(productVariantValue.getProductVariantForm() != null ? mapProductVariantForm(productVariantValue.getProductVariantForm().toString()) : null)
                            .setSku(productVariantValue.getSku() != null ? productVariantValue.getSku() : "")
                            .setPrice(money)
                            .setProduct(productVariantValue.getProduct().toString())
                            .setQuantity(productVariantValue.getQuantity() != 0 ? productVariantValue.getQuantity() : 0)
                            .addAllProperties(properties)
                            .putAllOptions(options)
                            .setPackageMeasurements(
                                    Measurements
                                            .newBuilder()
                                            .setWidth(productVariantValue.getWidth() != 0 ? productVariantValue.getWidth() : 0)
                                            .setLength(productVariantValue.getLength() != 0 ? productVariantValue.getLength() : 0)
                                            .setHeight(productVariantValue.getHeight() != 0 ? productVariantValue.getHeight() : 0)
                                            .setWeight(productVariantValue.getWeight() != 0 ? productVariantValue.getWeight() : 0)
                                            .build()
                            )
                            //.setImages()
                            .build();

                    productVariantList.add(productVariant);
                }

                productVariants = ProductVariants
                        .newBuilder()
                        .addAllNodes(productVariantList)
                        .build();
            }

        }catch (CustomException e){

            logger.error("Product variant id {} error message: {}", request.getProduct(), e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }

        responseObserver.onNext(productVariants);
        responseObserver.onCompleted();
    }

    private ProductVariant.Form mapProductVariantForm(String form){

        ProductVariant.Form formValue = null;

        if(form.toLowerCase() == "digital"){

            formValue = ProductVariant.Form.DIGITAL;

        }else if(form.toLowerCase() == "physical"){

            formValue = ProductVariant.Form.PHYSICAL;

        }else if(form.toLowerCase() == "billing"){

            formValue = ProductVariant.Form.BILLING;

        }else if(form.toLowerCase() == "lending"){

            formValue = ProductVariant.Form.LENDING;
        }

        return formValue;
    }
}