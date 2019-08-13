package com.bht.pim.service;

import com.bht.pim.proto.projects.ProjectInfo;

public interface ProjectService {

    // Add new project
    boolean addNewProject(ProjectInfo projectInfo);

    // Get a specific project
    ProjectInfo getProjectById(long id);

    // Update a specific project
    boolean updateProject(ProjectInfo projectInfo);

    // Delete a specific project
    boolean deleteProject(long id);
}
