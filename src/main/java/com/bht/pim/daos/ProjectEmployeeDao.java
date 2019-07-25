package com.bht.pim.daos;

import com.bht.pim.entities.ProjectEmployeeEntity;

import java.util.List;

public interface ProjectEmployeeDao {

    // Add a list of employee to project
    boolean addProjectEmployees(
            List<ProjectEmployeeEntity> projectEmployeeEntityList);

    // Remove a list of employee out of project
    boolean deleteProjectEmployees(
            List<ProjectEmployeeEntity> projectEmployeeEntityList);

    // Get all employee works on a specific project
    List<ProjectEmployeeEntity> getEmployeesByProject(long projectId);
}
