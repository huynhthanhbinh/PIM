package com.bht.pim.dao;

import java.util.List;

import com.bht.pim.entity.GroupEntity;

public interface GroupDao {

    // Next available/serialize Id value
    long nextIdValue();

    // Add a new group
    boolean addGroup(GroupEntity groupEntity);

    // Get an existing group
    GroupEntity getGroupById(long id);

    // Get all groups
    List<GroupEntity> getGroupList(int maxRow, int pageIndex);

    // Get project row count(*)
    long getNumberOfGroups();
}