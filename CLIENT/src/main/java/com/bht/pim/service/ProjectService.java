package com.bht.pim.service;

import com.bht.pim.dto.ProjectDto;
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

    // Get all of projects
    ObservableList<ProjectDto> getAllProjects();
}
