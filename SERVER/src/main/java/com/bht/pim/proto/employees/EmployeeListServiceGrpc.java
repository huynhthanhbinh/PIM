package com.bht.pim.proto.employees;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: EmployeeList.proto")
public final class EmployeeListServiceGrpc {

  private EmployeeListServiceGrpc() {}

    public static final String SERVICE_NAME = "com.bht.pim.proto.employees.EmployeeListService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<NoParam,
      EmployeeList> getGetEmployeeListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getEmployeeList",
      requestType = NoParam.class,
      responseType = EmployeeList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<NoParam,
      EmployeeList> getGetEmployeeListMethod() {
    io.grpc.MethodDescriptor<NoParam, EmployeeList> getGetEmployeeListMethod;
    if ((getGetEmployeeListMethod = EmployeeListServiceGrpc.getGetEmployeeListMethod) == null) {
      synchronized (EmployeeListServiceGrpc.class) {
        if ((getGetEmployeeListMethod = EmployeeListServiceGrpc.getGetEmployeeListMethod) == null) {
            EmployeeListServiceGrpc.getGetEmployeeListMethod = getGetEmployeeListMethod =
              io.grpc.MethodDescriptor.<NoParam, EmployeeList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                      "com.bht.pim.proto.employees.EmployeeListService", "getEmployeeList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  NoParam.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EmployeeList.getDefaultInstance()))
                  .setSchemaDescriptor(new EmployeeListServiceMethodDescriptorSupplier("getEmployeeList"))
                  .build();
          }
        }
     }
     return getGetEmployeeListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EmployeeListServiceStub newStub(io.grpc.Channel channel) {
    return new EmployeeListServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EmployeeListServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EmployeeListServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EmployeeListServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EmployeeListServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class EmployeeListServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getEmployeeList(NoParam request,
                                io.grpc.stub.StreamObserver<EmployeeList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEmployeeListMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetEmployeeListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                NoParam,
                EmployeeList>(
                  this, METHODID_GET_EMPLOYEE_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class EmployeeListServiceStub extends io.grpc.stub.AbstractStub<EmployeeListServiceStub> {
    private EmployeeListServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmployeeListServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmployeeListServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmployeeListServiceStub(channel, callOptions);
    }

    /**
     */
    public void getEmployeeList(NoParam request,
                                io.grpc.stub.StreamObserver<EmployeeList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetEmployeeListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EmployeeListServiceBlockingStub extends io.grpc.stub.AbstractStub<EmployeeListServiceBlockingStub> {
    private EmployeeListServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmployeeListServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmployeeListServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmployeeListServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public EmployeeList getEmployeeList(NoParam request) {
      return blockingUnaryCall(
          getChannel(), getGetEmployeeListMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EmployeeListServiceFutureStub extends io.grpc.stub.AbstractStub<EmployeeListServiceFutureStub> {
    private EmployeeListServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmployeeListServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmployeeListServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmployeeListServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<EmployeeList> getEmployeeList(
        NoParam request) {
      return futureUnaryCall(
          getChannel().newCall(getGetEmployeeListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_EMPLOYEE_LIST = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EmployeeListServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EmployeeListServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_EMPLOYEE_LIST:
          serviceImpl.getEmployeeList((NoParam) request,
              (io.grpc.stub.StreamObserver<EmployeeList>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EmployeeListServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EmployeeListServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return EmployeeListOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EmployeeListService");
    }
  }

  private static final class EmployeeListServiceFileDescriptorSupplier
      extends EmployeeListServiceBaseDescriptorSupplier {
    EmployeeListServiceFileDescriptorSupplier() {}
  }

  private static final class EmployeeListServiceMethodDescriptorSupplier
      extends EmployeeListServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EmployeeListServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EmployeeListServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EmployeeListServiceFileDescriptorSupplier())
              .addMethod(getGetEmployeeListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
