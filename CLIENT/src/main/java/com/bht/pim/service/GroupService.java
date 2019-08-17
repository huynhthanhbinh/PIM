package com.bht.pim.service;

import com.bht.pim.dto.GroupDto;
import javafx.collections.ObservableList;

public interface GroupService {

    // Add a new group
    boolean addNewGroup(GroupDto newGroup);

    // get a specific employee
    GroupDto getGroupById(long id);

    // Get all groups
    ObservableList<GroupDto> getAllGroups();
}
