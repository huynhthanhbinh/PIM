package com.bht.pim.service.impl;

import com.bht.pim.dao.GroupDao;
import com.bht.pim.dto.Group;
import com.bht.pim.dto.Project;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupDao groupDao;

    @Override
    public Group getGroupById(long id) {
        GroupEntity groupEntity =
                groupDao.getGroupById(id);

        if (groupEntity != null) {
            Group group = new Group();

            group.setId(groupEntity.getId());

            EmployeeEntity groupLeader = groupEntity
                    .getGroupLeader();

            group.setGroupLeaderId(groupLeader.getId());
            group.setGroupLeaderVisa(groupLeader.getVisa());
            group.setGroupLeaderName(
                    groupLeader.getLastName() + " " + groupLeader.getFirstName());

            Set<Project> projects = new HashSet<>();

            groupEntity.getJoinedProjects().forEach(projectEntity -> {
                Project project = new Project();

                project.setId(projectEntity.getId());
                project.setNumber(projectEntity.getNumber());
                project.setName(projectEntity.getName());
                project.setCustomer(projectEntity.getCustomer());

                projects.add(project);
            });

            group.setProjects(projects);

            return group;
        }

        return null;
    }

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
