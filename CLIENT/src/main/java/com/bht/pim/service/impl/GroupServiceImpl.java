package com.bht.pim.service.impl;

import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.service.GroupService;
import com.google.protobuf.Empty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupServiceGrpc.GroupServiceBlockingStub stub;

    // Add a new group
    @Override
    public boolean addNewGroup(Group newGroup) {
        return stub.addNewGroup(newGroup).getValue();
    }

    // Get all groups
    @Override
    public ObservableList<Group> getAllGroups() {
        return FXCollections.observableList(stub
                .getGroupList(Empty.getDefaultInstance())
                .getGroupsList());
    }
}
