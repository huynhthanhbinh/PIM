// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Project.proto

package com.bht.pim.proto.projects;

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

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\rProject.proto\022\032com.bht.pim.proto.proje" +
                        "cts\032\013Group.proto\032\037google/protobuf/timest" +
                        "amp.proto\"\331\001\n\007Project\022\n\n\002id\030\001 \001(\003\022\016\n\006num" +
                        "ber\030\002 \001(\003\022\014\n\004name\030\003 \001(\t\022\020\n\010customer\030\004 \001(" +
                        "\t\022\016\n\006status\030\005 \001(\t\022)\n\005start\030\006 \001(\0132\032.googl" +
                        "e.protobuf.Timestamp\022\'\n\003end\030\007 \001(\0132\032.goog" +
                        "le.protobuf.Timestamp\022.\n\005group\030\010 \001(\0132\037.c" +
                        "om.bht.pim.proto.groups.GroupB\005P\001\210\001\001b\006pr" +
                        "oto3"
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
                                GroupOuterClass.getDescriptor(),
                                com.google.protobuf.TimestampProto.getDescriptor(),
                        }, assigner);
        internal_static_com_bht_pim_proto_projects_Project_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_projects_Project_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_Project_descriptor,
                new String[]{"Id", "Number", "Name", "Customer", "Status", "Start", "End", "Group",});
        GroupOuterClass.getDescriptor();
        com.google.protobuf.TimestampProto.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}
