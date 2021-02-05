package tapp.product;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: product.proto")
public final class ProductServiceGrpc {

  private ProductServiceGrpc() {}

  public static final String SERVICE_NAME = "tapp.product.ProductService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tapp.product.ID,
      tapp.product.Category> getGetCategoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCategory",
      requestType = tapp.product.ID.class,
      responseType = tapp.product.Category.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tapp.product.ID,
      tapp.product.Category> getGetCategoryMethod() {
    io.grpc.MethodDescriptor<tapp.product.ID, tapp.product.Category> getGetCategoryMethod;
    if ((getGetCategoryMethod = ProductServiceGrpc.getGetCategoryMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getGetCategoryMethod = ProductServiceGrpc.getGetCategoryMethod) == null) {
          ProductServiceGrpc.getGetCategoryMethod = getGetCategoryMethod =
              io.grpc.MethodDescriptor.<tapp.product.ID, tapp.product.Category>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCategory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.ID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.Category.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("GetCategory"))
              .build();
        }
      }
    }
    return getGetCategoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tapp.product.CategoriesList,
      tapp.product.Categories> getListCategoriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListCategories",
      requestType = tapp.product.CategoriesList.class,
      responseType = tapp.product.Categories.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tapp.product.CategoriesList,
      tapp.product.Categories> getListCategoriesMethod() {
    io.grpc.MethodDescriptor<tapp.product.CategoriesList, tapp.product.Categories> getListCategoriesMethod;
    if ((getListCategoriesMethod = ProductServiceGrpc.getListCategoriesMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getListCategoriesMethod = ProductServiceGrpc.getListCategoriesMethod) == null) {
          ProductServiceGrpc.getListCategoriesMethod = getListCategoriesMethod =
              io.grpc.MethodDescriptor.<tapp.product.CategoriesList, tapp.product.Categories>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListCategories"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.CategoriesList.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.Categories.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("ListCategories"))
              .build();
        }
      }
    }
    return getListCategoriesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tapp.product.ID,
      tapp.product.Product> getGetProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetProduct",
      requestType = tapp.product.ID.class,
      responseType = tapp.product.Product.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tapp.product.ID,
      tapp.product.Product> getGetProductMethod() {
    io.grpc.MethodDescriptor<tapp.product.ID, tapp.product.Product> getGetProductMethod;
    if ((getGetProductMethod = ProductServiceGrpc.getGetProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getGetProductMethod = ProductServiceGrpc.getGetProductMethod) == null) {
          ProductServiceGrpc.getGetProductMethod = getGetProductMethod =
              io.grpc.MethodDescriptor.<tapp.product.ID, tapp.product.Product>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.ID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.Product.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("GetProduct"))
              .build();
        }
      }
    }
    return getGetProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tapp.product.ProductsList,
      tapp.product.Products> getListProductsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListProducts",
      requestType = tapp.product.ProductsList.class,
      responseType = tapp.product.Products.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tapp.product.ProductsList,
      tapp.product.Products> getListProductsMethod() {
    io.grpc.MethodDescriptor<tapp.product.ProductsList, tapp.product.Products> getListProductsMethod;
    if ((getListProductsMethod = ProductServiceGrpc.getListProductsMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getListProductsMethod = ProductServiceGrpc.getListProductsMethod) == null) {
          ProductServiceGrpc.getListProductsMethod = getListProductsMethod =
              io.grpc.MethodDescriptor.<tapp.product.ProductsList, tapp.product.Products>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListProducts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.ProductsList.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.Products.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("ListProducts"))
              .build();
        }
      }
    }
    return getListProductsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tapp.product.ID,
      tapp.product.ProductVariant> getGetProductVariantMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetProductVariant",
      requestType = tapp.product.ID.class,
      responseType = tapp.product.ProductVariant.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tapp.product.ID,
      tapp.product.ProductVariant> getGetProductVariantMethod() {
    io.grpc.MethodDescriptor<tapp.product.ID, tapp.product.ProductVariant> getGetProductVariantMethod;
    if ((getGetProductVariantMethod = ProductServiceGrpc.getGetProductVariantMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getGetProductVariantMethod = ProductServiceGrpc.getGetProductVariantMethod) == null) {
          ProductServiceGrpc.getGetProductVariantMethod = getGetProductVariantMethod =
              io.grpc.MethodDescriptor.<tapp.product.ID, tapp.product.ProductVariant>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetProductVariant"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.ID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.ProductVariant.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("GetProductVariant"))
              .build();
        }
      }
    }
    return getGetProductVariantMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tapp.product.ProductVariantsList,
      tapp.product.ProductVariants> getListProductVariantsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListProductVariants",
      requestType = tapp.product.ProductVariantsList.class,
      responseType = tapp.product.ProductVariants.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tapp.product.ProductVariantsList,
      tapp.product.ProductVariants> getListProductVariantsMethod() {
    io.grpc.MethodDescriptor<tapp.product.ProductVariantsList, tapp.product.ProductVariants> getListProductVariantsMethod;
    if ((getListProductVariantsMethod = ProductServiceGrpc.getListProductVariantsMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getListProductVariantsMethod = ProductServiceGrpc.getListProductVariantsMethod) == null) {
          ProductServiceGrpc.getListProductVariantsMethod = getListProductVariantsMethod =
              io.grpc.MethodDescriptor.<tapp.product.ProductVariantsList, tapp.product.ProductVariants>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListProductVariants"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.ProductVariantsList.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tapp.product.ProductVariants.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("ListProductVariants"))
              .build();
        }
      }
    }
    return getListProductVariantsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProductServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceStub>() {
        @java.lang.Override
        public ProductServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceStub(channel, callOptions);
        }
      };
    return ProductServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProductServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceBlockingStub>() {
        @java.lang.Override
        public ProductServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceBlockingStub(channel, callOptions);
        }
      };
    return ProductServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProductServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceFutureStub>() {
        @java.lang.Override
        public ProductServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceFutureStub(channel, callOptions);
        }
      };
    return ProductServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ProductServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCategory(tapp.product.ID request,
        io.grpc.stub.StreamObserver<tapp.product.Category> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCategoryMethod(), responseObserver);
    }

    /**
     */
    public void listCategories(tapp.product.CategoriesList request,
        io.grpc.stub.StreamObserver<tapp.product.Categories> responseObserver) {
      asyncUnimplementedUnaryCall(getListCategoriesMethod(), responseObserver);
    }

    /**
     */
    public void getProduct(tapp.product.ID request,
        io.grpc.stub.StreamObserver<tapp.product.Product> responseObserver) {
      asyncUnimplementedUnaryCall(getGetProductMethod(), responseObserver);
    }

    /**
     */
    public void listProducts(tapp.product.ProductsList request,
        io.grpc.stub.StreamObserver<tapp.product.Products> responseObserver) {
      asyncUnimplementedUnaryCall(getListProductsMethod(), responseObserver);
    }

    /**
     */
    public void getProductVariant(tapp.product.ID request,
        io.grpc.stub.StreamObserver<tapp.product.ProductVariant> responseObserver) {
      asyncUnimplementedUnaryCall(getGetProductVariantMethod(), responseObserver);
    }

    /**
     */
    public void listProductVariants(tapp.product.ProductVariantsList request,
        io.grpc.stub.StreamObserver<tapp.product.ProductVariants> responseObserver) {
      asyncUnimplementedUnaryCall(getListProductVariantsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCategoryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tapp.product.ID,
                tapp.product.Category>(
                  this, METHODID_GET_CATEGORY)))
          .addMethod(
            getListCategoriesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tapp.product.CategoriesList,
                tapp.product.Categories>(
                  this, METHODID_LIST_CATEGORIES)))
          .addMethod(
            getGetProductMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tapp.product.ID,
                tapp.product.Product>(
                  this, METHODID_GET_PRODUCT)))
          .addMethod(
            getListProductsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tapp.product.ProductsList,
                tapp.product.Products>(
                  this, METHODID_LIST_PRODUCTS)))
          .addMethod(
            getGetProductVariantMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tapp.product.ID,
                tapp.product.ProductVariant>(
                  this, METHODID_GET_PRODUCT_VARIANT)))
          .addMethod(
            getListProductVariantsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tapp.product.ProductVariantsList,
                tapp.product.ProductVariants>(
                  this, METHODID_LIST_PRODUCT_VARIANTS)))
          .build();
    }
  }

  /**
   */
  public static final class ProductServiceStub extends io.grpc.stub.AbstractAsyncStub<ProductServiceStub> {
    private ProductServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCategory(tapp.product.ID request,
        io.grpc.stub.StreamObserver<tapp.product.Category> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCategoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listCategories(tapp.product.CategoriesList request,
        io.grpc.stub.StreamObserver<tapp.product.Categories> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListCategoriesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProduct(tapp.product.ID request,
        io.grpc.stub.StreamObserver<tapp.product.Product> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listProducts(tapp.product.ProductsList request,
        io.grpc.stub.StreamObserver<tapp.product.Products> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListProductsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProductVariant(tapp.product.ID request,
        io.grpc.stub.StreamObserver<tapp.product.ProductVariant> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetProductVariantMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listProductVariants(tapp.product.ProductVariantsList request,
        io.grpc.stub.StreamObserver<tapp.product.ProductVariants> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListProductVariantsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProductServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ProductServiceBlockingStub> {
    private ProductServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public tapp.product.Category getCategory(tapp.product.ID request) {
      return blockingUnaryCall(
          getChannel(), getGetCategoryMethod(), getCallOptions(), request);
    }

    /**
     */
    public tapp.product.Categories listCategories(tapp.product.CategoriesList request) {
      return blockingUnaryCall(
          getChannel(), getListCategoriesMethod(), getCallOptions(), request);
    }

    /**
     */
    public tapp.product.Product getProduct(tapp.product.ID request) {
      return blockingUnaryCall(
          getChannel(), getGetProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public tapp.product.Products listProducts(tapp.product.ProductsList request) {
      return blockingUnaryCall(
          getChannel(), getListProductsMethod(), getCallOptions(), request);
    }

    /**
     */
    public tapp.product.ProductVariant getProductVariant(tapp.product.ID request) {
      return blockingUnaryCall(
          getChannel(), getGetProductVariantMethod(), getCallOptions(), request);
    }

    /**
     */
    public tapp.product.ProductVariants listProductVariants(tapp.product.ProductVariantsList request) {
      return blockingUnaryCall(
          getChannel(), getListProductVariantsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProductServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ProductServiceFutureStub> {
    private ProductServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tapp.product.Category> getCategory(
        tapp.product.ID request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCategoryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tapp.product.Categories> listCategories(
        tapp.product.CategoriesList request) {
      return futureUnaryCall(
          getChannel().newCall(getListCategoriesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tapp.product.Product> getProduct(
        tapp.product.ID request) {
      return futureUnaryCall(
          getChannel().newCall(getGetProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tapp.product.Products> listProducts(
        tapp.product.ProductsList request) {
      return futureUnaryCall(
          getChannel().newCall(getListProductsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tapp.product.ProductVariant> getProductVariant(
        tapp.product.ID request) {
      return futureUnaryCall(
          getChannel().newCall(getGetProductVariantMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tapp.product.ProductVariants> listProductVariants(
        tapp.product.ProductVariantsList request) {
      return futureUnaryCall(
          getChannel().newCall(getListProductVariantsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CATEGORY = 0;
  private static final int METHODID_LIST_CATEGORIES = 1;
  private static final int METHODID_GET_PRODUCT = 2;
  private static final int METHODID_LIST_PRODUCTS = 3;
  private static final int METHODID_GET_PRODUCT_VARIANT = 4;
  private static final int METHODID_LIST_PRODUCT_VARIANTS = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProductServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProductServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CATEGORY:
          serviceImpl.getCategory((tapp.product.ID) request,
              (io.grpc.stub.StreamObserver<tapp.product.Category>) responseObserver);
          break;
        case METHODID_LIST_CATEGORIES:
          serviceImpl.listCategories((tapp.product.CategoriesList) request,
              (io.grpc.stub.StreamObserver<tapp.product.Categories>) responseObserver);
          break;
        case METHODID_GET_PRODUCT:
          serviceImpl.getProduct((tapp.product.ID) request,
              (io.grpc.stub.StreamObserver<tapp.product.Product>) responseObserver);
          break;
        case METHODID_LIST_PRODUCTS:
          serviceImpl.listProducts((tapp.product.ProductsList) request,
              (io.grpc.stub.StreamObserver<tapp.product.Products>) responseObserver);
          break;
        case METHODID_GET_PRODUCT_VARIANT:
          serviceImpl.getProductVariant((tapp.product.ID) request,
              (io.grpc.stub.StreamObserver<tapp.product.ProductVariant>) responseObserver);
          break;
        case METHODID_LIST_PRODUCT_VARIANTS:
          serviceImpl.listProductVariants((tapp.product.ProductVariantsList) request,
              (io.grpc.stub.StreamObserver<tapp.product.ProductVariants>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ProductServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProductServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return tapp.product.ProductOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProductService");
    }
  }

  private static final class ProductServiceFileDescriptorSupplier
      extends ProductServiceBaseDescriptorSupplier {
    ProductServiceFileDescriptorSupplier() {}
  }

  private static final class ProductServiceMethodDescriptorSupplier
      extends ProductServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProductServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProductServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProductServiceFileDescriptorSupplier())
              .addMethod(getGetCategoryMethod())
              .addMethod(getListCategoriesMethod())
              .addMethod(getGetProductMethod())
              .addMethod(getListProductsMethod())
              .addMethod(getGetProductVariantMethod())
              .addMethod(getListProductVariantsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
