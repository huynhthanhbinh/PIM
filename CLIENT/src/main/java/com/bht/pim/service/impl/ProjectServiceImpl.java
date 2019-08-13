package com.bht.pim.service.impl;

import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import com.bht.pim.service.ProjectService;
import com.google.protobuf.Int64Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectServiceGrpc.ProjectServiceBlockingStub stub;

    // Add new project
    @Override
    public boolean addNewProject(ProjectInfo projectInfo) {
        return stub.addNewProject(projectInfo).getValue();
    }

    // Get a specific project
    @Override
    public ProjectInfo getProjectById(long id) {
        Int64Value projectId = Int64Value.newBuilder().setValue(id).build();
        return stub.getProjectById(projectId);
    }

    // Update a specific project
    @Override
    public boolean updateProject(ProjectInfo projectInfo) {
        return stub.editProject(projectInfo).getValue();
    }

    // Delete a specific project
    @Override
    public boolean deleteProject(long id) {
        Int64Value projectId = Int64Value.newBuilder().setValue(id).build();
        return stub.deleteProject(projectId).getValue();
    }
}
