// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Group.proto

package com.bht.pim.proto.groups;

import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeOrBuilder;

/**
 * Protobuf type {@code com.bht.pim.proto.groups.Group}
 */
public final class Group extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:com.bht.pim.proto.groups.Group)
        GroupOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use Group.newBuilder() to construct.
    private Group(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Group() {
        id_ = 0L;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
        return unknownFields;
    }

    public static final int ID_FIELD_NUMBER = 1;
    public static final int LEADER_FIELD_NUMBER = 2;
    // @@protoc_insertion_point(class_scope:com.bht.pim.proto.groups.Group)
    private static final Group DEFAULT_INSTANCE;
    private long id_;
    private Employee leader_;

    private Group(
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
                    case 18: {
                        Employee.Builder subBuilder = null;
                        if (leader_ != null) {
                            subBuilder = leader_.toBuilder();
                        }
                        leader_ = input.readMessage(Employee.parser(), extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom(leader_);
                            leader_ = subBuilder.buildPartial();
                        }

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

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
        return GroupOuterClass.internal_static_com_bht_pim_proto_groups_Group_descriptor;
    }

    @Override
    protected FieldAccessorTable
    internalGetFieldAccessorTable() {
        return GroupOuterClass.internal_static_com_bht_pim_proto_groups_Group_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        Group.class, Group.Builder.class);
    }

    /**
     * <code>int64 id = 1;</code>
     */
    @Override
    public long getId() {
        return id_;
    }

    /**
     * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
     */
    @Override
    public boolean hasLeader() {
        return leader_ != null;
    }

    /**
     * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
     */
    @Override
    public Employee getLeader() {
        return leader_ == null ? Employee.getDefaultInstance() : leader_;
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

    /**
     * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
     */
    @Override
    public EmployeeOrBuilder getLeaderOrBuilder() {
        return getLeader();
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
            throws java.io.IOException {
        if (id_ != 0L) {
            output.writeInt64(1, id_);
        }
        if (leader_ != null) {
            output.writeMessage(2, getLeader());
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
        if (leader_ != null) {
            size += com.google.protobuf.CodedOutputStream
                    .computeMessageSize(2, getLeader());
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
        if (!(obj instanceof Group)) {
            return super.equals(obj);
        }
        Group other = (Group) obj;

        boolean result = true;
        result = result && (getId()
                == other.getId());
        result = result && (hasLeader() == other.hasLeader());
        if (hasLeader()) {
            result = result && getLeader()
                    .equals(other.getLeader());
        }
        result = result && unknownFields.equals(other.unknownFields);
        return result;
    }

    public static Group parseFrom(
            java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Group parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Group parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Group parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Group parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Group parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Group parseFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static Group parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Group parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input);
    }

    public static Group parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static Group parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static Group parseFrom(
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

    public static Builder newBuilder(Group prototype) {
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
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
                getId());
        if (hasLeader()) {
            hash = (37 * hash) + LEADER_FIELD_NUMBER;
            hash = (53 * hash) + getLeader().hashCode();
        }
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    /**
     * Protobuf type {@code com.bht.pim.proto.groups.Group}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:com.bht.pim.proto.groups.Group)
            GroupOrBuilder {
        private Employee leader_ = null;
        private com.google.protobuf.SingleFieldBuilderV3<
                Employee, Employee.Builder, EmployeeOrBuilder> leaderBuilder_;

        // Construct using com.bht.pim.proto.groups.Group.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
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

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return GroupOuterClass.internal_static_com_bht_pim_proto_groups_Group_descriptor;
        }

        @Override
        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return GroupOuterClass.internal_static_com_bht_pim_proto_groups_Group_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            Group.class, Group.Builder.class);
        }

        @Override
        public Group getDefaultInstanceForType() {
            return Group.getDefaultInstance();
        }

        @Override
        public Group build() {
            Group result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @Override
        public Builder clear() {
            super.clear();
            id_ = 0L;

            if (leaderBuilder_ == null) {
                leader_ = null;
            } else {
                leader_ = null;
                leaderBuilder_ = null;
            }
            return this;
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
            if (other instanceof Group) {
                return mergeFrom((Group) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        @Override
        public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
            return GroupOuterClass.internal_static_com_bht_pim_proto_groups_Group_descriptor;
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
            Group parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (Group) e.getUnfinishedMessage();
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

        @Override
        public Group buildPartial() {
            Group result = new Group(this);
            result.id_ = id_;
            if (leaderBuilder_ == null) {
                result.leader_ = leader_;
            } else {
                result.leader_ = leaderBuilder_.build();
            }
            onBuilt();
            return result;
        }

        public Builder mergeFrom(Group other) {
            if (other == Group.getDefaultInstance()) {
                return this;
            }
            if (other.getId() != 0L) {
                setId(other.getId());
            }
            if (other.hasLeader()) {
                mergeLeader(other.getLeader());
            }
            mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
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

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        @Override
        public boolean hasLeader() {
            return leaderBuilder_ != null || leader_ != null;
        }

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        @Override
        public Employee getLeader() {
            if (leaderBuilder_ == null) {
                return leader_ == null ? Employee.getDefaultInstance() : leader_;
            } else {
                return leaderBuilder_.getMessage();
            }
        }

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        public Builder setLeader(Employee value) {
            if (leaderBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                leader_ = value;
                onChanged();
            } else {
                leaderBuilder_.setMessage(value);
            }

            return this;
        }

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        public Builder setLeader(
                Employee.Builder builderForValue) {
            if (leaderBuilder_ == null) {
                leader_ = builderForValue.build();
                onChanged();
            } else {
                leaderBuilder_.setMessage(builderForValue.build());
            }

            return this;
        }

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        public Builder mergeLeader(Employee value) {
            if (leaderBuilder_ == null) {
                if (leader_ != null) {
                    leader_ =
                            Employee.newBuilder(leader_).mergeFrom(value).buildPartial();
                } else {
                    leader_ = value;
                }
                onChanged();
            } else {
                leaderBuilder_.mergeFrom(value);
            }

            return this;
        }

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        public Builder clearLeader() {
            if (leaderBuilder_ == null) {
                leader_ = null;
                onChanged();
            } else {
                leader_ = null;
                leaderBuilder_ = null;
            }

            return this;
        }

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        public Employee.Builder getLeaderBuilder() {

            onChanged();
            return getLeaderFieldBuilder().getBuilder();
        }

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        @Override
        public EmployeeOrBuilder getLeaderOrBuilder() {
            if (leaderBuilder_ != null) {
                return leaderBuilder_.getMessageOrBuilder();
            } else {
                return leader_ == null ?
                        Employee.getDefaultInstance() : leader_;
            }
        }

        /**
         * <code>.com.bht.pim.proto.employees.Employee leader = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<
                Employee, Employee.Builder, EmployeeOrBuilder>
        getLeaderFieldBuilder() {
            if (leaderBuilder_ == null) {
                leaderBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        getLeader(),
                        getParentForChildren(),
                        isClean());
                leader_ = null;
            }
            return leaderBuilder_;
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


        // @@protoc_insertion_point(builder_scope:com.bht.pim.proto.groups.Group)
    }

    static {
        DEFAULT_INSTANCE = new Group();
    }

    public static Group getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Group>
            PARSER = new com.google.protobuf.AbstractParser<Group>() {
        @Override
        public Group parsePartialFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new Group(input, extensionRegistry);
        }
    };

    public static com.google.protobuf.Parser<Group> parser() {
        return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<Group> getParserForType() {
        return PARSER;
    }

    @Override
    public Group getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}

