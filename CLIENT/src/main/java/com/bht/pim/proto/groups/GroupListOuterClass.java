// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GroupList.proto

package com.bht.pim.proto.groups;

public final class GroupListOuterClass {
    private GroupListOuterClass() {
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
            internal_static_com_bht_pim_proto_groups_GroupList_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_groups_GroupList_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_groups_NoParam_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_groups_NoParam_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\017GroupList.proto\022\030com.bht.pim.proto.gro" +
                        "ups\032\013Group.proto\"<\n\tGroupList\022/\n\006groups\030" +
                        "\001 \003(\0132\037.com.bht.pim.proto.groups.Group\"\t" +
                        "\n\007NoParam2j\n\020GroupListService\022V\n\014getGrou" +
                        "pList\022!.com.bht.pim.proto.groups.NoParam" +
                        "\032#.com.bht.pim.proto.groups.GroupListB\005P" +
                        "\001\210\001\001b\006proto3"
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
                        }, assigner);
        internal_static_com_bht_pim_proto_groups_GroupList_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_groups_GroupList_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_groups_GroupList_descriptor,
                new String[]{"Groups",});
        internal_static_com_bht_pim_proto_groups_NoParam_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_com_bht_pim_proto_groups_NoParam_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_groups_NoParam_descriptor,
                new String[]{});
        GroupOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}
