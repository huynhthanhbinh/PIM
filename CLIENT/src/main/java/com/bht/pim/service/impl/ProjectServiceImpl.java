package com.bht.pim.service.impl;

import com.bht.pim.dto.ProjectDto;
import com.bht.pim.mapper.ProjectMapper;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.proto.projects.ProjectGroup;
import com.bht.pim.proto.projects.ProjectPagination;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import com.bht.pim.service.ProjectService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bht
 */
@Log4j
@Service
public final class ProjectServiceImpl implements ProjectService {

    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectServiceGrpc.ProjectServiceBlockingStub stub;

    // Add new project
    @Override
    public boolean addNewProject(ProjectDto project) {
        return stub
                .addNewProject(projectMapper
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

    @Override
    public ProjectDto getProjectByNumber(long number) {
        Int64Value projectNumber = Int64Value.newBuilder().setValue(number).build();
        return projectMapper.toProjectDto(stub
                .getProjectByNumber(projectNumber));
    }

    // Update a specific project
    @Override
    public boolean updateProject(ProjectDto project) {
        return stub
                .editProject(projectMapper
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
        return stub
                .getProjectNumbers(Empty.getDefaultInstance())
                .getProjectNumbersList();
    }

    // Get count project group-by status
    @Override
    public Map<String, Long> getProjectsGroupByStatus() {
        return stub
                .getProjectGroups(Empty.getDefaultInstance())
                .getProjectGroupsList().stream()
                .collect(Collectors.toMap(ProjectGroup::getStatus, ProjectGroup::getCount));
    }

    // Get all of projects
    @Override
    public ObservableList<ProjectDto> getProjectList(int maxRow, int pageIndex,
                                                     StringProperty keywordProperty,
                                                     StringProperty statusProperty) {

        if (statusProperty.get() != null) {
            return FXCollections.observableArrayList(
                    projectMapper.toProjectDtoList(stub
                            .getProjectList(ProjectPagination.newBuilder()
                                    .setMaxRow(maxRow)
                                    .setPageIndex(pageIndex)
                                    .setStatus(statusMapper.toSqlStatus(statusProperty))
                                    .build())
                            .getProjectsList()));
        }

        if (keywordProperty.get() != null) {
            return FXCollections.observableArrayList(
                    projectMapper.toProjectDtoList(stub
                            .getProjectList(ProjectPagination.newBuilder()
                                    .setMaxRow(maxRow)
                                    .setPageIndex(pageIndex)
                                    .setKeyword(keywordProperty.get())
                                    .build())
                            .getProjectsList()));
        }

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
        return stub
                .getNumberOfProjects(Empty.getDefaultInstance())
                .getValue();
    }

    @Override
    public long getNumberOfProjectsByStatus(StringProperty statusProperty) {
        return stub
                .getNumberOfProjectsByStatus(StringValue.newBuilder()
                        .setValue(statusMapper.toSqlStatus(statusProperty))
                        .build())
                .getValue();
    }

    @Override
    public long getNumberOfProjectsByKeyword(StringProperty keywordProperty) {
        return stub
                .getNumberOfProjectsByKeyword(StringValue.newBuilder()
                        .setValue(keywordProperty.get())
                        .build())
                .getValue();
    }
}
