// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProjectList.proto

package com.bht.pim.proto.project;

/**
 * Protobuf type {@code com.bht.pim.proto.project.ProjectList}
 */
public  final class ProjectList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.bht.pim.proto.project.ProjectList)
    ProjectListOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ProjectList.newBuilder() to construct.
  private ProjectList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ProjectList() {
    projectList_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ProjectList(
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
              projectList_ = new java.util.ArrayList<Project>();
              mutable_bitField0_ |= 0x00000001;
            }
            projectList_.add(
                input.readMessage(Project.parser(), extensionRegistry));
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
        projectList_ = java.util.Collections.unmodifiableList(projectList_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.bht.pim.proto.project.ProjectListOuterClass.internal_static_com_bht_pim_proto_project_ProjectList_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bht.pim.proto.project.ProjectListOuterClass.internal_static_com_bht_pim_proto_project_ProjectList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ProjectList.class, Builder.class);
  }

  public static final int PROJECTLIST_FIELD_NUMBER = 1;
  private java.util.List<Project> projectList_;
  /**
   * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
   */
  public java.util.List<Project> getProjectListList() {
    return projectList_;
  }
  /**
   * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
   */
  public java.util.List<? extends ProjectOrBuilder>
      getProjectListOrBuilderList() {
    return projectList_;
  }
  /**
   * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
   */
  public int getProjectListCount() {
    return projectList_.size();
  }
  /**
   * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
   */
  public Project getProjectList(int index) {
    return projectList_.get(index);
  }
  /**
   * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
   */
  public com.bht.pim.proto.project.ProjectOrBuilder getProjectListOrBuilder(
      int index) {
    return projectList_.get(index);
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
    for (int i = 0; i < projectList_.size(); i++) {
      output.writeMessage(1, projectList_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < projectList_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, projectList_.get(i));
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
    if (!(obj instanceof ProjectList)) {
      return super.equals(obj);
    }
    ProjectList other = (ProjectList) obj;

    boolean result = true;
    result = result && getProjectListList()
        .equals(other.getProjectListList());
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
    if (getProjectListCount() > 0) {
      hash = (37 * hash) + PROJECTLIST_FIELD_NUMBER;
      hash = (53 * hash) + getProjectListList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ProjectList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ProjectList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ProjectList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ProjectList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ProjectList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ProjectList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ProjectList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ProjectList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ProjectList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ProjectList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ProjectList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ProjectList parseFrom(
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
  public static Builder newBuilder(ProjectList prototype) {
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
   * Protobuf type {@code com.bht.pim.proto.project.ProjectList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.bht.pim.proto.project.ProjectList)
      com.bht.pim.proto.project.ProjectListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bht.pim.proto.project.ProjectListOuterClass.internal_static_com_bht_pim_proto_project_ProjectList_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bht.pim.proto.project.ProjectListOuterClass.internal_static_com_bht_pim_proto_project_ProjectList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ProjectList.class, Builder.class);
    }

    // Construct using com.bht.pim.proto.project.ProjectList.newBuilder()
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
        getProjectListFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (projectListBuilder_ == null) {
        projectList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        projectListBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bht.pim.proto.project.ProjectListOuterClass.internal_static_com_bht_pim_proto_project_ProjectList_descriptor;
    }

    public ProjectList getDefaultInstanceForType() {
      return ProjectList.getDefaultInstance();
    }

    public ProjectList build() {
      ProjectList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public ProjectList buildPartial() {
      ProjectList result = new ProjectList(this);
      int from_bitField0_ = bitField0_;
      if (projectListBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          projectList_ = java.util.Collections.unmodifiableList(projectList_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.projectList_ = projectList_;
      } else {
        result.projectList_ = projectListBuilder_.build();
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
      if (other instanceof ProjectList) {
        return mergeFrom((ProjectList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ProjectList other) {
      if (other == ProjectList.getDefaultInstance()) return this;
      if (projectListBuilder_ == null) {
        if (!other.projectList_.isEmpty()) {
          if (projectList_.isEmpty()) {
            projectList_ = other.projectList_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureProjectListIsMutable();
            projectList_.addAll(other.projectList_);
          }
          onChanged();
        }
      } else {
        if (!other.projectList_.isEmpty()) {
          if (projectListBuilder_.isEmpty()) {
            projectListBuilder_.dispose();
            projectListBuilder_ = null;
            projectList_ = other.projectList_;
            bitField0_ = (bitField0_ & ~0x00000001);
            projectListBuilder_ =
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getProjectListFieldBuilder() : null;
          } else {
            projectListBuilder_.addAllMessages(other.projectList_);
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
      ProjectList parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ProjectList) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<Project> projectList_ =
      java.util.Collections.emptyList();
    private void ensureProjectListIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        projectList_ = new java.util.ArrayList<Project>(projectList_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            Project, Project.Builder, ProjectOrBuilder> projectListBuilder_;

    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public java.util.List<Project> getProjectListList() {
      if (projectListBuilder_ == null) {
        return java.util.Collections.unmodifiableList(projectList_);
      } else {
        return projectListBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public int getProjectListCount() {
      if (projectListBuilder_ == null) {
        return projectList_.size();
      } else {
        return projectListBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Project getProjectList(int index) {
      if (projectListBuilder_ == null) {
        return projectList_.get(index);
      } else {
        return projectListBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder setProjectList(
        int index, Project value) {
      if (projectListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureProjectListIsMutable();
        projectList_.set(index, value);
        onChanged();
      } else {
        projectListBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder setProjectList(
        int index, Project.Builder builderForValue) {
      if (projectListBuilder_ == null) {
        ensureProjectListIsMutable();
        projectList_.set(index, builderForValue.build());
        onChanged();
      } else {
        projectListBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder addProjectList(Project value) {
      if (projectListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureProjectListIsMutable();
        projectList_.add(value);
        onChanged();
      } else {
        projectListBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder addProjectList(
        int index, Project value) {
      if (projectListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureProjectListIsMutable();
        projectList_.add(index, value);
        onChanged();
      } else {
        projectListBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder addProjectList(
        Project.Builder builderForValue) {
      if (projectListBuilder_ == null) {
        ensureProjectListIsMutable();
        projectList_.add(builderForValue.build());
        onChanged();
      } else {
        projectListBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder addProjectList(
        int index, Project.Builder builderForValue) {
      if (projectListBuilder_ == null) {
        ensureProjectListIsMutable();
        projectList_.add(index, builderForValue.build());
        onChanged();
      } else {
        projectListBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder addAllProjectList(
        Iterable<? extends Project> values) {
      if (projectListBuilder_ == null) {
        ensureProjectListIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, projectList_);
        onChanged();
      } else {
        projectListBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder clearProjectList() {
      if (projectListBuilder_ == null) {
        projectList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        projectListBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Builder removeProjectList(int index) {
      if (projectListBuilder_ == null) {
        ensureProjectListIsMutable();
        projectList_.remove(index);
        onChanged();
      } else {
        projectListBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Project.Builder getProjectListBuilder(
        int index) {
      return getProjectListFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public com.bht.pim.proto.project.ProjectOrBuilder getProjectListOrBuilder(
        int index) {
      if (projectListBuilder_ == null) {
        return projectList_.get(index);  } else {
        return projectListBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public java.util.List<? extends ProjectOrBuilder>
         getProjectListOrBuilderList() {
      if (projectListBuilder_ != null) {
        return projectListBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(projectList_);
      }
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Project.Builder addProjectListBuilder() {
      return getProjectListFieldBuilder().addBuilder(
          Project.getDefaultInstance());
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public Project.Builder addProjectListBuilder(
        int index) {
      return getProjectListFieldBuilder().addBuilder(
          index, Project.getDefaultInstance());
    }
    /**
     * <code>repeated .com.bht.pim.proto.project.Project projectList = 1;</code>
     */
    public java.util.List<Project.Builder>
         getProjectListBuilderList() {
      return getProjectListFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
            Project, Project.Builder, ProjectOrBuilder>
        getProjectListFieldBuilder() {
      if (projectListBuilder_ == null) {
        projectListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
                Project, Project.Builder, ProjectOrBuilder>(
                projectList_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        projectList_ = null;
      }
      return projectListBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.bht.pim.proto.project.ProjectList)
  }

  // @@protoc_insertion_point(class_scope:com.bht.pim.proto.project.ProjectList)
  private static final ProjectList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ProjectList();
  }

  public static ProjectList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ProjectList>
      PARSER = new com.google.protobuf.AbstractParser<ProjectList>() {
    public ProjectList parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ProjectList(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ProjectList> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<ProjectList> getParserForType() {
    return PARSER;
  }

  public ProjectList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

