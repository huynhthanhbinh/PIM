package com.bht.pim.service;

import com.bht.pim.proto.groups.Group;

public interface GroupService {

    // Add a new group
    boolean addNewGroup(Group newGroup);
}
