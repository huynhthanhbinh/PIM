package com.bht.pim.proto.projects;

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
        comments = "Source: Project.proto")
public final class ProjectServiceGrpc {

    private ProjectServiceGrpc() {
    }

    public static final String SERVICE_NAME = "com.bht.pim.proto.projects.ProjectService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Int64Value,
            Project> getGetProjectByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "getProjectById",
            requestType = com.google.protobuf.Int64Value.class,
            responseType = Project.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.google.protobuf.Int64Value,
            Project> getGetProjectByIdMethod() {
        io.grpc.MethodDescriptor<com.google.protobuf.Int64Value, Project> getGetProjectByIdMethod;
        if ((getGetProjectByIdMethod = ProjectServiceGrpc.getGetProjectByIdMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getGetProjectByIdMethod = ProjectServiceGrpc.getGetProjectByIdMethod) == null) {
                    ProjectServiceGrpc.getGetProjectByIdMethod = getGetProjectByIdMethod =
                            io.grpc.MethodDescriptor.<com.google.protobuf.Int64Value, Project>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "getProjectById"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.Int64Value.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            Project.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("getProjectById"))
                                    .build();
                }
            }
        }
        return getGetProjectByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<Project,
            com.google.protobuf.BoolValue> getAddNewProjectMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "addNewProject",
            requestType = Project.class,
            responseType = com.google.protobuf.BoolValue.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<Project,
            com.google.protobuf.BoolValue> getAddNewProjectMethod() {
        io.grpc.MethodDescriptor<Project, com.google.protobuf.BoolValue> getAddNewProjectMethod;
        if ((getAddNewProjectMethod = ProjectServiceGrpc.getAddNewProjectMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getAddNewProjectMethod = ProjectServiceGrpc.getAddNewProjectMethod) == null) {
                    ProjectServiceGrpc.getAddNewProjectMethod = getAddNewProjectMethod =
                            io.grpc.MethodDescriptor.<Project, com.google.protobuf.BoolValue>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "addNewProject"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            Project.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.BoolValue.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("addNewProject"))
                                    .build();
                }
            }
        }
        return getAddNewProjectMethod;
    }

    private static volatile io.grpc.MethodDescriptor<Project,
            com.google.protobuf.BoolValue> getEditProjectMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "editProject",
            requestType = Project.class,
            responseType = com.google.protobuf.BoolValue.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<Project,
            com.google.protobuf.BoolValue> getEditProjectMethod() {
        io.grpc.MethodDescriptor<Project, com.google.protobuf.BoolValue> getEditProjectMethod;
        if ((getEditProjectMethod = ProjectServiceGrpc.getEditProjectMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getEditProjectMethod = ProjectServiceGrpc.getEditProjectMethod) == null) {
                    ProjectServiceGrpc.getEditProjectMethod = getEditProjectMethod =
                            io.grpc.MethodDescriptor.<Project, com.google.protobuf.BoolValue>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "editProject"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            Project.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.BoolValue.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("editProject"))
                                    .build();
                }
            }
        }
        return getEditProjectMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Int64Value,
            com.google.protobuf.BoolValue> getDeleteProjectMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "deleteProject",
            requestType = com.google.protobuf.Int64Value.class,
            responseType = com.google.protobuf.BoolValue.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.google.protobuf.Int64Value,
            com.google.protobuf.BoolValue> getDeleteProjectMethod() {
        io.grpc.MethodDescriptor<com.google.protobuf.Int64Value, com.google.protobuf.BoolValue> getDeleteProjectMethod;
        if ((getDeleteProjectMethod = ProjectServiceGrpc.getDeleteProjectMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getDeleteProjectMethod = ProjectServiceGrpc.getDeleteProjectMethod) == null) {
                    ProjectServiceGrpc.getDeleteProjectMethod = getDeleteProjectMethod =
                            io.grpc.MethodDescriptor.<com.google.protobuf.Int64Value, com.google.protobuf.BoolValue>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "deleteProject"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.Int64Value.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.BoolValue.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("deleteProject"))
                                    .build();
                }
            }
        }
        return getDeleteProjectMethod;
    }

    private static volatile io.grpc.MethodDescriptor<ProjectPagination,
            ProjectList> getGetProjectListMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "getProjectList",
            requestType = ProjectPagination.class,
            responseType = ProjectList.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<ProjectPagination,
            ProjectList> getGetProjectListMethod() {
        io.grpc.MethodDescriptor<ProjectPagination, ProjectList> getGetProjectListMethod;
        if ((getGetProjectListMethod = ProjectServiceGrpc.getGetProjectListMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getGetProjectListMethod = ProjectServiceGrpc.getGetProjectListMethod) == null) {
                    ProjectServiceGrpc.getGetProjectListMethod = getGetProjectListMethod =
                            io.grpc.MethodDescriptor.<ProjectPagination, ProjectList>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "getProjectList"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            ProjectPagination.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            ProjectList.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("getProjectList"))
                                    .build();
                }
            }
        }
        return getGetProjectListMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
            ProjectNumbers> getGetProjectNumbersMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "getProjectNumbers",
            requestType = com.google.protobuf.Empty.class,
            responseType = ProjectNumbers.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
            ProjectNumbers> getGetProjectNumbersMethod() {
        io.grpc.MethodDescriptor<com.google.protobuf.Empty, ProjectNumbers> getGetProjectNumbersMethod;
        if ((getGetProjectNumbersMethod = ProjectServiceGrpc.getGetProjectNumbersMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getGetProjectNumbersMethod = ProjectServiceGrpc.getGetProjectNumbersMethod) == null) {
                    ProjectServiceGrpc.getGetProjectNumbersMethod = getGetProjectNumbersMethod =
                            io.grpc.MethodDescriptor.<com.google.protobuf.Empty, ProjectNumbers>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "getProjectNumbers"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.Empty.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            ProjectNumbers.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("getProjectNumbers"))
                                    .build();
                }
            }
        }
        return getGetProjectNumbersMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
            com.google.protobuf.Int64Value> getGetNumberOfProjectsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "getNumberOfProjects",
            requestType = com.google.protobuf.Empty.class,
            responseType = com.google.protobuf.Int64Value.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
            com.google.protobuf.Int64Value> getGetNumberOfProjectsMethod() {
        io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.google.protobuf.Int64Value> getGetNumberOfProjectsMethod;
        if ((getGetNumberOfProjectsMethod = ProjectServiceGrpc.getGetNumberOfProjectsMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getGetNumberOfProjectsMethod = ProjectServiceGrpc.getGetNumberOfProjectsMethod) == null) {
                    ProjectServiceGrpc.getGetNumberOfProjectsMethod = getGetNumberOfProjectsMethod =
                            io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.google.protobuf.Int64Value>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "getNumberOfProjects"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.Empty.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.Int64Value.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("getNumberOfProjects"))
                                    .build();
                }
            }
        }
        return getGetNumberOfProjectsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
            com.google.protobuf.Int64Value> getGetNumberOfProjectsByStatusMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "getNumberOfProjectsByStatus",
            requestType = com.google.protobuf.StringValue.class,
            responseType = com.google.protobuf.Int64Value.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
            com.google.protobuf.Int64Value> getGetNumberOfProjectsByStatusMethod() {
        io.grpc.MethodDescriptor<com.google.protobuf.StringValue, com.google.protobuf.Int64Value> getGetNumberOfProjectsByStatusMethod;
        if ((getGetNumberOfProjectsByStatusMethod = ProjectServiceGrpc.getGetNumberOfProjectsByStatusMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getGetNumberOfProjectsByStatusMethod = ProjectServiceGrpc.getGetNumberOfProjectsByStatusMethod) == null) {
                    ProjectServiceGrpc.getGetNumberOfProjectsByStatusMethod = getGetNumberOfProjectsByStatusMethod =
                            io.grpc.MethodDescriptor.<com.google.protobuf.StringValue, com.google.protobuf.Int64Value>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "getNumberOfProjectsByStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.StringValue.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.Int64Value.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("getNumberOfProjectsByStatus"))
                                    .build();
                }
            }
        }
        return getGetNumberOfProjectsByStatusMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
            com.google.protobuf.Int64Value> getGetNumberOfProjectsByKeywordMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "getNumberOfProjectsByKeyword",
            requestType = com.google.protobuf.StringValue.class,
            responseType = com.google.protobuf.Int64Value.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
            com.google.protobuf.Int64Value> getGetNumberOfProjectsByKeywordMethod() {
        io.grpc.MethodDescriptor<com.google.protobuf.StringValue, com.google.protobuf.Int64Value> getGetNumberOfProjectsByKeywordMethod;
        if ((getGetNumberOfProjectsByKeywordMethod = ProjectServiceGrpc.getGetNumberOfProjectsByKeywordMethod) == null) {
            synchronized (ProjectServiceGrpc.class) {
                if ((getGetNumberOfProjectsByKeywordMethod = ProjectServiceGrpc.getGetNumberOfProjectsByKeywordMethod) == null) {
                    ProjectServiceGrpc.getGetNumberOfProjectsByKeywordMethod = getGetNumberOfProjectsByKeywordMethod =
                            io.grpc.MethodDescriptor.<com.google.protobuf.StringValue, com.google.protobuf.Int64Value>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "com.bht.pim.proto.projects.ProjectService", "getNumberOfProjectsByKeyword"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.StringValue.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.google.protobuf.Int64Value.getDefaultInstance()))
                                    .setSchemaDescriptor(new ProjectServiceMethodDescriptorSupplier("getNumberOfProjectsByKeyword"))
                                    .build();
                }
            }
        }
        return getGetNumberOfProjectsByKeywordMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static ProjectServiceStub newStub(io.grpc.Channel channel) {
        return new ProjectServiceStub(channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static ProjectServiceBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        return new ProjectServiceBlockingStub(channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static ProjectServiceFutureStub newFutureStub(
            io.grpc.Channel channel) {
        return new ProjectServiceFutureStub(channel);
    }

    /**
     *
     */
    public static abstract class ProjectServiceImplBase implements io.grpc.BindableService {

        /**
         *
         */
        public void getProjectById(com.google.protobuf.Int64Value request,
                                   io.grpc.stub.StreamObserver<Project> responseObserver) {
            asyncUnimplementedUnaryCall(getGetProjectByIdMethod(), responseObserver);
        }

        /**
         *
         */
        public void addNewProject(Project request,
                                  io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
            asyncUnimplementedUnaryCall(getAddNewProjectMethod(), responseObserver);
        }

        /**
         *
         */
        public void editProject(Project request,
                                io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
            asyncUnimplementedUnaryCall(getEditProjectMethod(), responseObserver);
        }

        /**
         *
         */
        public void deleteProject(com.google.protobuf.Int64Value request,
                                  io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
            asyncUnimplementedUnaryCall(getDeleteProjectMethod(), responseObserver);
        }

        /**
         *
         */
        public void getProjectList(ProjectPagination request,
                                   io.grpc.stub.StreamObserver<ProjectList> responseObserver) {
            asyncUnimplementedUnaryCall(getGetProjectListMethod(), responseObserver);
        }

        /**
         *
         */
        public void getProjectNumbers(com.google.protobuf.Empty request,
                                      io.grpc.stub.StreamObserver<ProjectNumbers> responseObserver) {
            asyncUnimplementedUnaryCall(getGetProjectNumbersMethod(), responseObserver);
        }

        /**
         *
         */
        public void getNumberOfProjects(com.google.protobuf.Empty request,
                                        io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value> responseObserver) {
            asyncUnimplementedUnaryCall(getGetNumberOfProjectsMethod(), responseObserver);
        }

        /**
         *
         */
        public void getNumberOfProjectsByStatus(com.google.protobuf.StringValue request,
                                                io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value> responseObserver) {
            asyncUnimplementedUnaryCall(getGetNumberOfProjectsByStatusMethod(), responseObserver);
        }

        /**
         *
         */
        public void getNumberOfProjectsByKeyword(com.google.protobuf.StringValue request,
                                                 io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value> responseObserver) {
            asyncUnimplementedUnaryCall(getGetNumberOfProjectsByKeywordMethod(), responseObserver);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getGetProjectByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.google.protobuf.Int64Value,
                                            Project>(
                                            this, METHODID_GET_PROJECT_BY_ID)))
                    .addMethod(
                            getAddNewProjectMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            Project,
                                            com.google.protobuf.BoolValue>(
                                            this, METHODID_ADD_NEW_PROJECT)))
                    .addMethod(
                            getEditProjectMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            Project,
                                            com.google.protobuf.BoolValue>(
                                            this, METHODID_EDIT_PROJECT)))
                    .addMethod(
                            getDeleteProjectMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.google.protobuf.Int64Value,
                                            com.google.protobuf.BoolValue>(
                                            this, METHODID_DELETE_PROJECT)))
                    .addMethod(
                            getGetProjectListMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            ProjectPagination,
                                            ProjectList>(
                                            this, METHODID_GET_PROJECT_LIST)))
                    .addMethod(
                            getGetProjectNumbersMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.google.protobuf.Empty,
                                            ProjectNumbers>(
                                            this, METHODID_GET_PROJECT_NUMBERS)))
                    .addMethod(
                            getGetNumberOfProjectsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.google.protobuf.Empty,
                                            com.google.protobuf.Int64Value>(
                                            this, METHODID_GET_NUMBER_OF_PROJECTS)))
                    .addMethod(
                            getGetNumberOfProjectsByStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.google.protobuf.StringValue,
                                            com.google.protobuf.Int64Value>(
                                            this, METHODID_GET_NUMBER_OF_PROJECTS_BY_STATUS)))
                    .addMethod(
                            getGetNumberOfProjectsByKeywordMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.google.protobuf.StringValue,
                                            com.google.protobuf.Int64Value>(
                                            this, METHODID_GET_NUMBER_OF_PROJECTS_BY_KEYWORD)))
                    .build();
        }
    }

    /**
     *
     */
    public static final class ProjectServiceStub extends io.grpc.stub.AbstractStub<ProjectServiceStub> {
        private ProjectServiceStub(io.grpc.Channel channel) {
            super(channel);
        }

        private ProjectServiceStub(io.grpc.Channel channel,
                                   io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected ProjectServiceStub build(io.grpc.Channel channel,
                                           io.grpc.CallOptions callOptions) {
            return new ProjectServiceStub(channel, callOptions);
        }

        /**
         *
         */
        public void getProjectById(com.google.protobuf.Int64Value request,
                                   io.grpc.stub.StreamObserver<Project> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetProjectByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void addNewProject(Project request,
                                  io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getAddNewProjectMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void editProject(Project request,
                                io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getEditProjectMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void deleteProject(com.google.protobuf.Int64Value request,
                                  io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDeleteProjectMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getProjectList(ProjectPagination request,
                                   io.grpc.stub.StreamObserver<ProjectList> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetProjectListMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getProjectNumbers(com.google.protobuf.Empty request,
                                      io.grpc.stub.StreamObserver<ProjectNumbers> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetProjectNumbersMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getNumberOfProjects(com.google.protobuf.Empty request,
                                        io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetNumberOfProjectsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getNumberOfProjectsByStatus(com.google.protobuf.StringValue request,
                                                io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetNumberOfProjectsByStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getNumberOfProjectsByKeyword(com.google.protobuf.StringValue request,
                                                 io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetNumberOfProjectsByKeywordMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     *
     */
    public static final class ProjectServiceBlockingStub extends io.grpc.stub.AbstractStub<ProjectServiceBlockingStub> {
        private ProjectServiceBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        private ProjectServiceBlockingStub(io.grpc.Channel channel,
                                           io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected ProjectServiceBlockingStub build(io.grpc.Channel channel,
                                                   io.grpc.CallOptions callOptions) {
            return new ProjectServiceBlockingStub(channel, callOptions);
        }

        /**
         *
         */
        public Project getProjectById(com.google.protobuf.Int64Value request) {
            return blockingUnaryCall(
                    getChannel(), getGetProjectByIdMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.google.protobuf.BoolValue addNewProject(Project request) {
            return blockingUnaryCall(
                    getChannel(), getAddNewProjectMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.google.protobuf.BoolValue editProject(Project request) {
            return blockingUnaryCall(
                    getChannel(), getEditProjectMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.google.protobuf.BoolValue deleteProject(com.google.protobuf.Int64Value request) {
            return blockingUnaryCall(
                    getChannel(), getDeleteProjectMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public ProjectList getProjectList(ProjectPagination request) {
            return blockingUnaryCall(
                    getChannel(), getGetProjectListMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public ProjectNumbers getProjectNumbers(com.google.protobuf.Empty request) {
            return blockingUnaryCall(
                    getChannel(), getGetProjectNumbersMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.google.protobuf.Int64Value getNumberOfProjects(com.google.protobuf.Empty request) {
            return blockingUnaryCall(
                    getChannel(), getGetNumberOfProjectsMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.google.protobuf.Int64Value getNumberOfProjectsByStatus(com.google.protobuf.StringValue request) {
            return blockingUnaryCall(
                    getChannel(), getGetNumberOfProjectsByStatusMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.google.protobuf.Int64Value getNumberOfProjectsByKeyword(com.google.protobuf.StringValue request) {
            return blockingUnaryCall(
                    getChannel(), getGetNumberOfProjectsByKeywordMethod(), getCallOptions(), request);
        }
    }

    /**
     *
     */
    public static final class ProjectServiceFutureStub extends io.grpc.stub.AbstractStub<ProjectServiceFutureStub> {
        private ProjectServiceFutureStub(io.grpc.Channel channel) {
            super(channel);
        }

        private ProjectServiceFutureStub(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected ProjectServiceFutureStub build(io.grpc.Channel channel,
                                                 io.grpc.CallOptions callOptions) {
            return new ProjectServiceFutureStub(channel, callOptions);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<Project> getProjectById(
                com.google.protobuf.Int64Value request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetProjectByIdMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.BoolValue> addNewProject(
                Project request) {
            return futureUnaryCall(
                    getChannel().newCall(getAddNewProjectMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.BoolValue> editProject(
                Project request) {
            return futureUnaryCall(
                    getChannel().newCall(getEditProjectMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.BoolValue> deleteProject(
                com.google.protobuf.Int64Value request) {
            return futureUnaryCall(
                    getChannel().newCall(getDeleteProjectMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<ProjectList> getProjectList(
                ProjectPagination request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetProjectListMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<ProjectNumbers> getProjectNumbers(
                com.google.protobuf.Empty request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetProjectNumbersMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Int64Value> getNumberOfProjects(
                com.google.protobuf.Empty request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetNumberOfProjectsMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Int64Value> getNumberOfProjectsByStatus(
                com.google.protobuf.StringValue request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetNumberOfProjectsByStatusMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Int64Value> getNumberOfProjectsByKeyword(
                com.google.protobuf.StringValue request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetNumberOfProjectsByKeywordMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_GET_PROJECT_BY_ID = 0;
    private static final int METHODID_ADD_NEW_PROJECT = 1;
    private static final int METHODID_EDIT_PROJECT = 2;
    private static final int METHODID_DELETE_PROJECT = 3;
    private static final int METHODID_GET_PROJECT_LIST = 4;
    private static final int METHODID_GET_PROJECT_NUMBERS = 5;
    private static final int METHODID_GET_NUMBER_OF_PROJECTS = 6;
    private static final int METHODID_GET_NUMBER_OF_PROJECTS_BY_STATUS = 7;
    private static final int METHODID_GET_NUMBER_OF_PROJECTS_BY_KEYWORD = 8;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final ProjectServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(ProjectServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_PROJECT_BY_ID:
                    serviceImpl.getProjectById((com.google.protobuf.Int64Value) request,
                            (io.grpc.stub.StreamObserver<Project>) responseObserver);
                    break;
                case METHODID_ADD_NEW_PROJECT:
                    serviceImpl.addNewProject((Project) request,
                            (io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue>) responseObserver);
                    break;
                case METHODID_EDIT_PROJECT:
                    serviceImpl.editProject((Project) request,
                            (io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue>) responseObserver);
                    break;
                case METHODID_DELETE_PROJECT:
                    serviceImpl.deleteProject((com.google.protobuf.Int64Value) request,
                            (io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue>) responseObserver);
                    break;
                case METHODID_GET_PROJECT_LIST:
                    serviceImpl.getProjectList((ProjectPagination) request,
                            (io.grpc.stub.StreamObserver<ProjectList>) responseObserver);
                    break;
                case METHODID_GET_PROJECT_NUMBERS:
                    serviceImpl.getProjectNumbers((com.google.protobuf.Empty) request,
                            (io.grpc.stub.StreamObserver<ProjectNumbers>) responseObserver);
                    break;
                case METHODID_GET_NUMBER_OF_PROJECTS:
                    serviceImpl.getNumberOfProjects((com.google.protobuf.Empty) request,
                            (io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value>) responseObserver);
                    break;
                case METHODID_GET_NUMBER_OF_PROJECTS_BY_STATUS:
                    serviceImpl.getNumberOfProjectsByStatus((com.google.protobuf.StringValue) request,
                            (io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value>) responseObserver);
                    break;
                case METHODID_GET_NUMBER_OF_PROJECTS_BY_KEYWORD:
                    serviceImpl.getNumberOfProjectsByKeyword((com.google.protobuf.StringValue) request,
                            (io.grpc.stub.StreamObserver<com.google.protobuf.Int64Value>) responseObserver);
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

    private static abstract class ProjectServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        ProjectServiceBaseDescriptorSupplier() {
        }

        @Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return ProjectOuterClass.getDescriptor();
        }

        @Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("ProjectService");
        }
    }

    private static final class ProjectServiceFileDescriptorSupplier
            extends ProjectServiceBaseDescriptorSupplier {
        ProjectServiceFileDescriptorSupplier() {
        }
    }

    private static final class ProjectServiceMethodDescriptorSupplier
            extends ProjectServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        ProjectServiceMethodDescriptorSupplier(String methodName) {
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
            synchronized (ProjectServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new ProjectServiceFileDescriptorSupplier())
                            .addMethod(getGetProjectByIdMethod())
                            .addMethod(getAddNewProjectMethod())
                            .addMethod(getEditProjectMethod())
                            .addMethod(getDeleteProjectMethod())
                            .addMethod(getGetProjectListMethod())
                            .addMethod(getGetProjectNumbersMethod())
                            .addMethod(getGetNumberOfProjectsMethod())
                            .addMethod(getGetNumberOfProjectsByStatusMethod())
                            .addMethod(getGetNumberOfProjectsByKeywordMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
