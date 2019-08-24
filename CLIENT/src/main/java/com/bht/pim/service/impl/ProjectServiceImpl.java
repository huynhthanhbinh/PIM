package com.bht.pim.service.impl;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.mapper.ProjectMapper;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.proto.projects.ProjectPagination;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import com.bht.pim.service.ProjectService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private ProjectMapper projectMapper;

    private ObjectProperty<ProjectServiceGrpc.ProjectServiceBlockingStub> stubProperty
            = AppConfiguration.PROJECT_SERVICE_STUB_PROPERTY;

    // Add new project
    @Override
    public boolean addNewProject(ProjectDto project) {
        return stubProperty.get()
                .addNewProject(projectMapper
                        .toProject(project))
                .getValue();
    }

    // Get a specific project
    @Override
    public ProjectDto getProjectById(long id) {
        Int64Value projectId = Int64Value.newBuilder().setValue(id).build();
        return projectMapper.toProjectDto(stubProperty.get()
                .getProjectById(projectId));
    }

    // Update a specific project
    @Override
    public boolean updateProject(ProjectDto project) {
        return stubProperty.get()
                .editProject(projectMapper
                        .toProject(project))
                .getValue();
    }

    // Delete a specific project
    @Override
    public boolean deleteProject(long id) {
        Int64Value projectId = Int64Value.newBuilder().setValue(id).build();
        return stubProperty.get()
                .deleteProject(projectId).getValue();
    }

    // Get all project numbers
    @Override
    public List<Long> getProjectNumbers() {
        return stubProperty.get()
                .getProjectNumbers(Empty.getDefaultInstance())
                .getProjectNumbersList();
    }

    // Get all of projects
    @Override
    public ObservableList<ProjectDto> getProjectList(int maxRow, int pageIndex,
                                                     StringProperty keywordProperty,
                                                     StringProperty statusProperty) {

        if (statusProperty.get() != null) {
            return FXCollections.observableArrayList(
                    projectMapper.toProjectDtoList(stubProperty.get()
                            .getProjectList(ProjectPagination.newBuilder()
                                    .setMaxRow(maxRow)
                                    .setPageIndex(pageIndex)
                                    .setStatus(statusMapper.toSqlStatus(statusProperty))
                                    .build())
                            .getProjectsList()));
        }

        if (keywordProperty.get() != null) {
            return FXCollections.observableArrayList(
                    projectMapper.toProjectDtoList(stubProperty.get()
                            .getProjectList(ProjectPagination.newBuilder()
                                    .setMaxRow(maxRow)
                                    .setPageIndex(pageIndex)
                                    .setKeyword(keywordProperty.get())
                                    .build())
                            .getProjectsList()));
        }

        return FXCollections.observableArrayList(
                projectMapper.toProjectDtoList(stubProperty.get()
                        .getProjectList(ProjectPagination.newBuilder()
                                .setMaxRow(maxRow)
                                .setPageIndex(pageIndex)
                                .build())
                        .getProjectsList()));
    }

    @Override
    public long getNumberOfProjects() {
        return stubProperty.get()
                .getNumberOfProjects(Empty
                        .getDefaultInstance())
                .getValue();
    }

    @Override
    public long getNumberOfProjectsByStatus(StringProperty statusProperty) {
        return stubProperty.get()
                .getNumberOfProjectsByStatus(StringValue.newBuilder()
                        .setValue(statusMapper.toSqlStatus(statusProperty))
                        .build())
                .getValue();
    }

    @Override
    public long getNumberOfProjectsByKeyword(StringProperty keywordProperty) {
        return stubProperty.get()
                .getNumberOfProjectsByKeyword(StringValue.newBuilder()
                        .setValue(keywordProperty.get())
                        .build())
                .getValue();
    }
}
