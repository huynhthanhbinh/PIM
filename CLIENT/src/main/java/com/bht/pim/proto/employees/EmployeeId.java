// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EmployeeInfo.proto

package com.bht.pim.proto.employees;

/**
 * Protobuf type {@code com.bht.pim.proto.employees.EmployeeId}
 */
public final class EmployeeId extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:com.bht.pim.proto.employees.EmployeeId)
        EmployeeIdOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use EmployeeId.newBuilder() to construct.
    private EmployeeId(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private EmployeeId() {
        id_ = 0L;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
        return unknownFields;
    }

    private EmployeeId(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                com.google.protobuf.UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            while (!done) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        done = true;
                        break;
                    default: {
                        if (!parseUnknownFieldProto3(
                                input, unknownFields, extensionRegistry, tag)) {
                            done = true;
                        }
                        break;
                    }
                    case 8: {

                        id_ = input.readInt64();
                        break;
                    }
                }
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(
                    e).setUnfinishedMessage(this);
        } finally {
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    // @@protoc_insertion_point(class_scope:com.bht.pim.proto.employees.EmployeeId)
    private static final EmployeeId DEFAULT_INSTANCE;

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
        return EmployeeInfoOuterClass.internal_static_com_bht_pim_proto_employees_EmployeeId_descriptor;
    }

    public static final int ID_FIELD_NUMBER = 1;
    private long id_;

    /**
     * <code>int64 id = 1;</code>
     */
    @Override
    public long getId() {
        return id_;
    }

    private byte memoizedIsInitialized = -1;

    @Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
            throws java.io.IOException {
        if (id_ != 0L) {
            output.writeInt64(1, id_);
        }
        unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (id_ != 0L) {
            size += com.google.protobuf.CodedOutputStream
                    .computeInt64Size(1, id_);
        }
        size += unknownFields.getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EmployeeId)) {
            return super.equals(obj);
        }
        EmployeeId other = (EmployeeId) obj;

        boolean result = true;
        result = result && (getId()
                == other.getId());
        result = result && unknownFields.equals(other.unknownFields);
        return result;
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
                getId());
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static EmployeeId parseFrom(
            java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static EmployeeId parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static EmployeeId parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static EmployeeId parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static EmployeeId parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static EmployeeId parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static EmployeeId parseFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static EmployeeId parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static EmployeeId parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input);
    }

    public static EmployeeId parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static EmployeeId parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static EmployeeId parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EmployeeId prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
            BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    @Override
    protected FieldAccessorTable
    internalGetFieldAccessorTable() {
        return EmployeeInfoOuterClass.internal_static_com_bht_pim_proto_employees_EmployeeId_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        EmployeeId.class, EmployeeId.Builder.class);
    }

    /**
     * Protobuf type {@code com.bht.pim.proto.employees.EmployeeId}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:com.bht.pim.proto.employees.EmployeeId)
            EmployeeIdOrBuilder {
        // Construct using com.bht.pim.proto.employees.EmployeeId.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return EmployeeInfoOuterClass.internal_static_com_bht_pim_proto_employees_EmployeeId_descriptor;
        }

        @Override
        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return EmployeeInfoOuterClass.internal_static_com_bht_pim_proto_employees_EmployeeId_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            EmployeeId.class, EmployeeId.Builder.class);
        }

        private Builder(
                BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3
                    .alwaysUseFieldBuilders) {
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            id_ = 0L;

            return this;
        }

        @Override
        public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
            return EmployeeInfoOuterClass.internal_static_com_bht_pim_proto_employees_EmployeeId_descriptor;
        }

        @Override
        public EmployeeId getDefaultInstanceForType() {
            return EmployeeId.getDefaultInstance();
        }

        @Override
        public EmployeeId build() {
            EmployeeId result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @Override
        public EmployeeId buildPartial() {
            EmployeeId result = new EmployeeId(this);
            result.id_ = id_;
            onBuilt();
            return result;
        }

        @Override
        public Builder clone() {
            return (Builder) super.clone();
        }

        @Override
        public Builder setField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                Object value) {
            return (Builder) super.setField(field, value);
        }

        @Override
        public Builder clearField(
                com.google.protobuf.Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        @Override
        public Builder clearOneof(
                com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        @Override
        public Builder setRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        @Override
        public Builder addRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        @Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof EmployeeId) {
                return mergeFrom((EmployeeId) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(EmployeeId other) {
            if (other == EmployeeId.getDefaultInstance()) {
                return this;
            }
            if (other.getId() != 0L) {
                setId(other.getId());
            }
            mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        @Override
        public final boolean isInitialized() {
            return true;
        }

        @Override
        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            EmployeeId parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (EmployeeId) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private long id_;

        /**
         * <code>int64 id = 1;</code>
         */
        @Override
        public long getId() {
            return id_;
        }

        /**
         * <code>int64 id = 1;</code>
         */
        public Builder setId(long value) {

            id_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>int64 id = 1;</code>
         */
        public Builder clearId() {

            id_ = 0L;
            onChanged();
            return this;
        }

        @Override
        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFieldsProto3(unknownFields);
        }

        @Override
        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }


        // @@protoc_insertion_point(builder_scope:com.bht.pim.proto.employees.EmployeeId)
    }

    static {
        DEFAULT_INSTANCE = new EmployeeId();
    }

    public static EmployeeId getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<EmployeeId>
            PARSER = new com.google.protobuf.AbstractParser<EmployeeId>() {
        @Override
        public EmployeeId parsePartialFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new EmployeeId(input, extensionRegistry);
        }
    };

    public static com.google.protobuf.Parser<EmployeeId> parser() {
        return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<EmployeeId> getParserForType() {
        return PARSER;
    }

    @Override
    public EmployeeId getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}

