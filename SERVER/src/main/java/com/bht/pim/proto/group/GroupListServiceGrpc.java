package com.bht.pim.proto.group;

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
        comments = "Source: GroupList.proto")
public final class GroupListServiceGrpc {

  public static final String SERVICE_NAME = "com.bht.pim.proto.group.GroupListService";
  private static final int METHODID_GET_EMPLOYEE_LIST = 0;
  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<NoParam,
          GroupList> getGetEmployeeListMethod;
  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  private GroupListServiceGrpc() {
  }

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "getEmployeeList",
          requestType = NoParam.class,
          responseType = GroupList.class,
          methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<NoParam,
          GroupList> getGetEmployeeListMethod() {
    io.grpc.MethodDescriptor<NoParam, GroupList> getGetEmployeeListMethod;
    if ((getGetEmployeeListMethod = GroupListServiceGrpc.getGetEmployeeListMethod) == null) {
      synchronized (GroupListServiceGrpc.class) {
        if ((getGetEmployeeListMethod = GroupListServiceGrpc.getGetEmployeeListMethod) == null) {
          GroupListServiceGrpc.getGetEmployeeListMethod = getGetEmployeeListMethod =
                  io.grpc.MethodDescriptor.<NoParam, GroupList>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(
                                  "com.bht.pim.proto.group.GroupListService", "getEmployeeList"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  NoParam.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  GroupList.getDefaultInstance()))
                          .setSchemaDescriptor(new GroupListServiceMethodDescriptorSupplier("getEmployeeList"))
                          .build();
        }
      }
    }
    return getGetEmployeeListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GroupListServiceStub newStub(io.grpc.Channel channel) {
    return new GroupListServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GroupListServiceBlockingStub newBlockingStub(
          io.grpc.Channel channel) {
    return new GroupListServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GroupListServiceFutureStub newFutureStub(
          io.grpc.Channel channel) {
    return new GroupListServiceFutureStub(channel);
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GroupListServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                  .setSchemaDescriptor(new GroupListServiceFileDescriptorSupplier())
                  .addMethod(getGetEmployeeListMethod())
                  .build();
        }
      }
    }
    return result;
  }

  /**
   *
   */
  public static abstract class GroupListServiceImplBase implements io.grpc.BindableService {

    /**
     *
     */
    public void getEmployeeList(NoParam request,
                                io.grpc.stub.StreamObserver<GroupList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEmployeeListMethod(), responseObserver);
    }

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
              .addMethod(
                      getGetEmployeeListMethod(),
                      asyncUnaryCall(
                              new MethodHandlers<
                                      NoParam,
                                      GroupList>(
                                      this, METHODID_GET_EMPLOYEE_LIST)))
              .build();
    }
  }

  /**
   *
   */
  public static final class GroupListServiceStub extends io.grpc.stub.AbstractStub<GroupListServiceStub> {
    private GroupListServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GroupListServiceStub(io.grpc.Channel channel,
                                 io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GroupListServiceStub build(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
      return new GroupListServiceStub(channel, callOptions);
    }

    /**
     *
     */
    public void getEmployeeList(NoParam request,
                                io.grpc.stub.StreamObserver<GroupList> responseObserver) {
      asyncUnaryCall(
              getChannel().newCall(getGetEmployeeListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   *
   */
  public static final class GroupListServiceBlockingStub extends io.grpc.stub.AbstractStub<GroupListServiceBlockingStub> {
    private GroupListServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GroupListServiceBlockingStub(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GroupListServiceBlockingStub build(io.grpc.Channel channel,
                                                 io.grpc.CallOptions callOptions) {
      return new GroupListServiceBlockingStub(channel, callOptions);
    }

    /**
     *
     */
    public GroupList getEmployeeList(NoParam request) {
      return blockingUnaryCall(
              getChannel(), getGetEmployeeListMethod(), getCallOptions(), request);
    }
  }

  /**
   *
   */
  public static final class GroupListServiceFutureStub extends io.grpc.stub.AbstractStub<GroupListServiceFutureStub> {
    private GroupListServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GroupListServiceFutureStub(io.grpc.Channel channel,
                                       io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GroupListServiceFutureStub build(io.grpc.Channel channel,
                                               io.grpc.CallOptions callOptions) {
      return new GroupListServiceFutureStub(channel, callOptions);
    }

    /**
     *
     */
    public com.google.common.util.concurrent.ListenableFuture<GroupList> getEmployeeList(
            NoParam request) {
      return futureUnaryCall(
              getChannel().newCall(getGetEmployeeListMethod(), getCallOptions()), request);
    }
  }

  private static final class MethodHandlers<Req, Resp> implements
          io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GroupListServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GroupListServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_EMPLOYEE_LIST:
          serviceImpl.getEmployeeList((NoParam) request,
                  (io.grpc.stub.StreamObserver<GroupList>) responseObserver);
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

  private static abstract class GroupListServiceBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GroupListServiceBaseDescriptorSupplier() {
    }

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GroupListOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GroupListService");
    }
  }

  private static final class GroupListServiceFileDescriptorSupplier
          extends GroupListServiceBaseDescriptorSupplier {
    GroupListServiceFileDescriptorSupplier() {
    }
  }

  private static final class GroupListServiceMethodDescriptorSupplier
          extends GroupListServiceBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GroupListServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }
}
