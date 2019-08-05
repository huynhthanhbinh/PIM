// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProjectInfo.proto

package com.bht.pim.proto.projects;

import com.bht.pim.proto.employees.EmployeeOuterClass;
import com.bht.pim.proto.groups.GroupOuterClass;

public final class ProjectInfoOuterClass {
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_projects_ProjectInfo_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_projects_ProjectInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_projects_ProjectId_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_projects_ProjectId_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_projects_Success_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_projects_Success_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\021ProjectInfo.proto\022\032com.bht.pim.proto.p" +
                        "rojects\032\rProject.proto\032\016Employee.proto\032\013" +
                        "Group.proto\"}\n\013ProjectInfo\0224\n\007project\030\001 " +
                        "\001(\0132#.com.bht.pim.proto.projects.Project" +
                        "\0228\n\temployees\030\002 \003(\0132%.com.bht.pim.proto." +
                        "employees.Employee\"\027\n\tProjectId\022\n\n\002id\030\001 " +
                        "\001(\003\"\034\n\007Success\022\021\n\tisSuccess\030\001 \001(\0102\213\003\n\016Pr" +
                        "ojectService\022`\n\016getProjectById\022%.com.bht" +
                        ".pim.proto.projects.ProjectId\032\'.com.bht." +
                        "pim.proto.projects.ProjectInfo\022]\n\raddNew" +
                        "Project\022\'.com.bht.pim.proto.projects.Pro" +
                        "jectInfo\032#.com.bht.pim.proto.projects.Su" +
                        "ccess\022[\n\013editProject\022\'.com.bht.pim.proto" +
                        ".projects.ProjectInfo\032#.com.bht.pim.prot" +
                        "o.projects.Success\022[\n\rdeleteProject\022%.co" +
                        "m.bht.pim.proto.projects.ProjectId\032#.com" +
                        ".bht.pim.proto.projects.SuccessB\005P\001\210\001\001b\006" +
                        "proto3"
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
                                ProjectOuterClass.getDescriptor(),
                                EmployeeOuterClass.getDescriptor(),
                                GroupOuterClass.getDescriptor(),
                        }, assigner);
        internal_static_com_bht_pim_proto_projects_ProjectInfo_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_projects_ProjectInfo_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_ProjectInfo_descriptor,
                new String[]{"Project", "Employees",});
        internal_static_com_bht_pim_proto_projects_ProjectId_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_com_bht_pim_proto_projects_ProjectId_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_ProjectId_descriptor,
                new String[]{"Id",});
        internal_static_com_bht_pim_proto_projects_Success_descriptor =
                getDescriptor().getMessageTypes().get(2);
        internal_static_com_bht_pim_proto_projects_Success_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_Success_descriptor,
                new String[]{"IsSuccess",});
        ProjectOuterClass.getDescriptor();
        EmployeeOuterClass.getDescriptor();
        GroupOuterClass.getDescriptor();
    }

    private ProjectInfoOuterClass() {
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
