// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Employee.proto

package com.bht.pim.proto.employees;

import com.bht.pim.proto.projects.ProjectInfoOuterClass;

public final class EmployeeOuterClass {
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

    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_employees_Employee_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_employees_Employee_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_employees_EmployeeList_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_employees_EmployeeList_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_employees_EmployeePagination_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_employees_EmployeePagination_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\016Employee.proto\022\033com.bht.pim.proto.empl" +
                        "oyees\032\036google/protobuf/wrappers.proto\032\027i" +
                        "nfo/EmployeeInfo.proto\032\026info/ProjectInfo" +
                        ".proto\"\216\001\n\010Employee\022?\n\014employeeInfo\030\001 \001(" +
                        "\0132).com.bht.pim.proto.employees.Employee" +
                        "Info\022A\n\020enrolledProjects\030\002 \003(\0132\'.com.bht" +
                        ".pim.proto.projects.ProjectInfo\"H\n\014Emplo" +
                        "yeeList\0228\n\temployees\030\001 \003(\0132%.com.bht.pim" +
                        ".proto.employees.Employee\"7\n\022EmployeePag" +
                        "ination\022\016\n\006maxRow\030\001 \001(\005\022\021\n\tpageIndex\030\002 \001" +
                        "(\0052\327\001\n\017EmployeeService\022U\n\017getEmployeeByI" +
                        "d\022\033.google.protobuf.Int64Value\032%.com.bht" +
                        ".pim.proto.employees.Employee\022m\n\017getEmpl" +
                        "oyeeList\022/.com.bht.pim.proto.employees.E" +
                        "mployeePagination\032).com.bht.pim.proto.em" +
                        "ployees.EmployeeListB\005P\001\210\001\001b\006proto3"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    @Override
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                                com.google.protobuf.WrappersProto.getDescriptor(),
                                EmployeeInfoOuterClass.getDescriptor(),
                                ProjectInfoOuterClass.getDescriptor(),
                        }, assigner);
        internal_static_com_bht_pim_proto_employees_Employee_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_employees_Employee_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_employees_Employee_descriptor,
                new String[]{"EmployeeInfo", "EnrolledProjects",});
        internal_static_com_bht_pim_proto_employees_EmployeeList_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_com_bht_pim_proto_employees_EmployeeList_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_employees_EmployeeList_descriptor,
                new String[]{"Employees",});
        internal_static_com_bht_pim_proto_employees_EmployeePagination_descriptor =
                getDescriptor().getMessageTypes().get(2);
        internal_static_com_bht_pim_proto_employees_EmployeePagination_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_employees_EmployeePagination_descriptor,
                new String[]{"MaxRow", "PageIndex",});
        com.google.protobuf.WrappersProto.getDescriptor();
        EmployeeInfoOuterClass.getDescriptor();
        ProjectInfoOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}
