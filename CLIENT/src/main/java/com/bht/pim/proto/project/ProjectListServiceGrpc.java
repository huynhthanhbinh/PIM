package com.bht.pim.proto.project;

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
    comments = "Source: ProjectList.proto")
public final class ProjectListServiceGrpc {

  private ProjectListServiceGrpc() {}

  public static final String SERVICE_NAME = "com.bht.pim.proto.project.ProjectListService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<NoParam,
          ProjectList> getGetProjectListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getProjectList",
      requestType = NoParam.class,
      responseType = ProjectList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<NoParam,
          ProjectList> getGetProjectListMethod() {
    io.grpc.MethodDescriptor<NoParam, ProjectList> getGetProjectListMethod;
    if ((getGetProjectListMethod = ProjectListServiceGrpc.getGetProjectListMethod) == null) {
      synchronized (ProjectListServiceGrpc.class) {
        if ((getGetProjectListMethod = ProjectListServiceGrpc.getGetProjectListMethod) == null) {
          ProjectListServiceGrpc.getGetProjectListMethod = getGetProjectListMethod =
              io.grpc.MethodDescriptor.<NoParam, ProjectList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.bht.pim.proto.project.ProjectListService", "getProjectList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  NoParam.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ProjectList.getDefaultInstance()))
                  .setSchemaDescriptor(new ProjectListServiceMethodDescriptorSupplier("getProjectList"))
                  .build();
          }
        }
     }
     return getGetProjectListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<NoParam,
          ProjectNumbers> getGetProjectNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getProjectNumbers",
      requestType = NoParam.class,
      responseType = ProjectNumbers.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<NoParam,
          ProjectNumbers> getGetProjectNumbersMethod() {
    io.grpc.MethodDescriptor<NoParam, ProjectNumbers> getGetProjectNumbersMethod;
    if ((getGetProjectNumbersMethod = ProjectListServiceGrpc.getGetProjectNumbersMethod) == null) {
      synchronized (ProjectListServiceGrpc.class) {
        if ((getGetProjectNumbersMethod = ProjectListServiceGrpc.getGetProjectNumbersMethod) == null) {
          ProjectListServiceGrpc.getGetProjectNumbersMethod = getGetProjectNumbersMethod =
              io.grpc.MethodDescriptor.<NoParam, ProjectNumbers>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.bht.pim.proto.project.ProjectListService", "getProjectNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  NoParam.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ProjectNumbers.getDefaultInstance()))
                  .setSchemaDescriptor(new ProjectListServiceMethodDescriptorSupplier("getProjectNumbers"))
                  .build();
          }
        }
     }
     return getGetProjectNumbersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProjectListServiceStub newStub(io.grpc.Channel channel) {
    return new ProjectListServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProjectListServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ProjectListServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProjectListServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ProjectListServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ProjectListServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getProjectList(NoParam request,
                               io.grpc.stub.StreamObserver<ProjectList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetProjectListMethod(), responseObserver);
    }

    /**
     */
    public void getProjectNumbers(NoParam request,
                                  io.grpc.stub.StreamObserver<ProjectNumbers> responseObserver) {
      asyncUnimplementedUnaryCall(getGetProjectNumbersMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetProjectListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                      NoParam,
                      ProjectList>(
                  this, METHODID_GET_PROJECT_LIST)))
          .addMethod(
            getGetProjectNumbersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                      NoParam,
                      ProjectNumbers>(
                  this, METHODID_GET_PROJECT_NUMBERS)))
          .build();
    }
  }

  /**
   */
  public static final class ProjectListServiceStub extends io.grpc.stub.AbstractStub<ProjectListServiceStub> {
    private ProjectListServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProjectListServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ProjectListServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProjectListServiceStub(channel, callOptions);
    }

    /**
     */
    public void getProjectList(NoParam request,
                               io.grpc.stub.StreamObserver<ProjectList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetProjectListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProjectNumbers(NoParam request,
                                  io.grpc.stub.StreamObserver<ProjectNumbers> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetProjectNumbersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProjectListServiceBlockingStub extends io.grpc.stub.AbstractStub<ProjectListServiceBlockingStub> {
    private ProjectListServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProjectListServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ProjectListServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProjectListServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ProjectList getProjectList(NoParam request) {
      return blockingUnaryCall(
          getChannel(), getGetProjectListMethod(), getCallOptions(), request);
    }

    /**
     */
    public ProjectNumbers getProjectNumbers(NoParam request) {
      return blockingUnaryCall(
          getChannel(), getGetProjectNumbersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProjectListServiceFutureStub extends io.grpc.stub.AbstractStub<ProjectListServiceFutureStub> {
    private ProjectListServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProjectListServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ProjectListServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProjectListServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ProjectList> getProjectList(
        NoParam request) {
      return futureUnaryCall(
          getChannel().newCall(getGetProjectListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ProjectNumbers> getProjectNumbers(
        NoParam request) {
      return futureUnaryCall(
          getChannel().newCall(getGetProjectNumbersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PROJECT_LIST = 0;
  private static final int METHODID_GET_PROJECT_NUMBERS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProjectListServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProjectListServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PROJECT_LIST:
          serviceImpl.getProjectList((NoParam) request,
              (io.grpc.stub.StreamObserver<ProjectList>) responseObserver);
          break;
        case METHODID_GET_PROJECT_NUMBERS:
          serviceImpl.getProjectNumbers((NoParam) request,
              (io.grpc.stub.StreamObserver<ProjectNumbers>) responseObserver);
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

  private static abstract class ProjectListServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProjectListServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ProjectListOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProjectListService");
    }
  }

  private static final class ProjectListServiceFileDescriptorSupplier
      extends ProjectListServiceBaseDescriptorSupplier {
    ProjectListServiceFileDescriptorSupplier() {}
  }

  private static final class ProjectListServiceMethodDescriptorSupplier
      extends ProjectListServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProjectListServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ProjectListServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProjectListServiceFileDescriptorSupplier())
              .addMethod(getGetProjectListMethod())
              .addMethod(getGetProjectNumbersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
