// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Project.proto

package com.bht.pim.proto.projects;

public interface ProjectPaginationOrBuilder extends
        // @@protoc_insertion_point(interface_extends:com.bht.pim.proto.projects.ProjectPagination)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 maxRow = 1;</code>
     */
    int getMaxRow();

    /**
     * <code>int32 pageIndex = 2;</code>
     */
    int getPageIndex();

    /**
     * <code>string status = 3;</code>
     */
    String getStatus();

    /**
     * <code>string status = 3;</code>
     */
    com.google.protobuf.ByteString
    getStatusBytes();

    /**
     * <code>string keyword = 4;</code>
     */
    String getKeyword();

    /**
     * <code>string keyword = 4;</code>
     */
    com.google.protobuf.ByteString
    getKeywordBytes();
}