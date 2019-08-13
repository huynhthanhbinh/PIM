package com.bht.pim.service.impl;

import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.groups.GroupListServiceGrpc;
import com.bht.pim.service.GroupListService;
import com.google.protobuf.Empty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupListServiceImpl implements GroupListService {

    @Autowired
    private GroupListServiceGrpc.GroupListServiceBlockingStub stub;

    // Get all groups
    @Override
    public ObservableList<Group> getAllGroups() {
        return FXCollections.observableList(stub
                .getGroupList(Empty.getDefaultInstance())
                .getGroupsList());
    }
}
