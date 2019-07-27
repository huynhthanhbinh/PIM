package com.bht.pim.service;

import com.bht.pim.dto.Group;

import java.util.List;

public interface GroupService {

    // Get a specific group
    Group getGroupById(long id);

    // Get all groups
    List<Group> getAllGroups();
}
