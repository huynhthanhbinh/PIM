package com.bht.pim.service.impl;

import com.bht.pim.proto.projects.Project;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import com.bht.pim.service.ProjectService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectServiceGrpc.ProjectServiceBlockingStub stub;

    // Add new project
    @Override
    public boolean addNewProject(Project project) {
        return stub.addNewProject(project).getValue();
    }

    // Get a specific project
    @Override
    public Project getProjectById(long id) {
        Int64Value projectId = Int64Value.newBuilder().setValue(id).build();
        return stub.getProjectById(projectId);
    }

    // Update a specific project
    @Override
    public boolean updateProject(Project project) {
        return stub.editProject(project).getValue();
    }

    // Delete a specific project
    @Override
    public boolean deleteProject(long id) {
        Int64Value projectId = Int64Value.newBuilder().setValue(id).build();
        return stub.deleteProject(projectId).getValue();
    }

    // Get all project numbers
    @Override
    public List<Long> getProjectNumbers() {
        return stub.getProjectNumbers(Empty.getDefaultInstance())
                .getProjectNumbersList();
    }

    // Get all of projects
    @Override
    public ObservableList<Project> getAllProjects() {
        return FXCollections.observableArrayList(stub
                .getProjectList(Empty.getDefaultInstance())
                .getProjectsList());
    }
}
