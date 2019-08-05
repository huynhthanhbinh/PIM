// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProjectNumbers.proto

package com.bht.pim.proto.projects;

/**
 * Protobuf type {@code com.bht.pim.proto.projects.ProjectNumbers}
 */
public final class ProjectNumbers extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:com.bht.pim.proto.projects.ProjectNumbers)
        ProjectNumbersOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use ProjectNumbers.newBuilder() to construct.
    private ProjectNumbers(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    public static final int PROJECTNUMBERS_FIELD_NUMBER = 1;

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
        return unknownFields;
    }

    // @@protoc_insertion_point(class_scope:com.bht.pim.proto.projects.ProjectNumbers)
    private static final ProjectNumbers DEFAULT_INSTANCE;
    private java.util.List<Long> projectNumbers_;
    private int projectNumbersMemoizedSerializedSize = -1;

    private ProjectNumbers() {
        projectNumbers_ = java.util.Collections.emptyList();
    }

    private ProjectNumbers(
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
                        if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                            projectNumbers_ = new java.util.ArrayList<>();
                            mutable_bitField0_ |= 0x00000001;
                        }
                        projectNumbers_.add(input.readInt64());
                        break;
                    }
                    case 10: {
                        int length = input.readRawVarint32();
                        int limit = input.pushLimit(length);
                        if (!((mutable_bitField0_ & 0x00000001) == 0x00000001) && input.getBytesUntilLimit() > 0) {
                            projectNumbers_ = new java.util.ArrayList<>();
                            mutable_bitField0_ |= 0x00000001;
                        }
                        while (input.getBytesUntilLimit() > 0) {
                            projectNumbers_.add(input.readInt64());
                        }
                        input.popLimit(limit);
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
            if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                projectNumbers_ = java.util.Collections.unmodifiableList(projectNumbers_);
            }
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
        return ProjectNumbersOuterClass.internal_static_com_bht_pim_proto_projects_ProjectNumbers_descriptor;
    }

    @Override
    protected FieldAccessorTable
    internalGetFieldAccessorTable() {
        return ProjectNumbersOuterClass.internal_static_com_bht_pim_proto_projects_ProjectNumbers_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        ProjectNumbers.class, ProjectNumbers.Builder.class);
    }

    /**
     * <code>repeated int64 projectNumbers = 1;</code>
     */
    @Override
    public java.util.List<Long>
    getProjectNumbersList() {
        return projectNumbers_;
    }

    /**
     * <code>repeated int64 projectNumbers = 1;</code>
     */
    @Override
    public int getProjectNumbersCount() {
        return projectNumbers_.size();
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
     * <code>repeated int64 projectNumbers = 1;</code>
     */
    @Override
    public long getProjectNumbers(int index) {
        return projectNumbers_.get(index);
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
            throws java.io.IOException {
        getSerializedSize();
        if (getProjectNumbersList().size() > 0) {
            output.writeUInt32NoTag(10);
            output.writeUInt32NoTag(projectNumbersMemoizedSerializedSize);
        }
        for (int i = 0; i < projectNumbers_.size(); i++) {
            output.writeInt64NoTag(projectNumbers_.get(i));
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
        {
            int dataSize = 0;
            for (int i = 0; i < projectNumbers_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(projectNumbers_.get(i));
            }
            size += dataSize;
            if (!getProjectNumbersList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32SizeNoTag(dataSize);
            }
            projectNumbersMemoizedSerializedSize = dataSize;
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
        if (!(obj instanceof ProjectNumbers)) {
            return super.equals(obj);
        }
        ProjectNumbers other = (ProjectNumbers) obj;

        boolean result = true;
        result = result && getProjectNumbersList()
                .equals(other.getProjectNumbersList());
        result = result && unknownFields.equals(other.unknownFields);
        return result;
    }

    public static ProjectNumbers parseFrom(
            java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ProjectNumbers parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ProjectNumbers parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ProjectNumbers parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ProjectNumbers parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static ProjectNumbers parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static ProjectNumbers parseFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static ProjectNumbers parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static ProjectNumbers parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input);
    }

    public static ProjectNumbers parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static ProjectNumbers parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static ProjectNumbers parseFrom(
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

    public static Builder newBuilder(ProjectNumbers prototype) {
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
        if (getProjectNumbersCount() > 0) {
            hash = (37 * hash) + PROJECTNUMBERS_FIELD_NUMBER;
            hash = (53 * hash) + getProjectNumbersList().hashCode();
        }
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    /**
     * Protobuf type {@code com.bht.pim.proto.projects.ProjectNumbers}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:com.bht.pim.proto.projects.ProjectNumbers)
            ProjectNumbersOrBuilder {
        private java.util.List<Long> projectNumbers_ = java.util.Collections.emptyList();

        // Construct using com.bht.pim.proto.projects.ProjectNumbers.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return ProjectNumbersOuterClass.internal_static_com_bht_pim_proto_projects_ProjectNumbers_descriptor;
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
        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return ProjectNumbersOuterClass.internal_static_com_bht_pim_proto_projects_ProjectNumbers_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            ProjectNumbers.class, ProjectNumbers.Builder.class);
        }

        @Override
        public Builder clear() {
            super.clear();
            projectNumbers_ = java.util.Collections.emptyList();
            bitField0_ = (bitField0_ & ~0x00000001);
            return this;
        }

        @Override
        public ProjectNumbers getDefaultInstanceForType() {
            return ProjectNumbers.getDefaultInstance();
        }

        @Override
        public ProjectNumbers build() {
            ProjectNumbers result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @Override
        public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
            return ProjectNumbersOuterClass.internal_static_com_bht_pim_proto_projects_ProjectNumbers_descriptor;
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
            if (other instanceof ProjectNumbers) {
                return mergeFrom((ProjectNumbers) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        @Override
        public ProjectNumbers buildPartial() {
            ProjectNumbers result = new ProjectNumbers(this);
            int from_bitField0_ = bitField0_;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                projectNumbers_ = java.util.Collections.unmodifiableList(projectNumbers_);
                bitField0_ = (bitField0_ & ~0x00000001);
            }
            result.projectNumbers_ = projectNumbers_;
            onBuilt();
            return result;
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
            ProjectNumbers parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (ProjectNumbers) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private int bitField0_;

        public Builder mergeFrom(ProjectNumbers other) {
            if (other == ProjectNumbers.getDefaultInstance()) {
                return this;
            }
            if (!other.projectNumbers_.isEmpty()) {
                if (projectNumbers_.isEmpty()) {
                    projectNumbers_ = other.projectNumbers_;
                    bitField0_ = (bitField0_ & ~0x00000001);
                } else {
                    ensureProjectNumbersIsMutable();
                    projectNumbers_.addAll(other.projectNumbers_);
                }
                onChanged();
            }
            mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        private void ensureProjectNumbersIsMutable() {
            if (!((bitField0_ & 0x00000001) == 0x00000001)) {
                projectNumbers_ = new java.util.ArrayList<>(projectNumbers_);
                bitField0_ |= 0x00000001;
            }
        }

        /**
         * <code>repeated int64 projectNumbers = 1;</code>
         */
        @Override
        public java.util.List<Long>
        getProjectNumbersList() {
            return java.util.Collections.unmodifiableList(projectNumbers_);
        }

        /**
         * <code>repeated int64 projectNumbers = 1;</code>
         */
        @Override
        public int getProjectNumbersCount() {
            return projectNumbers_.size();
        }

        /**
         * <code>repeated int64 projectNumbers = 1;</code>
         */
        @Override
        public long getProjectNumbers(int index) {
            return projectNumbers_.get(index);
        }

        /**
         * <code>repeated int64 projectNumbers = 1;</code>
         */
        public Builder setProjectNumbers(
                int index, long value) {
            ensureProjectNumbersIsMutable();
            projectNumbers_.set(index, value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 projectNumbers = 1;</code>
         */
        public Builder addProjectNumbers(long value) {
            ensureProjectNumbersIsMutable();
            projectNumbers_.add(value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 projectNumbers = 1;</code>
         */
        public Builder addAllProjectNumbers(
                Iterable<? extends Long> values) {
            ensureProjectNumbersIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(
                    values, projectNumbers_);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 projectNumbers = 1;</code>
         */
        public Builder clearProjectNumbers() {
            projectNumbers_ = java.util.Collections.emptyList();
            bitField0_ = (bitField0_ & ~0x00000001);
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


        // @@protoc_insertion_point(builder_scope:com.bht.pim.proto.projects.ProjectNumbers)
    }

    static {
        DEFAULT_INSTANCE = new ProjectNumbers();
    }

    public static ProjectNumbers getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ProjectNumbers>
            PARSER = new com.google.protobuf.AbstractParser<ProjectNumbers>() {
        @Override
        public ProjectNumbers parsePartialFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new ProjectNumbers(input, extensionRegistry);
        }
    };

    public static com.google.protobuf.Parser<ProjectNumbers> parser() {
        return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<ProjectNumbers> getParserForType() {
        return PARSER;
    }

    @Override
    public ProjectNumbers getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}

