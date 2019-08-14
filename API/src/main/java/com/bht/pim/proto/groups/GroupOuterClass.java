// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Group.proto

package com.bht.pim.proto.groups;

import com.bht.pim.proto.projects.ProjectInfoOuterClass;

public final class GroupOuterClass {
    private GroupOuterClass() {
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
            internal_static_com_bht_pim_proto_groups_Group_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_groups_Group_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_groups_GroupList_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_groups_GroupList_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\013Group.proto\022\030com.bht.pim.proto.groups\032" +
                        "\036google/protobuf/wrappers.proto\032\033google/" +
                        "protobuf/empty.proto\032\024info/GroupInfo.pro" +
                        "to\032\026info/ProjectInfo.proto\"\202\001\n\005Group\0226\n\t" +
                        "groupInfo\030\001 \001(\0132#.com.bht.pim.proto.grou" +
                        "ps.GroupInfo\022A\n\020enrolledProjects\030\002 \003(\0132\'" +
                        ".com.bht.pim.proto.projects.ProjectInfo\"" +
                        "<\n\tGroupList\022/\n\006groups\030\001 \003(\0132\037.com.bht.p" +
                        "im.proto.groups.Group2\365\001\n\014GroupService\022L" +
                        "\n\014getGroupById\022\033.google.protobuf.Int64Va" +
                        "lue\032\037.com.bht.pim.proto.groups.Group\022J\n\013" +
                        "addNewGroup\022\037.com.bht.pim.proto.groups.G" +
                        "roup\032\032.google.protobuf.BoolValue\022K\n\014getG" +
                        "roupList\022\026.google.protobuf.Empty\032#.com.b" +
                        "ht.pim.proto.groups.GroupListB\005P\001\210\001\001b\006pr" +
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
                                com.google.protobuf.WrappersProto.getDescriptor(),
                                com.google.protobuf.EmptyProto.getDescriptor(),
                                GroupInfoOuterClass.getDescriptor(),
                                ProjectInfoOuterClass.getDescriptor(),
                        }, assigner);
        internal_static_com_bht_pim_proto_groups_Group_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_groups_Group_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_groups_Group_descriptor,
                new String[]{"GroupInfo", "EnrolledProjects",});
        internal_static_com_bht_pim_proto_groups_GroupList_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_com_bht_pim_proto_groups_GroupList_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_groups_GroupList_descriptor,
                new String[]{"Groups",});
        com.google.protobuf.WrappersProto.getDescriptor();
        com.google.protobuf.EmptyProto.getDescriptor();
        GroupInfoOuterClass.getDescriptor();
        ProjectInfoOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}
