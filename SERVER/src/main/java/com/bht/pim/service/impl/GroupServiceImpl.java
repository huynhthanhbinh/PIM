package com.bht.pim.service.impl;

import com.bht.pim.dao.GroupDao;
import com.bht.pim.dto.Group;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupDao groupDao;

    @Override
    public List<Group> getAllGroups() {

        List<GroupEntity> groupEntities =
                groupDao.getAllGroups();

        List<Group> groups = new ArrayList<>();

        groupEntities.forEach(groupEntity -> {
            Group group = new Group();

            group.setId(groupEntity.getId());
            group.setGroupLeaderId(groupEntity.getGroupLeader().getId());

            EmployeeEntity groupLeader = groupEntity.getGroupLeader();

            group.setGroupLeaderName(
                    groupLeader.getVisa() + " - " +
                            groupLeader.getLastName() + " " +
                            groupLeader.getFirstName());

            groups.add(group);
        });

        return groups;
    }
}
