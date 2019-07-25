package com.bht.pim.services;

import com.bht.pim.models.Project;

import java.util.List;

// CRUD
// Create - Read - Update - Delete
public interface ProjectService {

    // Add a new project, new group
    boolean addProject(Project project);

    // Add a new project, existing group
    boolean addProject(Project project, long groupLeaderId);

    // Edit an existing project
    boolean updateProject(Project project);

    // Delete a new project only
    boolean deleteProject(long id);

    // Get an existing project
    Project getProjectById(long id);

    // Get all projects
    List<Project> getAllProjects();
}
