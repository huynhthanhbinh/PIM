// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Project.proto

package com.bht.pim.proto.project;

public final class ProjectOuterClass {
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_bht_pim_proto_project_Project_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_bht_pim_proto_project_Project_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\rProject.proto\022\031com.bht.pim.proto.proje" +
                        "ct\032\nDate.proto\"\276\001\n\007Project\022\n\n\002id\030\001 \001(\003\022\016" +
                        "\n\006number\030\002 \001(\003\022\014\n\004name\030\003 \001(\t\022\020\n\010customer" +
                        "\030\004 \001(\t\022\017\n\007groupId\030\005 \001(\003\022\016\n\006status\030\006 \001(\t\022" +
                        "+\n\005start\030\007 \001(\0132\034.com.bht.pim.proto.date." +
                        "Date\022)\n\003end\030\010 \001(\0132\034.com.bht.pim.proto.da" +
                        "te.DateB\002P\001b\006proto3"
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
                                com.bht.pim.proto.date.DateOuterClass.getDescriptor(),
                        }, assigner);
        internal_static_com_bht_pim_proto_project_Project_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_bht_pim_proto_project_Project_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_bht_pim_proto_project_Project_descriptor,
                new String[]{"Id", "Number", "Name", "Customer", "GroupId", "Status", "Start", "End",});
        com.bht.pim.proto.date.DateOuterClass.getDescriptor();
    }

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

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    // @@protoc_insertion_point(outer_class_scope)
}
