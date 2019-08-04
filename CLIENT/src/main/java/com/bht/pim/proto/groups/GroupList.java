// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GroupList.proto

package com.bht.pim.proto.groups;

/**
 * Protobuf type {@code com.bht.pim.proto.groups.GroupList}
 */
public final class GroupList extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:com.bht.pim.proto.groups.GroupList)
        GroupListOrBuilder {
    public static final int GROUPS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0L;
    // @@protoc_insertion_point(class_scope:com.bht.pim.proto.groups.GroupList)
    private static final GroupList DEFAULT_INSTANCE;
    private static final com.google.protobuf.Parser<GroupList>
            PARSER = new com.google.protobuf.AbstractParser<GroupList>() {
        public GroupList parsePartialFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new GroupList(input, extensionRegistry);
        }
    };

    static {
        DEFAULT_INSTANCE = new GroupList();
    }

    private java.util.List<Group> groups_;
    private byte memoizedIsInitialized = -1;

    // Use GroupList.newBuilder() to construct.
    private GroupList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupList() {
        groups_ = java.util.Collections.emptyList();
    }

    private GroupList(
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
                    case 10: {
                        if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                            groups_ = new java.util.ArrayList<Group>();
                            mutable_bitField0_ |= 0x00000001;
                        }
                        groups_.add(
                                input.readMessage(Group.parser(), extensionRegistry));
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
                groups_ = java.util.Collections.unmodifiableList(groups_);
            }
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
        return GroupListOuterClass.internal_static_com_bht_pim_proto_groups_GroupList_descriptor;
    }

    public static GroupList parseFrom(
            java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GroupList parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GroupList parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GroupList parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GroupList parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GroupList parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GroupList parseFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static GroupList parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static GroupList parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input);
    }

    public static GroupList parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static GroupList parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static GroupList parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GroupList prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    public static GroupList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static com.google.protobuf.Parser<GroupList> parser() {
        return PARSER;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
        return this.unknownFields;
    }

    protected FieldAccessorTable
    internalGetFieldAccessorTable() {
        return GroupListOuterClass.internal_static_com_bht_pim_proto_groups_GroupList_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        GroupList.class, GroupList.Builder.class);
    }

    /**
     * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
     */
    public java.util.List<Group> getGroupsList() {
        return groups_;
    }

    /**
     * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
     */
    public java.util.List<? extends GroupOrBuilder>
    getGroupsOrBuilderList() {
        return groups_;
    }

    /**
     * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
     */
    public int getGroupsCount() {
        return groups_.size();
    }

    /**
     * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
     */
    public Group getGroups(int index) {
        return groups_.get(index);
    }

    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) return true;
        if (isInitialized == 0) return false;

        memoizedIsInitialized = 1;
        return true;
    }

    /**
     * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
     */
    public GroupOrBuilder getGroupsOrBuilder(
            int index) {
        return groups_.get(index);
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
            throws java.io.IOException {
        for (int i = 0; i < groups_.size(); i++) {
            output.writeMessage(1, groups_.get(i));
        }
        unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) return size;

        size = 0;
        for (int i = 0; i < groups_.size(); i++) {
            size += com.google.protobuf.CodedOutputStream
                    .computeMessageSize(1, groups_.get(i));
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
        if (!(obj instanceof GroupList)) {
            return super.equals(obj);
        }
        GroupList other = (GroupList) obj;

        boolean result = true;
        result = result && getGroupsList()
                .equals(other.getGroupsList());
        result = result && unknownFields.equals(other.unknownFields);
        return result;
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

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
        if (getGroupsCount() > 0) {
            hash = (37 * hash) + GROUPS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupsList().hashCode();
        }
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    @Override
    public com.google.protobuf.Parser<GroupList> getParserForType() {
        return PARSER;
    }

    public GroupList getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    /**
     * Protobuf type {@code com.bht.pim.proto.groups.GroupList}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:com.bht.pim.proto.groups.GroupList)
            GroupListOrBuilder {
        private java.util.List<Group> groups_ =
                java.util.Collections.emptyList();
        private com.google.protobuf.RepeatedFieldBuilderV3<
                Group, Group.Builder, GroupOrBuilder> groupsBuilder_;
        private int bitField0_;

        // Construct using com.bht.pim.proto.groups.GroupList.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(
                BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return GroupListOuterClass.internal_static_com_bht_pim_proto_groups_GroupList_descriptor;
        }

        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return GroupListOuterClass.internal_static_com_bht_pim_proto_groups_GroupList_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            GroupList.class, GroupList.Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3
                    .alwaysUseFieldBuilders) {
                getGroupsFieldBuilder();
            }
        }

        public GroupList getDefaultInstanceForType() {
            return GroupList.getDefaultInstance();
        }

        public GroupList build() {
            GroupList result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        public Builder clear() {
            super.clear();
            if (groupsBuilder_ == null) {
                groups_ = java.util.Collections.emptyList();
                bitField0_ = (bitField0_ & ~0x00000001);
            } else {
                groupsBuilder_.clear();
            }
            return this;
        }

        public Builder clone() {
            return (Builder) super.clone();
        }

        public Builder setField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                Object value) {
            return (Builder) super.setField(field, value);
        }

        public Builder clearField(
                com.google.protobuf.Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        public Builder clearOneof(
                com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        public Builder setRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        public Builder addRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof GroupList) {
                return mergeFrom((GroupList) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
            return GroupListOuterClass.internal_static_com_bht_pim_proto_groups_GroupList_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            GroupList parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (GroupList) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        public GroupList buildPartial() {
            GroupList result = new GroupList(this);
            int from_bitField0_ = bitField0_;
            if (groupsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) == 0x00000001)) {
                    groups_ = java.util.Collections.unmodifiableList(groups_);
                    bitField0_ = (bitField0_ & ~0x00000001);
                }
                result.groups_ = groups_;
            } else {
                result.groups_ = groupsBuilder_.build();
            }
            onBuilt();
            return result;
        }

        public Builder mergeFrom(GroupList other) {
            if (other == GroupList.getDefaultInstance()) return this;
            if (groupsBuilder_ == null) {
                if (!other.groups_.isEmpty()) {
                    if (groups_.isEmpty()) {
                        groups_ = other.groups_;
                        bitField0_ = (bitField0_ & ~0x00000001);
                    } else {
                        ensureGroupsIsMutable();
                        groups_.addAll(other.groups_);
                    }
                    onChanged();
                }
            } else {
                if (!other.groups_.isEmpty()) {
                    if (groupsBuilder_.isEmpty()) {
                        groupsBuilder_.dispose();
                        groupsBuilder_ = null;
                        groups_ = other.groups_;
                        bitField0_ = (bitField0_ & ~0x00000001);
                        groupsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                                        getGroupsFieldBuilder() : null;
                    } else {
                        groupsBuilder_.addAllMessages(other.groups_);
                    }
                }
            }
            this.mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        private void ensureGroupsIsMutable() {
            if (!((bitField0_ & 0x00000001) == 0x00000001)) {
                groups_ = new java.util.ArrayList<Group>(groups_);
                bitField0_ |= 0x00000001;
            }
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public java.util.List<Group> getGroupsList() {
            if (groupsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(groups_);
            } else {
                return groupsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public int getGroupsCount() {
            if (groupsBuilder_ == null) {
                return groups_.size();
            } else {
                return groupsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Group getGroups(int index) {
            if (groupsBuilder_ == null) {
                return groups_.get(index);
            } else {
                return groupsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder setGroups(
                int index, Group value) {
            if (groupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupsIsMutable();
                groups_.set(index, value);
                onChanged();
            } else {
                groupsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder setGroups(
                int index, Group.Builder builderForValue) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                groups_.set(index, builderForValue.build());
                onChanged();
            } else {
                groupsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder addGroups(Group value) {
            if (groupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupsIsMutable();
                groups_.add(value);
                onChanged();
            } else {
                groupsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder addGroups(
                int index, Group value) {
            if (groupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupsIsMutable();
                groups_.add(index, value);
                onChanged();
            } else {
                groupsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder addGroups(
                Group.Builder builderForValue) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                groups_.add(builderForValue.build());
                onChanged();
            } else {
                groupsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder addGroups(
                int index, Group.Builder builderForValue) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                groups_.add(index, builderForValue.build());
                onChanged();
            } else {
                groupsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder addAllGroups(
                Iterable<? extends Group> values) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(
                        values, groups_);
                onChanged();
            } else {
                groupsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder clearGroups() {
            if (groupsBuilder_ == null) {
                groups_ = java.util.Collections.emptyList();
                bitField0_ = (bitField0_ & ~0x00000001);
                onChanged();
            } else {
                groupsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Builder removeGroups(int index) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                groups_.remove(index);
                onChanged();
            } else {
                groupsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Group.Builder getGroupsBuilder(
                int index) {
            return getGroupsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public GroupOrBuilder getGroupsOrBuilder(
                int index) {
            if (groupsBuilder_ == null) {
                return groups_.get(index);
            } else {
                return groupsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public java.util.List<? extends GroupOrBuilder>
        getGroupsOrBuilderList() {
            if (groupsBuilder_ != null) {
                return groupsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(groups_);
            }
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Group.Builder addGroupsBuilder() {
            return getGroupsFieldBuilder().addBuilder(
                    Group.getDefaultInstance());
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public Group.Builder addGroupsBuilder(
                int index) {
            return getGroupsFieldBuilder().addBuilder(
                    index, Group.getDefaultInstance());
        }

        /**
         * <code>repeated .com.bht.pim.proto.groups.Group groups = 1;</code>
         */
        public java.util.List<Group.Builder>
        getGroupsBuilderList() {
            return getGroupsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<
                Group, Group.Builder, GroupOrBuilder>
        getGroupsFieldBuilder() {
            if (groupsBuilder_ == null) {
                groupsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
                        Group, Group.Builder, GroupOrBuilder>(
                        groups_,
                        ((bitField0_ & 0x00000001) == 0x00000001),
                        getParentForChildren(),
                        isClean());
                groups_ = null;
            }
            return groupsBuilder_;
        }

        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFieldsProto3(unknownFields);
        }

        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }


        // @@protoc_insertion_point(builder_scope:com.bht.pim.proto.groups.GroupList)
    }

}

