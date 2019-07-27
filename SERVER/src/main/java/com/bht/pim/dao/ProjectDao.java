package com.bht.pim.dao;

// Use entity User instead of model User
// Remind: entity User != model User
//  + User Model  : for presentation layer (view)
//  + User Entity : for data access layer (repo)

import com.bht.pim.entity.ProjectEntity;

import java.util.List;

// For accessing database
public interface ProjectDao {

    // Get next project Id
    long nextIdValue();

    // Get all projects number
    List<Long> getAllProjectsNumber();

    // Add a new project
    boolean addProject(ProjectEntity projectEntity);

    // Edit an existing project
    boolean updateProject(ProjectEntity projectEntity);

    // Delete a new project only
    boolean deleteProject(long id);

    // Get an existing project
    ProjectEntity getProjectById(long id);

    // Get all projects
    List<ProjectEntity> getAllProjects();
}
