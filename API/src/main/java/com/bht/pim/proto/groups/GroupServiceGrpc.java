package com.bht.pim.proto.groups;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 *
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.16.1)",
        comments = "Source: GroupInfo.proto")
public final class GroupServiceGrpc {

    private GroupServiceGrpc() {
    }

    public static final String SERVICE_NAME = "com.bht.pim.proto.groups.GroupService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Int64Value,
            GroupInfo> getGetGroupByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "getGroupById",
            requestType = com.google.protobuf.Int64Value.class,
            responseType = GroupInfo.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.google.protobuf.Int64Value,
            GroupInfo> getGetGroupByIdMethod() {
        io.grpc.MethodDescriptor<com.google.protobuf.Int64Value, GroupInfo> getGetGroupByIdMethod;
        if ((getGetGroupByIdMethod = GroupServiceGrpc.getGetGroupByIdMethod) == null) {
            synchronized (GroupServiceGrpc.class) {
                if ((getGetGroupByIdMethod = GroupServiceGrpc.getGetGroupByIdMethod) == null) {
                    GroupServiceGrpc.getGetGroupByIdMethod = getGetGroupByIdMethod =
                            io.grpc.MethodDescriptor.<com.google.protobuf.Int64Value, GroupInfo>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.groups.GroupService", "getGroupById"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.Int64Value.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            GroupInfo.getDefaultInstance()))
                                    .setSchemaDescriptor(new GroupServiceMethodDescriptorSupplier("getGroupById"))
                                    .build();
                }
            }
        }
        return getGetGroupByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<Group,
            com.google.protobuf.BoolValue> getAddNewGroupMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "addNewGroup",
            requestType = Group.class,
            responseType = com.google.protobuf.BoolValue.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<Group,
            com.google.protobuf.BoolValue> getAddNewGroupMethod() {
        io.grpc.MethodDescriptor<Group, com.google.protobuf.BoolValue> getAddNewGroupMethod;
        if ((getAddNewGroupMethod = GroupServiceGrpc.getAddNewGroupMethod) == null) {
            synchronized (GroupServiceGrpc.class) {
                if ((getAddNewGroupMethod = GroupServiceGrpc.getAddNewGroupMethod) == null) {
                    GroupServiceGrpc.getAddNewGroupMethod = getAddNewGroupMethod =
                            io.grpc.MethodDescriptor.<Group, com.google.protobuf.BoolValue>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.groups.GroupService", "addNewGroup"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            Group.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.BoolValue.getDefaultInstance()))
                                    .setSchemaDescriptor(new GroupServiceMethodDescriptorSupplier("addNewGroup"))
                                    .build();
                }
            }
        }
        return getAddNewGroupMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static GroupServiceStub newStub(io.grpc.Channel channel) {
        return new GroupServiceStub(channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static GroupServiceBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        return new GroupServiceBlockingStub(channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static GroupServiceFutureStub newFutureStub(
            io.grpc.Channel channel) {
        return new GroupServiceFutureStub(channel);
    }

    /**
     *
     */
    public static abstract class GroupServiceImplBase implements io.grpc.BindableService {

        /**
         *
         */
        public void getGroupById(com.google.protobuf.Int64Value request,
                                 io.grpc.stub.StreamObserver<GroupInfo> responseObserver) {
            asyncUnimplementedUnaryCall(getGetGroupByIdMethod(), responseObserver);
        }

        /**
         *
         */
        public void addNewGroup(Group request,
                                io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
            asyncUnimplementedUnaryCall(getAddNewGroupMethod(), responseObserver);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getGetGroupByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.google.protobuf.Int64Value,
                                            GroupInfo>(
                                            this, METHODID_GET_GROUP_BY_ID)))
                    .addMethod(
                            getAddNewGroupMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            Group,
                                            com.google.protobuf.BoolValue>(
                                            this, METHODID_ADD_NEW_GROUP)))
                    .build();
        }
    }

    /**
     *
     */
    public static final class GroupServiceStub extends io.grpc.stub.AbstractStub<GroupServiceStub> {
        private GroupServiceStub(io.grpc.Channel channel) {
            super(channel);
        }

        private GroupServiceStub(io.grpc.Channel channel,
                                 io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected GroupServiceStub build(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
            return new GroupServiceStub(channel, callOptions);
        }

        /**
         *
         */
        public void getGroupById(com.google.protobuf.Int64Value request,
                                 io.grpc.stub.StreamObserver<GroupInfo> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetGroupByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void addNewGroup(Group request,
                                io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getAddNewGroupMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     *
     */
    public static final class GroupServiceBlockingStub extends io.grpc.stub.AbstractStub<GroupServiceBlockingStub> {
        private GroupServiceBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        private GroupServiceBlockingStub(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected GroupServiceBlockingStub build(io.grpc.Channel channel,
                                                 io.grpc.CallOptions callOptions) {
            return new GroupServiceBlockingStub(channel, callOptions);
        }

        /**
         *
         */
        public GroupInfo getGroupById(com.google.protobuf.Int64Value request) {
            return blockingUnaryCall(
                    getChannel(), getGetGroupByIdMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.google.protobuf.BoolValue addNewGroup(Group request) {
            return blockingUnaryCall(
                    getChannel(), getAddNewGroupMethod(), getCallOptions(), request);
        }
    }

    /**
     *
     */
    public static final class GroupServiceFutureStub extends io.grpc.stub.AbstractStub<GroupServiceFutureStub> {
        private GroupServiceFutureStub(io.grpc.Channel channel) {
            super(channel);
        }

        private GroupServiceFutureStub(io.grpc.Channel channel,
                                       io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected GroupServiceFutureStub build(io.grpc.Channel channel,
                                               io.grpc.CallOptions callOptions) {
            return new GroupServiceFutureStub(channel, callOptions);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<GroupInfo> getGroupById(
                com.google.protobuf.Int64Value request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetGroupByIdMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.BoolValue> addNewGroup(
                Group request) {
            return futureUnaryCall(
                    getChannel().newCall(getAddNewGroupMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_GET_GROUP_BY_ID = 0;
    private static final int METHODID_ADD_NEW_GROUP = 1;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final GroupServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(GroupServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_GROUP_BY_ID:
                    serviceImpl.getGroupById((com.google.protobuf.Int64Value) request,
                            (io.grpc.stub.StreamObserver<GroupInfo>) responseObserver);
                    break;
                case METHODID_ADD_NEW_GROUP:
                    serviceImpl.addNewGroup((Group) request,
                            (io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue>) responseObserver);
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

    private static abstract class GroupServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        GroupServiceBaseDescriptorSupplier() {
        }

        @Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return GroupInfoOuterClass.getDescriptor();
        }

        @Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("GroupService");
        }
    }

    private static final class GroupServiceFileDescriptorSupplier
            extends GroupServiceBaseDescriptorSupplier {
        GroupServiceFileDescriptorSupplier() {
        }
    }

    private static final class GroupServiceMethodDescriptorSupplier
            extends GroupServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        GroupServiceMethodDescriptorSupplier(String methodName) {
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
            synchronized (GroupServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new GroupServiceFileDescriptorSupplier())
                            .addMethod(getGetGroupByIdMethod())
                            .addMethod(getAddNewGroupMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
