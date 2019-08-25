// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Project.proto

package com.bht.pim.proto.projects;

import com.bht.pim.proto.employees.EmployeeInfoOuterClass;
import com.bht.pim.proto.groups.GroupOuterClass;

public final class ProjectOuterClass {
    private ProjectOuterClass() {
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
            internal_static_com_bht_pim_proto_projects_Project_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_projects_Project_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_projects_ProjectList_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_projects_ProjectList_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_projects_ProjectNumbers_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_projects_ProjectNumbers_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_projects_ProjectPagination_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_projects_ProjectPagination_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\rProject.proto\022\032com.bht.pim.proto.proje" +
                        "cts\032\036google/protobuf/wrappers.proto\032\033goo" +
                        "gle/protobuf/empty.proto\032\026info/ProjectIn" +
                        "fo.proto\032\027info/EmployeeInfo.proto\032\013Group" +
                        ".proto\"\203\001\n\007Project\022<\n\013projectInfo\030\001 \001(\0132" +
                        "\'.com.bht.pim.proto.projects.ProjectInfo" +
                        "\022:\n\007members\030\002 \003(\0132).com.bht.pim.proto.em" +
                        "ployees.EmployeeInfo\"D\n\013ProjectList\0225\n\010p" +
                        "rojects\030\001 \003(\0132#.com.bht.pim.proto.projec" +
                        "ts.Project\"(\n\016ProjectNumbers\022\026\n\016projectN" +
                        "umbers\030\001 \003(\003\"W\n\021ProjectPagination\022\016\n\006max" +
                        "Row\030\001 \001(\005\022\021\n\tpageIndex\030\002 \001(\005\022\016\n\006status\030\003" +
                        " \001(\t\022\017\n\007keyword\030\004 \001(\t2\354\006\n\016ProjectService" +
                        "\022R\n\016getProjectById\022\033.google.protobuf.Int" +
                        "64Value\032#.com.bht.pim.proto.projects.Pro" +
                        "ject\022V\n\022getProjectByNumber\022\033.google.prot" +
                        "obuf.Int64Value\032#.com.bht.pim.proto.proj" +
                        "ects.Project\022P\n\raddNewProject\022#.com.bht." +
                        "pim.proto.projects.Project\032\032.google.prot" +
                        "obuf.BoolValue\022N\n\013editProject\022#.com.bht." +
                        "pim.proto.projects.Project\032\032.google.prot" +
                        "obuf.BoolValue\022H\n\rdeleteProject\022\033.google" +
                        ".protobuf.Int64Value\032\032.google.protobuf.B" +
                        "oolValue\022h\n\016getProjectList\022-.com.bht.pim" +
                        ".proto.projects.ProjectPagination\032\'.com." +
                        "bht.pim.proto.projects.ProjectList\022W\n\021ge" +
                        "tProjectNumbers\022\026.google.protobuf.Empty\032" +
                        "*.com.bht.pim.proto.projects.ProjectNumb" +
                        "ers\022J\n\023getNumberOfProjects\022\026.google.prot" +
                        "obuf.Empty\032\033.google.protobuf.Int64Value\022" +
                        "X\n\033getNumberOfProjectsByStatus\022\034.google." +
                        "protobuf.StringValue\032\033.google.protobuf.I" +
                        "nt64Value\022Y\n\034getNumberOfProjectsByKeywor" +
                        "d\022\034.google.protobuf.StringValue\032\033.google" +
                        ".protobuf.Int64ValueB\005P\001\210\001\001b\006proto3"
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
                                com.google.protobuf.EmptyProto.getDescriptor(),
                                ProjectInfoOuterClass.getDescriptor(),
                                EmployeeInfoOuterClass.getDescriptor(),
                                GroupOuterClass.getDescriptor(),
                        }, assigner);
        internal_static_com_bht_pim_proto_projects_Project_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_projects_Project_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_Project_descriptor,
                new String[]{"ProjectInfo", "Members",});
        internal_static_com_bht_pim_proto_projects_ProjectList_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_com_bht_pim_proto_projects_ProjectList_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_ProjectList_descriptor,
                new String[]{"Projects",});
        internal_static_com_bht_pim_proto_projects_ProjectNumbers_descriptor =
                getDescriptor().getMessageTypes().get(2);
        internal_static_com_bht_pim_proto_projects_ProjectNumbers_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_ProjectNumbers_descriptor,
                new String[]{"ProjectNumbers",});
        internal_static_com_bht_pim_proto_projects_ProjectPagination_descriptor =
                getDescriptor().getMessageTypes().get(3);
        internal_static_com_bht_pim_proto_projects_ProjectPagination_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_ProjectPagination_descriptor,
                new String[]{"MaxRow", "PageIndex", "Status", "Keyword",});
        com.google.protobuf.WrappersProto.getDescriptor();
        com.google.protobuf.EmptyProto.getDescriptor();
        ProjectInfoOuterClass.getDescriptor();
        EmployeeInfoOuterClass.getDescriptor();
        GroupOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}
