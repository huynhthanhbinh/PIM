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

    // Get project row count(*)
    long getNumberOfProjects();

    // Get project row count where STATUS = status
    long getNumberOfProjectsByStatus(String status);

    // Get project row count where contains keyword
    long getNumberOfProjectsByKeyword(String keyword);

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

    // Get an existing project but by number instead of id
    ProjectEntity getProjectByNumber(long number);

    // Get all projects - pagination
    List<ProjectEntity> getProjectList(int maxRow, int pageIndex);

    // Get all projects - pagination - by status
    List<ProjectEntity> getProjectListByStatus(int maxRow, int pageIndex, String status);

    // Get all projects - pagination - by keyword
    List<ProjectEntity> getProjectListByKeyword(int maxRow, int pageIndex, String keyword);
}
