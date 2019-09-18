package com.bht.pim.service;

import com.bht.pim.base.BaseBean;
import com.bht.pim.dto.GroupDto;

import javafx.collections.ObservableList;

/**
 * @author bht
 */
public interface GroupService extends BaseBean {

    // Add a new group
    boolean addNewGroup(GroupDto newGroup);

    // get a specific employee
    GroupDto getGroupById(long id);

    // Get project row count(*)
    long getNumberOfGroups();

    // Get all groups
    ObservableList<GroupDto> getGroupList(int maxRow, int pageIndex);
}
