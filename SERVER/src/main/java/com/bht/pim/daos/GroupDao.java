package com.bht.pim.daos;

import com.bht.pim.entities.GroupEntity;

import java.util.List;

public interface GroupDao {

    // Next available/serialize Id value
    long nextIdValue();

    // Add a new group
    boolean addGroup(GroupEntity groupEntity);

    // Edit an existing group
    boolean updateGroup(GroupEntity groupEntity);

    // Delete a group
    boolean deleteGroup(long id);

    // Get an existing group
    GroupEntity getGroupById(long id);

    // Get all groups
    List<GroupEntity> getAllGroups();
}
