package com.bht.pim.service;

import com.bht.pim.proto.groups.Group;
import javafx.collections.ObservableList;

public interface GroupService {

    // Add a new group
    boolean addNewGroup(Group newGroup);

    // Get all groups
    ObservableList<Group> getAllGroups();
}
