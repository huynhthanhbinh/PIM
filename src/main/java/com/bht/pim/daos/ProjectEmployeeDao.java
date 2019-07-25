package com.bht.pim.daos;

import com.bht.pim.entities.ProjectEmployeeEntity;

import java.util.List;

public interface ProjectEmployeeDao {

    // Add a new project_employee
    boolean addProjectEmployee(
            ProjectEmployeeEntity projectEmployeeEntity);

    // Add a list of employee to project
    boolean addProjectEmployeeList(
            List<ProjectEmployeeEntity> projectEmployeeEntityList);

    // Delete a project_employee
    boolean deleteProjectEmployee(int id);

    // Get an existing project_employee
    ProjectEmployeeEntity getProjectEmployeeById(int id);

    // Get all employee works on a specific project
    List<ProjectEmployeeEntity> getEmployeesByProject(long projectId);
}
