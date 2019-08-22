package com.bht.pim.service;

import com.bht.pim.dto.ProjectDto;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.List;

public interface ProjectService {

    // Add new project
    boolean addNewProject(ProjectDto project);

    // Get a specific project
    ProjectDto getProjectById(long id);

    // Update a specific project
    boolean updateProject(ProjectDto project);

    // Delete a specific project
    boolean deleteProject(long id);

    // Get all project numbers
    List<Long> getProjectNumbers();

    // Get number of projects / row count(*)
    long getNumberOfProjects();

    // Get project list - pagination
    ObservableList<ProjectDto> getProjectList(int maxRow, int pageIndex,
                                              StringProperty keywordProperty,
                                              StringProperty statusProperty);
}
