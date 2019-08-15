package com.bht.pim.service;

import com.bht.pim.proto.groups.Group;
import javafx.collections.ObservableList;

public interface GroupService {

    // Add a new group
    boolean addNewGroup(Group newGroup);

    // get a specific employee
    Group getGroupById(long id);

    // Get all groups
    ObservableList<Group> getAllGroups();
}
