package com.bht.pim.service.impl;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.GroupDto;
import com.bht.pim.mapper.GroupMapper;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.service.GroupService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    private ObjectProperty<GroupServiceGrpc.GroupServiceBlockingStub> stubProperty
            = AppConfiguration.GROUP_SERVICE_STUB_PROPERTY;

    // Add a new group
    @Override
    public boolean addNewGroup(GroupDto newGroup) {
        return stubProperty.get()
                .addNewGroup(groupMapper
                        .toGroup(newGroup))
                .getValue();
    }

    // Get a specific group
    @Override
    public GroupDto getGroupById(long id) {
        return groupMapper.toGroupDto(stubProperty.get()
                .getGroupById(Int64Value.newBuilder()
                        .setValue(id)
                        .build()));
    }

    // Get all groups
    @Override
    public ObservableList<GroupDto> getAllGroups() {
        return FXCollections.observableList(
                groupMapper.toGroupDtoList(stubProperty.get()
                        .getGroupList(Empty.getDefaultInstance())
                        .getGroupsList()));
    }
}
