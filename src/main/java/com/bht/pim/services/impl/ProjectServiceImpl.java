package com.bht.pim.services.impl;

import com.bht.pim.daos.GroupDao;
import com.bht.pim.daos.ProjectDao;
import com.bht.pim.daos.ProjectEmployeeDao;
import com.bht.pim.entities.GroupEntity;
import com.bht.pim.entities.ProjectEmployeeEntity;
import com.bht.pim.entities.ProjectEntity;
import com.bht.pim.models.Project;
import com.bht.pim.services.ProjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    // Autowired into new ProjectDaoImpl object
    // If we have more than 1 implement for ProjectDao Interface
    // We have to use @Qualifier( <exact impl of ProjectDao> )
    // For eg. Autowired ProjectDaoImpl projectDao
    // As polymorphism of Object Oriented Programming
    // We can use interface to reference instead of implementation
    // Therefore, we can rewrite as: Autowired ProjectDao projectDao
    // That is the best-practise, common coding style !
    @Autowired
    ProjectDao projectDao;

    @Autowired
    GroupDao groupDao;

    @Autowired
    ProjectEmployeeDao projectEmployeeDao;


    @Override
    public boolean addProject(Project project) {
        List<ProjectEmployeeEntity> projectEmployees =
                new ArrayList<>();

        long projectId = projectDao.nextIdValue();

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setGroupId(project.getGroupId());
        projectEntity.setNumber(project.getNumber());
        projectEntity.setName(project.getName());
        projectEntity.setCustomer(project.getCustomer());
        projectEntity.setStatus(toDaoStatus(project.getStatus()));
        projectEntity.setStart(toSqlDate(project.getStart()));
        if (project.getEnd() != null) {
            projectEntity.setEnd(toSqlDate(project.getEnd()));
        }

        project.getMembers().forEach(employeeId -> {
            ProjectEmployeeEntity projectEmployeeEntity =
                    new ProjectEmployeeEntity();

            projectEmployeeEntity.setProjectId(projectId);
            projectEmployeeEntity.setEmployeeId(employeeId);

            projectEmployees.add(projectEmployeeEntity);
        });

        return projectDao.addProject(projectEntity) &&
                projectEmployeeDao.addProjectEmployees(projectEmployees);
    }

    @Override
    public boolean addProject(Project project, long groupLeaderId) {
        List<ProjectEmployeeEntity> projectEmployees =
                new ArrayList<>();

        long projectId = projectDao.nextIdValue();

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setGroupId(groupDao.nextIdValue());
        projectEntity.setNumber(project.getNumber());
        projectEntity.setName(project.getName());
        projectEntity.setCustomer(project.getCustomer());
        projectEntity.setStatus(toDaoStatus(project.getStatus()));
        projectEntity.setStart(toSqlDate(project.getStart()));
        if (project.getEnd() != null) {
            projectEntity.setEnd(toSqlDate(project.getEnd()));
        }

        project.getMembers().forEach(employeeId -> {
            ProjectEmployeeEntity projectEmployeeEntity =
                    new ProjectEmployeeEntity();

            projectEmployeeEntity.setProjectId(projectId);
            projectEmployeeEntity.setEmployeeId(employeeId);

            projectEmployees.add(projectEmployeeEntity);
        });

        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupLeaderId(groupLeaderId);

        return groupDao.addGroup(groupEntity) &&
                projectDao.addProject(projectEntity) &&
                projectEmployeeDao.addProjectEmployees(projectEmployees);
    }

    @Override
    public boolean updateProject(Project project) {
        long projectId = project.getId();

        ProjectEntity projectEntity =
                projectDao.getProjectById(projectId);

        if (projectEntity != null) {

            projectEntity.setGroupId(project.getGroupId());
            projectEntity.setNumber(project.getNumber());
            projectEntity.setName(project.getName());
            projectEntity.setCustomer(project.getCustomer());
            projectEntity.setStatus(toDaoStatus(project.getStatus()));
            projectEntity.setStart(toSqlDate(project.getStart()));
            if (project.getEnd() != null) {
                projectEntity.setEnd(toSqlDate(project.getEnd()));
            }

            List<ProjectEmployeeEntity> projectEmployeeEntities
                    = new ArrayList<>();

            project.getMembers().forEach(memberId -> {
                ProjectEmployeeEntity entity = new ProjectEmployeeEntity();
                entity.setEmployeeId(memberId);
                entity.setProjectId(projectId);
                projectEmployeeEntities.add(entity);
            });

            List<List<ProjectEmployeeEntity>> differences
                    = memberDifferences(
                    projectEmployeeDao.getEmployeesByProject(projectId),
                    projectEmployeeEntities
            );

            List<ProjectEmployeeEntity> removeList = differences.get(0);
            List<ProjectEmployeeEntity> addList = differences.get(1);

            logger.info("Remove List");
            removeList.forEach(logger::info);
            logger.info("Add List");
            addList.forEach(logger::info);

            return projectDao.updateProject(projectEntity) &&
                    projectEmployeeDao.addProjectEmployees(addList) &&
                    projectEmployeeDao.deleteProjectEmployees(removeList);
        }

        return false;
    }

    @Override
    public boolean deleteProject(long id) {

        // Check if project is exist
        ProjectEntity projectEntity =
                projectDao.getProjectById(id);

        // Check if it is a new project,
        // otherwise, cannot delete
        if (projectEntity != null
                && projectEntity.getStatus().equals("NEW")) {
            return projectDao.deleteProject(id);
        }
        return false;
    }

    @Override
    public Project getProjectById(long id) {
        ProjectEntity projectEntity =
                projectDao.getProjectById(id);

        if (projectEntity != null) {
            Project project = new Project();

            project.setId(projectEntity.getId());
            project.setNumber(projectEntity.getNumber());
            project.setName(projectEntity.getName());
            project.setCustomer(projectEntity.getCustomer());
            project.setGroupId(projectEntity.getGroupId());
            project.setStatus(toStatus(projectEntity.getStatus()));
            project.setStart(toUtilDate(projectEntity.getStart()));
            if (projectEntity.getEnd() != null) {
                project.setEnd(toUtilDate(projectEntity.getEnd()));
            }

            return project;
        }

        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        List<ProjectEntity> projectEntities =
                projectDao.getAllProjects();

        List<Project> projects = new ArrayList<>();

        for (ProjectEntity projectEntity : projectEntities) {
            Project project = new Project();

            project.setId(projectEntity.getId());
            project.setNumber(projectEntity.getNumber());
            project.setName(projectEntity.getName());
            project.setCustomer(projectEntity.getCustomer());
            project.setGroupId(projectEntity.getGroupId());
            project.setStatus(toStatus(projectEntity.getStatus()));
            project.setStart(toUtilDate(projectEntity.getStart()));
            if (projectEntity.getEnd() != null) {
                project.setEnd(toUtilDate(projectEntity.getEnd()));
            }

            projects.add(project);
        }

        return projects;
    }


    private Date toUtilDate(java.sql.Date date) {
        return new Date(date.getTime());
    }


    private java.sql.Date toSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }


    private String toStatus(String status) {
        switch (status) {
            case "NEW":
                return "New";
            case "PLA":
                return "Planned";
            case "INP":
                return "In progress";
            case "FIN":
                return "Finished";
            default:
                return "";
        }
    }


    private String toDaoStatus(String status) {
        switch (status) {
            case "New":
                return "NEW";
            case "Planned":
                return "PLA";
            case "In progress":
                return "INP";
            case "Finished":
                return "FIN";
            default:
                return "";
        }
    }


    private List<List<ProjectEmployeeEntity>> memberDifferences(
            List<ProjectEmployeeEntity> oldList,
            List<ProjectEmployeeEntity> newList) {

        List<ProjectEmployeeEntity> remove = new ArrayList<>(oldList);
        List<ProjectEmployeeEntity> add = new ArrayList<>(newList);

        // removeAll : elements in difference
        // retainAll : elements in commons
        remove.removeAll(newList);
        add.removeAll(oldList);

        List<ProjectEmployeeEntity> removeList = new ArrayList<>();
        List<ProjectEmployeeEntity> addList = new ArrayList<>();

        List<List<ProjectEmployeeEntity>> result = new ArrayList<>();
        result.add(removeList);
        result.add(addList);

        return result;
    }
}
