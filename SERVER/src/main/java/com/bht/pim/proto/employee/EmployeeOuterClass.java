// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Employee.proto

package com.bht.pim.proto.employee;

public final class EmployeeOuterClass {
  static final com.google.protobuf.Descriptors.Descriptor
          internal_static_com_bht_pim_proto_employee_Employee_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_com_bht_pim_proto_employee_Employee_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.FileDescriptor
          descriptor;

  static {
    String[] descriptorData = {
            "\n\016Employee.proto\022\032com.bht.pim.proto.empl" +
                    "oyee\"]\n\010Employee\022\n\n\002id\030\001 \001(\003\022\014\n\004visa\030\002 \001" +
                    "(\t\022\022\n\nfirst_name\030\003 \001(\t\022\021\n\tlast_name\030\004 \001(" +
                    "\t\022\020\n\010birthday\030\005 \001(\003B\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
            new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
              public com.google.protobuf.ExtensionRegistry assignDescriptors(
                      com.google.protobuf.Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
              }
            };
    com.google.protobuf.Descriptors.FileDescriptor
            .internalBuildGeneratedFileFrom(descriptorData,
                    new com.google.protobuf.Descriptors.FileDescriptor[]{
                    }, assigner);
    internal_static_com_bht_pim_proto_employee_Employee_descriptor =
            getDescriptor().getMessageTypes().get(0);
    internal_static_com_bht_pim_proto_employee_Employee_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_com_bht_pim_proto_employee_Employee_descriptor,
            new String[]{"Id", "Visa", "FirstName", "LastName", "Birthday",});
  }

  private EmployeeOuterClass() {
  }

  public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
            (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
  getDescriptor() {
    return descriptor;
  }

  // @@protoc_insertion_point(outer_class_scope)
}
