// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EmployeeInfo.proto

package com.bht.pim.proto.employee;

public final class EmployeeInfoOuterClass {
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_employee_EmployeeInfo_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_employee_EmployeeInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_employee_EmployeeId_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_employee_EmployeeId_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\022EmployeeInfo.proto\022\032com.bht.pim.proto." +
                        "employee\032\016Employee.proto\032\rProject.proto\"" +
                        "\204\001\n\014EmployeeInfo\0226\n\010employee\030\001 \001(\0132$.com" +
                        ".bht.pim.proto.employee.Employee\022<\n\020enro" +
                        "lledProjects\030\002 \003(\0132\".com.bht.pim.proto.p" +
                        "roject.Project\"\030\n\nEmployeeId\022\n\n\002id\030\001 \001(\003" +
                        "2v\n\017EmployeeService\022c\n\017getEmployeeById\022&" +
                        ".com.bht.pim.proto.employee.EmployeeId\032(" +
                        ".com.bht.pim.proto.employee.EmployeeInfo" +
                        "B\002P\001b\006proto3"
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
                                EmployeeOuterClass.getDescriptor(),
                                com.bht.pim.proto.project.ProjectOuterClass.getDescriptor(),
                        }, assigner);
        internal_static_com_bht_pim_proto_employee_EmployeeInfo_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_employee_EmployeeInfo_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_employee_EmployeeInfo_descriptor,
                new String[]{"Employee", "EnrolledProjects",});
        internal_static_com_bht_pim_proto_employee_EmployeeId_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_com_bht_pim_proto_employee_EmployeeId_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_employee_EmployeeId_descriptor,
                new String[]{"Id",});
        EmployeeOuterClass.getDescriptor();
        com.bht.pim.proto.project.ProjectOuterClass.getDescriptor();
    }

    private EmployeeInfoOuterClass() {
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
