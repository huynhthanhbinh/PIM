// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProjectInfo.proto

package com.bht.pim.proto.project;

/**
 * Protobuf type {@code com.bht.pim.proto.project.ProjectInfo}
 */
public  final class ProjectInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.bht.pim.proto.project.ProjectInfo)
        ProjectInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ProjectInfo.newBuilder() to construct.
  private ProjectInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ProjectInfo() {
    groupLeader_ = "";
    employees_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ProjectInfo(
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
            Project.Builder subBuilder = null;
            if (project_ != null) {
              subBuilder = project_.toBuilder();
            }
            project_ = input.readMessage(Project.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(project_);
              project_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            groupLeader_ = s;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              employees_ = new java.util.ArrayList<com.bht.pim.proto.employee.Employee>();
              mutable_bitField0_ |= 0x00000004;
            }
            employees_.add(
                input.readMessage(com.bht.pim.proto.employee.Employee.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        employees_ = java.util.Collections.unmodifiableList(employees_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.bht.pim.proto.project.ProjectInfoOuterClass.internal_static_com_bht_pim_proto_project_ProjectInfo_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bht.pim.proto.project.ProjectInfoOuterClass.internal_static_com_bht_pim_proto_project_ProjectInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ProjectInfo.class, Builder.class);
  }

  private int bitField0_;
  public static final int PROJECT_FIELD_NUMBER = 1;
  private Project project_;
  /**
   * <code>.com.bht.pim.proto.project.Project project = 1;</code>
   */
  public boolean hasProject() {
    return project_ != null;
  }
  /**
   * <code>.com.bht.pim.proto.project.Project project = 1;</code>
   */
  public Project getProject() {
    return project_ == null ? Project.getDefaultInstance() : project_;
  }
  /**
   * <code>.com.bht.pim.proto.project.Project project = 1;</code>
   */
  public com.bht.pim.proto.project.ProjectOrBuilder getProjectOrBuilder() {
    return getProject();
  }

  public static final int GROUPLEADER_FIELD_NUMBER = 2;
  private volatile Object groupLeader_;
  /**
   * <code>string groupLeader = 2;</code>
   */
  public String getGroupLeader() {
    Object ref = groupLeader_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      groupLeader_ = s;
      return s;
    }
  }
  /**
   * <code>string groupLeader = 2;</code>
   */
  public com.google.protobuf.ByteString
      getGroupLeaderBytes() {
    Object ref = groupLeader_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      groupLeader_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EMPLOYEES_FIELD_NUMBER = 3;
  private java.util.List<com.bht.pim.proto.employee.Employee> employees_;
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
   */
  public java.util.List<com.bht.pim.proto.employee.Employee> getEmployeesList() {
    return employees_;
  }
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
   */
  public java.util.List<? extends com.bht.pim.proto.employee.EmployeeOrBuilder>
      getEmployeesOrBuilderList() {
    return employees_;
  }
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
   */
  public int getEmployeesCount() {
    return employees_.size();
  }
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
   */
  public com.bht.pim.proto.employee.Employee getEmployees(int index) {
    return employees_.get(index);
  }
  /**
   * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
   */
  public com.bht.pim.proto.employee.EmployeeOrBuilder getEmployeesOrBuilder(
      int index) {
    return employees_.get(index);
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
    if (project_ != null) {
      output.writeMessage(1, getProject());
    }
    if (!getGroupLeaderBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, groupLeader_);
    }
    for (int i = 0; i < employees_.size(); i++) {
      output.writeMessage(3, employees_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (project_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getProject());
    }
    if (!getGroupLeaderBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, groupLeader_);
    }
    for (int i = 0; i < employees_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, employees_.get(i));
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
    if (!(obj instanceof ProjectInfo)) {
      return super.equals(obj);
    }
    ProjectInfo other = (ProjectInfo) obj;

    boolean result = true;
    result = result && (hasProject() == other.hasProject());
    if (hasProject()) {
      result = result && getProject()
          .equals(other.getProject());
    }
    result = result && getGroupLeader()
        .equals(other.getGroupLeader());
    result = result && getEmployeesList()
        .equals(other.getEmployeesList());
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
    if (hasProject()) {
      hash = (37 * hash) + PROJECT_FIELD_NUMBER;
      hash = (53 * hash) + getProject().hashCode();
    }
    hash = (37 * hash) + GROUPLEADER_FIELD_NUMBER;
    hash = (53 * hash) + getGroupLeader().hashCode();
    if (getEmployeesCount() > 0) {
      hash = (37 * hash) + EMPLOYEES_FIELD_NUMBER;
      hash = (53 * hash) + getEmployeesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ProjectInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ProjectInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ProjectInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ProjectInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ProjectInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ProjectInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ProjectInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ProjectInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ProjectInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ProjectInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ProjectInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ProjectInfo parseFrom(
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
  public static Builder newBuilder(ProjectInfo prototype) {
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
   * Protobuf type {@code com.bht.pim.proto.project.ProjectInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.bht.pim.proto.project.ProjectInfo)
      com.bht.pim.proto.project.ProjectInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bht.pim.proto.project.ProjectInfoOuterClass.internal_static_com_bht_pim_proto_project_ProjectInfo_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bht.pim.proto.project.ProjectInfoOuterClass.internal_static_com_bht_pim_proto_project_ProjectInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ProjectInfo.class, Builder.class);
    }

    // Construct using com.bht.pim.proto.project.ProjectInfo.newBuilder()
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
        getEmployeesFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (projectBuilder_ == null) {
        project_ = null;
      } else {
        project_ = null;
        projectBuilder_ = null;
      }
      groupLeader_ = "";

      if (employeesBuilder_ == null) {
        employees_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        employeesBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bht.pim.proto.project.ProjectInfoOuterClass.internal_static_com_bht_pim_proto_project_ProjectInfo_descriptor;
    }

    public ProjectInfo getDefaultInstanceForType() {
      return ProjectInfo.getDefaultInstance();
    }

    public ProjectInfo build() {
      ProjectInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public ProjectInfo buildPartial() {
      ProjectInfo result = new ProjectInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (projectBuilder_ == null) {
        result.project_ = project_;
      } else {
        result.project_ = projectBuilder_.build();
      }
      result.groupLeader_ = groupLeader_;
      if (employeesBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          employees_ = java.util.Collections.unmodifiableList(employees_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.employees_ = employees_;
      } else {
        result.employees_ = employeesBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof ProjectInfo) {
        return mergeFrom((ProjectInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ProjectInfo other) {
      if (other == ProjectInfo.getDefaultInstance()) return this;
      if (other.hasProject()) {
        mergeProject(other.getProject());
      }
      if (!other.getGroupLeader().isEmpty()) {
        groupLeader_ = other.groupLeader_;
        onChanged();
      }
      if (employeesBuilder_ == null) {
        if (!other.employees_.isEmpty()) {
          if (employees_.isEmpty()) {
            employees_ = other.employees_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureEmployeesIsMutable();
            employees_.addAll(other.employees_);
          }
          onChanged();
        }
      } else {
        if (!other.employees_.isEmpty()) {
          if (employeesBuilder_.isEmpty()) {
            employeesBuilder_.dispose();
            employeesBuilder_ = null;
            employees_ = other.employees_;
            bitField0_ = (bitField0_ & ~0x00000004);
            employeesBuilder_ =
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getEmployeesFieldBuilder() : null;
          } else {
            employeesBuilder_.addAllMessages(other.employees_);
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
      ProjectInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ProjectInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private Project project_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
            Project, Project.Builder, ProjectOrBuilder> projectBuilder_;
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    public boolean hasProject() {
      return projectBuilder_ != null || project_ != null;
    }
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    public Project getProject() {
      if (projectBuilder_ == null) {
        return project_ == null ? Project.getDefaultInstance() : project_;
      } else {
        return projectBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    public Builder setProject(Project value) {
      if (projectBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        project_ = value;
        onChanged();
      } else {
        projectBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    public Builder setProject(
        Project.Builder builderForValue) {
      if (projectBuilder_ == null) {
        project_ = builderForValue.build();
        onChanged();
      } else {
        projectBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    public Builder mergeProject(Project value) {
      if (projectBuilder_ == null) {
        if (project_ != null) {
          project_ =
            Project.newBuilder(project_).mergeFrom(value).buildPartial();
        } else {
          project_ = value;
        }
        onChanged();
      } else {
        projectBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    public Builder clearProject() {
      if (projectBuilder_ == null) {
        project_ = null;
        onChanged();
      } else {
        project_ = null;
        projectBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    public Project.Builder getProjectBuilder() {

      onChanged();
      return getProjectFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    public com.bht.pim.proto.project.ProjectOrBuilder getProjectOrBuilder() {
      if (projectBuilder_ != null) {
        return projectBuilder_.getMessageOrBuilder();
      } else {
        return project_ == null ?
            Project.getDefaultInstance() : project_;
      }
    }
    /**
     * <code>.com.bht.pim.proto.project.Project project = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
            Project, Project.Builder, ProjectOrBuilder>
        getProjectFieldBuilder() {
      if (projectBuilder_ == null) {
        projectBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                Project, Project.Builder, ProjectOrBuilder>(
                getProject(),
                getParentForChildren(),
                isClean());
        project_ = null;
      }
      return projectBuilder_;
    }

    private Object groupLeader_ = "";
    /**
     * <code>string groupLeader = 2;</code>
     */
    public String getGroupLeader() {
      Object ref = groupLeader_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        groupLeader_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string groupLeader = 2;</code>
     */
    public com.google.protobuf.ByteString
        getGroupLeaderBytes() {
      Object ref = groupLeader_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        groupLeader_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string groupLeader = 2;</code>
     */
    public Builder setGroupLeader(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      groupLeader_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string groupLeader = 2;</code>
     */
    public Builder clearGroupLeader() {
      
      groupLeader_ = getDefaultInstance().getGroupLeader();
      onChanged();
      return this;
    }
    /**
     * <code>string groupLeader = 2;</code>
     */
    public Builder setGroupLeaderBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      groupLeader_ = value;
      onChanged();
      return this;
    }

    private java.util.List<com.bht.pim.proto.employee.Employee> employees_ =
      java.util.Collections.emptyList();
    private void ensureEmployeesIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        employees_ = new java.util.ArrayList<com.bht.pim.proto.employee.Employee>(employees_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.bht.pim.proto.employee.Employee, com.bht.pim.proto.employee.Employee.Builder, com.bht.pim.proto.employee.EmployeeOrBuilder> employeesBuilder_;

    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public java.util.List<com.bht.pim.proto.employee.Employee> getEmployeesList() {
      if (employeesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(employees_);
      } else {
        return employeesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public int getEmployeesCount() {
      if (employeesBuilder_ == null) {
        return employees_.size();
      } else {
        return employeesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public com.bht.pim.proto.employee.Employee getEmployees(int index) {
      if (employeesBuilder_ == null) {
        return employees_.get(index);
      } else {
        return employeesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder setEmployees(
        int index, com.bht.pim.proto.employee.Employee value) {
      if (employeesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEmployeesIsMutable();
        employees_.set(index, value);
        onChanged();
      } else {
        employeesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder setEmployees(
        int index, com.bht.pim.proto.employee.Employee.Builder builderForValue) {
      if (employeesBuilder_ == null) {
        ensureEmployeesIsMutable();
        employees_.set(index, builderForValue.build());
        onChanged();
      } else {
        employeesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder addEmployees(com.bht.pim.proto.employee.Employee value) {
      if (employeesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEmployeesIsMutable();
        employees_.add(value);
        onChanged();
      } else {
        employeesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder addEmployees(
        int index, com.bht.pim.proto.employee.Employee value) {
      if (employeesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEmployeesIsMutable();
        employees_.add(index, value);
        onChanged();
      } else {
        employeesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder addEmployees(
        com.bht.pim.proto.employee.Employee.Builder builderForValue) {
      if (employeesBuilder_ == null) {
        ensureEmployeesIsMutable();
        employees_.add(builderForValue.build());
        onChanged();
      } else {
        employeesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder addEmployees(
        int index, com.bht.pim.proto.employee.Employee.Builder builderForValue) {
      if (employeesBuilder_ == null) {
        ensureEmployeesIsMutable();
        employees_.add(index, builderForValue.build());
        onChanged();
      } else {
        employeesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder addAllEmployees(
        Iterable<? extends com.bht.pim.proto.employee.Employee> values) {
      if (employeesBuilder_ == null) {
        ensureEmployeesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, employees_);
        onChanged();
      } else {
        employeesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder clearEmployees() {
      if (employeesBuilder_ == null) {
        employees_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        employeesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public Builder removeEmployees(int index) {
      if (employeesBuilder_ == null) {
        ensureEmployeesIsMutable();
        employees_.remove(index);
        onChanged();
      } else {
        employeesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public com.bht.pim.proto.employee.Employee.Builder getEmployeesBuilder(
        int index) {
      return getEmployeesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public com.bht.pim.proto.employee.EmployeeOrBuilder getEmployeesOrBuilder(
        int index) {
      if (employeesBuilder_ == null) {
        return employees_.get(index);  } else {
        return employeesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public java.util.List<? extends com.bht.pim.proto.employee.EmployeeOrBuilder> 
         getEmployeesOrBuilderList() {
      if (employeesBuilder_ != null) {
        return employeesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(employees_);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public com.bht.pim.proto.employee.Employee.Builder addEmployeesBuilder() {
      return getEmployeesFieldBuilder().addBuilder(
          com.bht.pim.proto.employee.Employee.getDefaultInstance());
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public com.bht.pim.proto.employee.Employee.Builder addEmployeesBuilder(
        int index) {
      return getEmployeesFieldBuilder().addBuilder(
          index, com.bht.pim.proto.employee.Employee.getDefaultInstance());
    }
    /**
     * <code>repeated .com.bht.pim.proto.employee.Employee employees = 3;</code>
     */
    public java.util.List<com.bht.pim.proto.employee.Employee.Builder> 
         getEmployeesBuilderList() {
      return getEmployeesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.bht.pim.proto.employee.Employee, com.bht.pim.proto.employee.Employee.Builder, com.bht.pim.proto.employee.EmployeeOrBuilder> 
        getEmployeesFieldBuilder() {
      if (employeesBuilder_ == null) {
        employeesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.bht.pim.proto.employee.Employee, com.bht.pim.proto.employee.Employee.Builder, com.bht.pim.proto.employee.EmployeeOrBuilder>(
                employees_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        employees_ = null;
      }
      return employeesBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.bht.pim.proto.project.ProjectInfo)
  }

  // @@protoc_insertion_point(class_scope:com.bht.pim.proto.project.ProjectInfo)
  private static final ProjectInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ProjectInfo();
  }

  public static ProjectInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ProjectInfo>
      PARSER = new com.google.protobuf.AbstractParser<ProjectInfo>() {
    public ProjectInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ProjectInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ProjectInfo> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<ProjectInfo> getParserForType() {
    return PARSER;
  }

  public ProjectInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

