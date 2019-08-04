// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EmployeeList.proto

package com.bht.pim.proto.employees;

/**
 * Protobuf service {@code com.bht.pim.proto.employees.EmployeeListService}
 */
public abstract class EmployeeListService
        implements com.google.protobuf.Service {
    protected EmployeeListService() {
    }

    public static com.google.protobuf.Service newReflectiveService(
            final Interface impl) {
        return new EmployeeListService() {
            @Override
            public void getEmployeeList(
                    com.google.protobuf.RpcController controller,
                    NoParam request,
                    com.google.protobuf.RpcCallback<EmployeeList> done) {
                impl.getEmployeeList(controller, request, done);
            }

        };
    }

    public static com.google.protobuf.BlockingService
    newReflectiveBlockingService(final BlockingInterface impl) {
        return new com.google.protobuf.BlockingService() {
            public final com.google.protobuf.Descriptors.ServiceDescriptor
            getDescriptorForType() {
                return getDescriptor();
            }

            public final com.google.protobuf.Message callBlockingMethod(
                    com.google.protobuf.Descriptors.MethodDescriptor method,
                    com.google.protobuf.RpcController controller,
                    com.google.protobuf.Message request)
                    throws com.google.protobuf.ServiceException {
                if (method.getService() != getDescriptor()) {
                    throw new IllegalArgumentException(
                            "Service.callBlockingMethod() given method descriptor for " +
                                    "wrong service type.");
                }
                switch (method.getIndex()) {
                    case 0:
                        return impl.getEmployeeList(controller, (NoParam) request);
                    default:
                        throw new AssertionError("Can't get here.");
                }
            }

            public final com.google.protobuf.Message
            getRequestPrototype(
                    com.google.protobuf.Descriptors.MethodDescriptor method) {
                if (method.getService() != getDescriptor()) {
                    throw new IllegalArgumentException(
                            "Service.getRequestPrototype() given method " +
                                    "descriptor for wrong service type.");
                }
                switch (method.getIndex()) {
                    case 0:
                        return NoParam.getDefaultInstance();
                    default:
                        throw new AssertionError("Can't get here.");
                }
            }

            public final com.google.protobuf.Message
            getResponsePrototype(
                    com.google.protobuf.Descriptors.MethodDescriptor method) {
                if (method.getService() != getDescriptor()) {
                    throw new IllegalArgumentException(
                            "Service.getResponsePrototype() given method " +
                                    "descriptor for wrong service type.");
                }
                switch (method.getIndex()) {
                    case 0:
                        return EmployeeList.getDefaultInstance();
                    default:
                        throw new AssertionError("Can't get here.");
                }
            }

        };
    }

    public static final com.google.protobuf.Descriptors.ServiceDescriptor
    getDescriptor() {
        return EmployeeListOuterClass.getDescriptor().getServices().get(0);
    }

    public static Stub newStub(
            com.google.protobuf.RpcChannel channel) {
        return new Stub(channel);
    }

    public static BlockingInterface newBlockingStub(
            com.google.protobuf.BlockingRpcChannel channel) {
        return new BlockingStub(channel);
    }

    /**
     * <code>rpc getEmployeeList(.com.bht.pim.proto.employees.NoParam) returns (.com.bht.pim.proto.employees.EmployeeList);</code>
     */
    public abstract void getEmployeeList(
            com.google.protobuf.RpcController controller,
            NoParam request,
            com.google.protobuf.RpcCallback<EmployeeList> done);

    public final com.google.protobuf.Descriptors.ServiceDescriptor
    getDescriptorForType() {
        return getDescriptor();
    }

    public final void callMethod(
            com.google.protobuf.Descriptors.MethodDescriptor method,
            com.google.protobuf.RpcController controller,
            com.google.protobuf.Message request,
            com.google.protobuf.RpcCallback<
                    com.google.protobuf.Message> done) {
        if (method.getService() != getDescriptor()) {
            throw new IllegalArgumentException(
                    "Service.callMethod() given method descriptor for wrong " +
                            "service type.");
        }
        switch (method.getIndex()) {
            case 0:
                this.getEmployeeList(controller, (NoParam) request,
                        com.google.protobuf.RpcUtil.<EmployeeList>specializeCallback(
                                done));
                return;
            default:
                throw new AssertionError("Can't get here.");
        }
    }

    public final com.google.protobuf.Message
    getRequestPrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
        if (method.getService() != getDescriptor()) {
            throw new IllegalArgumentException(
                    "Service.getRequestPrototype() given method " +
                            "descriptor for wrong service type.");
        }
        switch (method.getIndex()) {
            case 0:
                return NoParam.getDefaultInstance();
            default:
                throw new AssertionError("Can't get here.");
        }
    }

    public final com.google.protobuf.Message
    getResponsePrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
        if (method.getService() != getDescriptor()) {
            throw new IllegalArgumentException(
                    "Service.getResponsePrototype() given method " +
                            "descriptor for wrong service type.");
        }
        switch (method.getIndex()) {
            case 0:
                return EmployeeList.getDefaultInstance();
            default:
                throw new AssertionError("Can't get here.");
        }
    }

    public interface Interface {
        /**
         * <code>rpc getEmployeeList(.com.bht.pim.proto.employees.NoParam) returns (.com.bht.pim.proto.employees.EmployeeList);</code>
         */
        public abstract void getEmployeeList(
                com.google.protobuf.RpcController controller,
                NoParam request,
                com.google.protobuf.RpcCallback<EmployeeList> done);

    }

    public interface BlockingInterface {
        public EmployeeList getEmployeeList(
                com.google.protobuf.RpcController controller,
                NoParam request)
                throws com.google.protobuf.ServiceException;
    }

    public static final class Stub extends EmployeeListService implements Interface {
        private final com.google.protobuf.RpcChannel channel;

        private Stub(com.google.protobuf.RpcChannel channel) {
            this.channel = channel;
        }

        public com.google.protobuf.RpcChannel getChannel() {
            return channel;
        }

        public void getEmployeeList(
                com.google.protobuf.RpcController controller,
                NoParam request,
                com.google.protobuf.RpcCallback<EmployeeList> done) {
            channel.callMethod(
                    getDescriptor().getMethods().get(0),
                    controller,
                    request,
                    EmployeeList.getDefaultInstance(),
                    com.google.protobuf.RpcUtil.generalizeCallback(
                            done,
                            EmployeeList.class,
                            EmployeeList.getDefaultInstance()));
        }
    }

    private static final class BlockingStub implements BlockingInterface {
        private final com.google.protobuf.BlockingRpcChannel channel;

        private BlockingStub(com.google.protobuf.BlockingRpcChannel channel) {
            this.channel = channel;
        }

        public EmployeeList getEmployeeList(
                com.google.protobuf.RpcController controller,
                NoParam request)
                throws com.google.protobuf.ServiceException {
            return (EmployeeList) channel.callBlockingMethod(
                    getDescriptor().getMethods().get(0),
                    controller,
                    request,
                    EmployeeList.getDefaultInstance());
        }

    }

    // @@protoc_insertion_point(class_scope:com.bht.pim.proto.employees.EmployeeListService)
}

