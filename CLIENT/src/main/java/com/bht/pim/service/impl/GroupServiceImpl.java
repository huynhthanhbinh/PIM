package com.bht.pim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bht.pim.dto.GroupDto;
import com.bht.pim.mapper.GroupMapper;
import com.bht.pim.proto.groups.GroupPagination;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.service.GroupService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupServiceGrpc.GroupServiceBlockingStub stub;

    // Add a new group
    @Override
    public boolean addNewGroup(GroupDto newGroup) {
        return stub
                .addNewGroup(groupMapper.toGroup(newGroup))
                .getValue();
    }

    // Get a specific group
    @Override
    public GroupDto getGroupById(long id) {
        return groupMapper.toGroupDto(stub
                .getGroupById(Int64Value.newBuilder()
                        .setValue(id)
                        .build()));
    }

    @Override
    public long getNumberOfGroups() {
        return stub
                .getNumberOfGroups(Empty.getDefaultInstance())
                .getValue();
    }

    // Get all groups
    @Override
    public ObservableList<GroupDto> getGroupList(int maxRow, int pageIndex) {
        return FXCollections.observableList(
                groupMapper.toGroupDtoList(stub
                        .getGroupList(GroupPagination.newBuilder()
                                .setMaxRow(maxRow)
                                .setPageIndex(pageIndex)
                                .build())
                        .getGroupsList()));
    }
}
