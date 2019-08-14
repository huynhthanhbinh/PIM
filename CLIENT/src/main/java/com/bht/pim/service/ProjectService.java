package com.bht.pim.service;

import com.bht.pim.proto.projects.Project;
import javafx.collections.ObservableList;

import java.util.List;

public interface ProjectService {

    // Add new project
    boolean addNewProject(Project project);

    // Get a specific project
    Project getProjectById(long id);

    // Update a specific project
    boolean updateProject(Project project);

    // Delete a specific project
    boolean deleteProject(long id);

    // Get all project numbers
    List<Long> getProjectNumbers();

    // Get all of projects
    ObservableList<Project> getAllProjects();
}
