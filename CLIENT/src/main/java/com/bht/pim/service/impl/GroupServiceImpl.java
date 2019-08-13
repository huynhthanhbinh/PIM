package com.bht.pim.service.impl;

import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.service.GroupService;
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
}
