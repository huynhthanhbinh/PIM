// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: info/ProjectInfo.proto

package com.bht.pim.proto.projects;

import com.bht.pim.proto.groups.GroupInfoOuterClass;

public final class ProjectInfoOuterClass {
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

    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_projects_ProjectInfo_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_projects_ProjectInfo_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\026info/ProjectInfo.proto\022\032com.bht.pim.pr" +
                        "oto.projects\032\024info/GroupInfo.proto\032\037goog" +
                        "le/protobuf/timestamp.proto\"\341\001\n\013ProjectI" +
                        "nfo\022\n\n\002id\030\001 \001(\003\022\016\n\006number\030\002 \001(\003\022\014\n\004name\030" +
                        "\003 \001(\t\022\020\n\010customer\030\004 \001(\t\022\016\n\006status\030\005 \001(\t\022" +
                        ")\n\005start\030\006 \001(\0132\032.google.protobuf.Timesta" +
                        "mp\022\'\n\003end\030\007 \001(\0132\032.google.protobuf.Timest" +
                        "amp\0222\n\005group\030\010 \001(\0132#.com.bht.pim.proto.g" +
                        "roups.GroupInfoB\005P\001\210\001\001b\006proto3"
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
                                GroupInfoOuterClass.getDescriptor(),
                                com.google.protobuf.TimestampProto.getDescriptor(),
                        }, assigner);
        internal_static_com_bht_pim_proto_projects_ProjectInfo_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_projects_ProjectInfo_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_projects_ProjectInfo_descriptor,
                new String[]{"Id", "Number", "Name", "Customer", "Status", "Start", "End", "Group",});
        GroupInfoOuterClass.getDescriptor();
        com.google.protobuf.TimestampProto.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}
