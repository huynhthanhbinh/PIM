package com.bht.pim.service;

import com.bht.pim.proto.groups.Group;
import javafx.collections.ObservableList;

public interface GroupListService {

    // Get all groups
    ObservableList<Group> getAllGroups();
}
