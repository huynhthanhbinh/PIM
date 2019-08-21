package com.bht.pim.service.impl;

import com.bht.pim.dto.ProjectDto;
import com.bht.pim.mapper.ProjectMapper;
import com.bht.pim.proto.projects.ProjectPagination;
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
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectServiceGrpc.ProjectServiceBlockingStub stub;

    // Add new project
    @Override
    public boolean addNewProject(ProjectDto project) {
        return stub.addNewProject(projectMapper
                .toProject(project))
                .getValue();
    }

    // Get a specific project
    @Override
    public ProjectDto getProjectById(long id) {
        Int64Value projectId = Int64Value.newBuilder().setValue(id).build();
        return projectMapper.toProjectDto(stub
                .getProjectById(projectId));
    }

    // Update a specific project
    @Override
    public boolean updateProject(ProjectDto project) {
        return stub.editProject(projectMapper
                .toProject(project))
                .getValue();
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
    public ObservableList<ProjectDto> getProjectList(int maxRow, int pageIndex) {
        return FXCollections.observableArrayList(
                projectMapper.toProjectDtoList(stub
                        .getProjectList(ProjectPagination.newBuilder()
                                .setMaxRow(maxRow)
                                .setPageIndex(pageIndex)
                                .build())
                        .getProjectsList()));
    }

    @Override
    public long getNumberOfProjects() {
        return stub.getNumberOfProjects(Empty.getDefaultInstance()).getValue();
    }
}
