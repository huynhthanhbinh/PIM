package com.bht.pim.service;

import java.util.List;
import java.util.Map;

import com.bht.pim.base.BaseBean;
import com.bht.pim.dto.ProjectDto;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * @author bht
 */
public interface ProjectService extends BaseBean {

    // Add new project
    boolean addNewProject(ProjectDto project);

    // Get a specific project
    ProjectDto getProjectById(long id);

    // Get a specific project by number instead of id
    ProjectDto getProjectByNumber(long number);

    // Update a specific project
    boolean updateProject(ProjectDto project);

    // Delete a specific project
    boolean deleteProject(long id);

    // Get all project numbers
    List<Long> getProjectNumbers();

    // Get count project group-by status
    Map<String, Long> getProjectsGroupByStatus();

    // Get number of projects / row count(*)
    long getNumberOfProjects();

    // Get project row count where STATUS = status
    long getNumberOfProjectsByStatus(StringProperty statusProperty);

    // Get project row count where contains keyword
    long getNumberOfProjectsByKeyword(StringProperty keywordProperty);

    // Get project list - pagination
    ObservableList<ProjectDto> getProjectList(int maxRow, int pageIndex,
                                              StringProperty keywordProperty,
                                              StringProperty statusProperty);
}
