// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EmployeeList.proto

package com.bht.pim.proto.employee;

/**
 * Protobuf type {@code com.bht.pim.proto.employee.EmployeeList}
 */
public  final class EmployeeList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.bht.pim.proto.employee.EmployeeList)
    EmployeeListOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EmployeeList.newBuilder() to construct.
  private EmployeeList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EmployeeList() {
    employeeList_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EmployeeList(
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
              employeeList_ = new java.util.ArrayList<Employee>();
              mutable_bitField0_ |= 0x00000001;
            }
            employeeList_.add(
                input.readMessage(Employee.parser(), extensionRegistry));
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
        employeeList_ = java.util.Collections.unmodifiableList(employeeList_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.bht.pim.proto.employee.EmployeeListOuterClass.internal_static_com_bht_pim_proto_employee_EmployeeList_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bht.pim.proto.employee.EmployeeListOuterClass.internal_static_com_bht_pim_proto_employee_EmployeeList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            EmployeeList.class, Builder.class);
  }

  public static final int EMPLOYEELIST_FIELD_NUMBER = 1;
  private java.util.List<Employee> employeeList_;
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
   */
  public java.util.List<Employee> getEmployeeListList() {
    return employeeList_;
  }
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
   */
  public java.util.List<? extends com.bht.pim.proto.employee.EmployeeOrBuilder> 
      getEmployeeListOrBuilderList() {
    return employeeList_;
  }
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
   */
  public int getEmployeeListCount() {
    return employeeList_.size();
  }
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
   */
  public Employee getEmployeeList(int index) {
    return employeeList_.get(index);
  }
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
   */
  public com.bht.pim.proto.employee.EmployeeOrBuilder getEmployeeListOrBuilder(
      int index) {
    return employeeList_.get(index);
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
    for (int i = 0; i < employeeList_.size(); i++) {
      output.writeMessage(1, employeeList_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < employeeList_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, employeeList_.get(i));
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
    if (!(obj instanceof EmployeeList)) {
      return super.equals(obj);
    }
    EmployeeList other = (EmployeeList) obj;

    boolean result = true;
    result = result && getEmployeeListList()
        .equals(other.getEmployeeListList());
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
    if (getEmployeeListCount() > 0) {
      hash = (37 * hash) + EMPLOYEELIST_FIELD_NUMBER;
      hash = (53 * hash) + getEmployeeListList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static EmployeeList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EmployeeList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EmployeeList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EmployeeList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EmployeeList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EmployeeList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EmployeeList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static EmployeeList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static EmployeeList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static EmployeeList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static EmployeeList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static EmployeeList parseFrom(
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
  public static Builder newBuilder(EmployeeList prototype) {
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
   * Protobuf type {@code com.bht.pim.proto.employee.EmployeeList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.bht.pim.proto.employee.EmployeeList)
      com.bht.pim.proto.employee.EmployeeListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bht.pim.proto.employee.EmployeeListOuterClass.internal_static_com_bht_pim_proto_employee_EmployeeList_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bht.pim.proto.employee.EmployeeListOuterClass.internal_static_com_bht_pim_proto_employee_EmployeeList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              EmployeeList.class, Builder.class);
    }

    // Construct using com.bht.pim.proto.employee.EmployeeList.newBuilder()
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
        getEmployeeListFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (employeeListBuilder_ == null) {
        employeeList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        employeeListBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bht.pim.proto.employee.EmployeeListOuterClass.internal_static_com_bht_pim_proto_employee_EmployeeList_descriptor;
    }

    public EmployeeList getDefaultInstanceForType() {
      return EmployeeList.getDefaultInstance();
    }

    public EmployeeList build() {
      EmployeeList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public EmployeeList buildPartial() {
      EmployeeList result = new EmployeeList(this);
      int from_bitField0_ = bitField0_;
      if (employeeListBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          employeeList_ = java.util.Collections.unmodifiableList(employeeList_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.employeeList_ = employeeList_;
      } else {
        result.employeeList_ = employeeListBuilder_.build();
      }
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
      if (other instanceof EmployeeList) {
        return mergeFrom((EmployeeList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(EmployeeList other) {
      if (other == EmployeeList.getDefaultInstance()) return this;
      if (employeeListBuilder_ == null) {
        if (!other.employeeList_.isEmpty()) {
          if (employeeList_.isEmpty()) {
            employeeList_ = other.employeeList_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureEmployeeListIsMutable();
            employeeList_.addAll(other.employeeList_);
          }
          onChanged();
        }
      } else {
        if (!other.employeeList_.isEmpty()) {
          if (employeeListBuilder_.isEmpty()) {
            employeeListBuilder_.dispose();
            employeeListBuilder_ = null;
            employeeList_ = other.employeeList_;
            bitField0_ = (bitField0_ & ~0x00000001);
            employeeListBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getEmployeeListFieldBuilder() : null;
          } else {
            employeeListBuilder_.addAllMessages(other.employeeList_);
          }
        }
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
      EmployeeList parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (EmployeeList) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<Employee> employeeList_ =
      java.util.Collections.emptyList();
    private void ensureEmployeeListIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        employeeList_ = new java.util.ArrayList<Employee>(employeeList_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        Employee, Employee.Builder, com.bht.pim.proto.employee.EmployeeOrBuilder> employeeListBuilder_;

    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public java.util.List<Employee> getEmployeeListList() {
      if (employeeListBuilder_ == null) {
        return java.util.Collections.unmodifiableList(employeeList_);
      } else {
        return employeeListBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public int getEmployeeListCount() {
      if (employeeListBuilder_ == null) {
        return employeeList_.size();
      } else {
        return employeeListBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Employee getEmployeeList(int index) {
      if (employeeListBuilder_ == null) {
        return employeeList_.get(index);
      } else {
        return employeeListBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder setEmployeeList(
        int index, Employee value) {
      if (employeeListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEmployeeListIsMutable();
        employeeList_.set(index, value);
        onChanged();
      } else {
        employeeListBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder setEmployeeList(
        int index, Employee.Builder builderForValue) {
      if (employeeListBuilder_ == null) {
        ensureEmployeeListIsMutable();
        employeeList_.set(index, builderForValue.build());
        onChanged();
      } else {
        employeeListBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder addEmployeeList(Employee value) {
      if (employeeListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEmployeeListIsMutable();
        employeeList_.add(value);
        onChanged();
      } else {
        employeeListBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder addEmployeeList(
        int index, Employee value) {
      if (employeeListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEmployeeListIsMutable();
        employeeList_.add(index, value);
        onChanged();
      } else {
        employeeListBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder addEmployeeList(
        Employee.Builder builderForValue) {
      if (employeeListBuilder_ == null) {
        ensureEmployeeListIsMutable();
        employeeList_.add(builderForValue.build());
        onChanged();
      } else {
        employeeListBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder addEmployeeList(
        int index, Employee.Builder builderForValue) {
      if (employeeListBuilder_ == null) {
        ensureEmployeeListIsMutable();
        employeeList_.add(index, builderForValue.build());
        onChanged();
      } else {
        employeeListBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder addAllEmployeeList(
        Iterable<? extends Employee> values) {
      if (employeeListBuilder_ == null) {
        ensureEmployeeListIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, employeeList_);
        onChanged();
      } else {
        employeeListBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder clearEmployeeList() {
      if (employeeListBuilder_ == null) {
        employeeList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        employeeListBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Builder removeEmployeeList(int index) {
      if (employeeListBuilder_ == null) {
        ensureEmployeeListIsMutable();
        employeeList_.remove(index);
        onChanged();
      } else {
        employeeListBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Employee.Builder getEmployeeListBuilder(
        int index) {
      return getEmployeeListFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public com.bht.pim.proto.employee.EmployeeOrBuilder getEmployeeListOrBuilder(
        int index) {
      if (employeeListBuilder_ == null) {
        return employeeList_.get(index);  } else {
        return employeeListBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public java.util.List<? extends com.bht.pim.proto.employee.EmployeeOrBuilder> 
         getEmployeeListOrBuilderList() {
      if (employeeListBuilder_ != null) {
        return employeeListBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(employeeList_);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Employee.Builder addEmployeeListBuilder() {
      return getEmployeeListFieldBuilder().addBuilder(
          Employee.getDefaultInstance());
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public Employee.Builder addEmployeeListBuilder(
        int index) {
      return getEmployeeListFieldBuilder().addBuilder(
          index, Employee.getDefaultInstance());
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employeeList = 1;</code>
     */
    public java.util.List<Employee.Builder>
         getEmployeeListBuilderList() {
      return getEmployeeListFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        Employee, Employee.Builder, com.bht.pim.proto.employee.EmployeeOrBuilder>
        getEmployeeListFieldBuilder() {
      if (employeeListBuilder_ == null) {
        employeeListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            Employee, Employee.Builder, com.bht.pim.proto.employee.EmployeeOrBuilder>(
                employeeList_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        employeeList_ = null;
      }
      return employeeListBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.bht.pim.proto.employee.EmployeeList)
  }

  // @@protoc_insertion_point(class_scope:com.bht.pim.proto.employee.EmployeeList)
  private static final EmployeeList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new EmployeeList();
  }

  public static EmployeeList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EmployeeList>
      PARSER = new com.google.protobuf.AbstractParser<EmployeeList>() {
    public EmployeeList parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new EmployeeList(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EmployeeList> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<EmployeeList> getParserForType() {
    return PARSER;
  }

  public EmployeeList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

