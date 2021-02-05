package AWS;

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
    comments = "Source: alb.proto")
public final class ALBGrpc {

  private ALBGrpc() {}

  public static final String SERVICE_NAME = "AWS.ALB";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.protobuf.Empty> getHealthcheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "healthcheck",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.protobuf.Empty> getHealthcheckMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.google.protobuf.Empty> getHealthcheckMethod;
    if ((getHealthcheckMethod = ALBGrpc.getHealthcheckMethod) == null) {
      synchronized (ALBGrpc.class) {
        if ((getHealthcheckMethod = ALBGrpc.getHealthcheckMethod) == null) {
          ALBGrpc.getHealthcheckMethod = getHealthcheckMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "healthcheck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ALBMethodDescriptorSupplier("healthcheck"))
              .build();
        }
      }
    }
    return getHealthcheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ALBStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ALBStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ALBStub>() {
        @java.lang.Override
        public ALBStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ALBStub(channel, callOptions);
        }
      };
    return ALBStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ALBBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ALBBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ALBBlockingStub>() {
        @java.lang.Override
        public ALBBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ALBBlockingStub(channel, callOptions);
        }
      };
    return ALBBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ALBFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ALBFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ALBFutureStub>() {
        @java.lang.Override
        public ALBFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ALBFutureStub(channel, callOptions);
        }
      };
    return ALBFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ALBImplBase implements io.grpc.BindableService {

    /**
     */
    public void healthcheck(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getHealthcheckMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHealthcheckMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                com.google.protobuf.Empty>(
                  this, METHODID_HEALTHCHECK)))
          .build();
    }
  }

  /**
   */
  public static final class ALBStub extends io.grpc.stub.AbstractAsyncStub<ALBStub> {
    private ALBStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ALBStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ALBStub(channel, callOptions);
    }

    /**
     */
    public void healthcheck(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHealthcheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ALBBlockingStub extends io.grpc.stub.AbstractBlockingStub<ALBBlockingStub> {
    private ALBBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ALBBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ALBBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty healthcheck(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getHealthcheckMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ALBFutureStub extends io.grpc.stub.AbstractFutureStub<ALBFutureStub> {
    private ALBFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ALBFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ALBFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> healthcheck(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getHealthcheckMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HEALTHCHECK = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ALBImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ALBImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HEALTHCHECK:
          serviceImpl.healthcheck((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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

  private static abstract class ALBBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ALBBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AWS.Alb.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ALB");
    }
  }

  private static final class ALBFileDescriptorSupplier
      extends ALBBaseDescriptorSupplier {
    ALBFileDescriptorSupplier() {}
  }

  private static final class ALBMethodDescriptorSupplier
      extends ALBBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ALBMethodDescriptorSupplier(String methodName) {
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
      synchronized (ALBGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ALBFileDescriptorSupplier())
              .addMethod(getHealthcheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
