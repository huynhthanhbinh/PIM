// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Group.proto

package com.bht.pim.proto.group;

/**
 * Protobuf type {@code com.bht.pim.proto.group.Group}
 */
public  final class Group extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.bht.pim.proto.group.Group)
    GroupOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Group.newBuilder() to construct.
  private Group(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Group() {
    id_ = 0L;
    groupLeaderId_ = 0L;
    groupLeaderVisa_ = "";
    groupLeaderName_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
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
          case 16: {

            groupLeaderId_ = input.readInt64();
            break;
          }
          case 26: {
            String s = input.readStringRequireUtf8();

            groupLeaderVisa_ = s;
            break;
          }
          case 34: {
            String s = input.readStringRequireUtf8();

            groupLeaderName_ = s;
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
    return com.bht.pim.proto.group.GroupOuterClass.internal_static_com_bht_pim_proto_group_Group_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bht.pim.proto.group.GroupOuterClass.internal_static_com_bht_pim_proto_group_Group_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            Group.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int GROUPLEADERID_FIELD_NUMBER = 2;
  private long groupLeaderId_;
  /**
   * <code>int64 groupLeaderId = 2;</code>
   */
  public long getGroupLeaderId() {
    return groupLeaderId_;
  }

  public static final int GROUPLEADERVISA_FIELD_NUMBER = 3;
  private volatile Object groupLeaderVisa_;
  /**
   * <code>string groupLeaderVisa = 3;</code>
   */
  public String getGroupLeaderVisa() {
    Object ref = groupLeaderVisa_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      groupLeaderVisa_ = s;
      return s;
    }
  }
  /**
   * <code>string groupLeaderVisa = 3;</code>
   */
  public com.google.protobuf.ByteString
      getGroupLeaderVisaBytes() {
    Object ref = groupLeaderVisa_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      groupLeaderVisa_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int GROUPLEADERNAME_FIELD_NUMBER = 4;
  private volatile Object groupLeaderName_;
  /**
   * <code>string groupLeaderName = 4;</code>
   */
  public String getGroupLeaderName() {
    Object ref = groupLeaderName_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      groupLeaderName_ = s;
      return s;
    }
  }
  /**
   * <code>string groupLeaderName = 4;</code>
   */
  public com.google.protobuf.ByteString
      getGroupLeaderNameBytes() {
    Object ref = groupLeaderName_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      groupLeaderName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (id_ != 0L) {
      output.writeInt64(1, id_);
    }
    if (groupLeaderId_ != 0L) {
      output.writeInt64(2, groupLeaderId_);
    }
    if (!getGroupLeaderVisaBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, groupLeaderVisa_);
    }
    if (!getGroupLeaderNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, groupLeaderName_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, id_);
    }
    if (groupLeaderId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, groupLeaderId_);
    }
    if (!getGroupLeaderVisaBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, groupLeaderVisa_);
    }
    if (!getGroupLeaderNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, groupLeaderName_);
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
    result = result && (getGroupLeaderId()
        == other.getGroupLeaderId());
    result = result && getGroupLeaderVisa()
        .equals(other.getGroupLeaderVisa());
    result = result && getGroupLeaderName()
        .equals(other.getGroupLeaderName());
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
    hash = (37 * hash) + GROUPLEADERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getGroupLeaderId());
    hash = (37 * hash) + GROUPLEADERVISA_FIELD_NUMBER;
    hash = (53 * hash) + getGroupLeaderVisa().hashCode();
    hash = (37 * hash) + GROUPLEADERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getGroupLeaderName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
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

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(Group prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
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
  /**
   * Protobuf type {@code com.bht.pim.proto.group.Group}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.bht.pim.proto.group.Group)
      com.bht.pim.proto.group.GroupOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bht.pim.proto.group.GroupOuterClass.internal_static_com_bht_pim_proto_group_Group_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bht.pim.proto.group.GroupOuterClass.internal_static_com_bht_pim_proto_group_Group_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Group.class, Builder.class);
    }

    // Construct using com.bht.pim.proto.group.Group.newBuilder()
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
    public Builder clear() {
      super.clear();
      id_ = 0L;

      groupLeaderId_ = 0L;

      groupLeaderVisa_ = "";

      groupLeaderName_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bht.pim.proto.group.GroupOuterClass.internal_static_com_bht_pim_proto_group_Group_descriptor;
    }

    public Group getDefaultInstanceForType() {
      return Group.getDefaultInstance();
    }

    public Group build() {
      Group result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public Group buildPartial() {
      Group result = new Group(this);
      result.id_ = id_;
      result.groupLeaderId_ = groupLeaderId_;
      result.groupLeaderVisa_ = groupLeaderVisa_;
      result.groupLeaderName_ = groupLeaderName_;
      onBuilt();
      return result;
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
      if (other instanceof Group) {
        return mergeFrom((Group)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Group other) {
      if (other == Group.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.getGroupLeaderId() != 0L) {
        setGroupLeaderId(other.getGroupLeaderId());
      }
      if (!other.getGroupLeaderVisa().isEmpty()) {
        groupLeaderVisa_ = other.groupLeaderVisa_;
        onChanged();
      }
      if (!other.getGroupLeaderName().isEmpty()) {
        groupLeaderName_ = other.groupLeaderName_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

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

    private long id_ ;
    /**
     * <code>int64 id = 1;</code>
     */
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

    private long groupLeaderId_ ;
    /**
     * <code>int64 groupLeaderId = 2;</code>
     */
    public long getGroupLeaderId() {
      return groupLeaderId_;
    }
    /**
     * <code>int64 groupLeaderId = 2;</code>
     */
    public Builder setGroupLeaderId(long value) {
      
      groupLeaderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 groupLeaderId = 2;</code>
     */
    public Builder clearGroupLeaderId() {
      
      groupLeaderId_ = 0L;
      onChanged();
      return this;
    }

    private Object groupLeaderVisa_ = "";
    /**
     * <code>string groupLeaderVisa = 3;</code>
     */
    public String getGroupLeaderVisa() {
      Object ref = groupLeaderVisa_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        groupLeaderVisa_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string groupLeaderVisa = 3;</code>
     */
    public com.google.protobuf.ByteString
        getGroupLeaderVisaBytes() {
      Object ref = groupLeaderVisa_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        groupLeaderVisa_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string groupLeaderVisa = 3;</code>
     */
    public Builder setGroupLeaderVisa(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      groupLeaderVisa_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string groupLeaderVisa = 3;</code>
     */
    public Builder clearGroupLeaderVisa() {
      
      groupLeaderVisa_ = getDefaultInstance().getGroupLeaderVisa();
      onChanged();
      return this;
    }
    /**
     * <code>string groupLeaderVisa = 3;</code>
     */
    public Builder setGroupLeaderVisaBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      groupLeaderVisa_ = value;
      onChanged();
      return this;
    }

    private Object groupLeaderName_ = "";
    /**
     * <code>string groupLeaderName = 4;</code>
     */
    public String getGroupLeaderName() {
      Object ref = groupLeaderName_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        groupLeaderName_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string groupLeaderName = 4;</code>
     */
    public com.google.protobuf.ByteString
        getGroupLeaderNameBytes() {
      Object ref = groupLeaderName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        groupLeaderName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string groupLeaderName = 4;</code>
     */
    public Builder setGroupLeaderName(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      groupLeaderName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string groupLeaderName = 4;</code>
     */
    public Builder clearGroupLeaderName() {
      
      groupLeaderName_ = getDefaultInstance().getGroupLeaderName();
      onChanged();
      return this;
    }
    /**
     * <code>string groupLeaderName = 4;</code>
     */
    public Builder setGroupLeaderNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      groupLeaderName_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.bht.pim.proto.group.Group)
  }

  // @@protoc_insertion_point(class_scope:com.bht.pim.proto.group.Group)
  private static final Group DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Group();
  }

  public static Group getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Group>
      PARSER = new com.google.protobuf.AbstractParser<Group>() {
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

  public Group getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

