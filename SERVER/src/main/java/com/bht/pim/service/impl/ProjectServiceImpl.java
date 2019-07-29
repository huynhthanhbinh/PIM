package com.bht.pim.service.impl;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.GroupDao;
import com.bht.pim.dao.ProjectDao;
import com.bht.pim.dto.Employee;
import com.bht.pim.dto.Project;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.service.ProjectService;
import org.apache.log4j.Logger;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@GRpcService
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
    EmployeeDao employeeDao;


    @Override
    public List<Long> getAllProjectsNumber() {
        return projectDao.getAllProjectsNumber();
    }

    @Override
    public boolean addProject(Project project) {
        try {
            Set<EmployeeEntity> projectEmployees = new HashSet<>();
            project.getMembers().forEach(member ->
                    projectEmployees.add(employeeDao
                            .getEmployeeById(member.getId())));


            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setGroup(groupDao.getGroupById(project.getId()));
            projectEntity.setNumber(project.getNumber());
            projectEntity.setName(project.getName());
            projectEntity.setCustomer(project.getCustomer());
            projectEntity.setStatus("NEW");
            projectEntity.setEnrolledEmployees(projectEmployees);
            projectEntity.setStart(toSqlDate(project.getStart()));
            if (project.getEnd() != null) {
                projectEntity.setEnd(toSqlDate(project.getEnd()));
            }

            return projectDao.addProject(projectEntity);

        } catch (Exception exception) {

            logger.info(exception);
            return false;
        }
    }

    @Override
    public boolean addProject(Project project, long groupLeaderId) {
        try {
            Set<EmployeeEntity> projectEmployees = new HashSet<>();
            project.getMembers().forEach(member ->
                    projectEmployees.add(employeeDao
                            .getEmployeeById(member.getId())));

            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setGroupLeader(
                    employeeDao.getEmployeeById(groupLeaderId));
            groupDao.addGroup(groupEntity);

            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setGroup(groupDao
                    .getGroupById(groupDao.nextIdValue() - 1));
            projectEntity.setNumber(project.getNumber());
            projectEntity.setName(project.getName());
            projectEntity.setCustomer(project.getCustomer());
            projectEntity.setStatus("NEW");
            projectEntity.setEnrolledEmployees(projectEmployees);
            projectEntity.setStart(toSqlDate(project.getStart()));
            if (project.getEnd() != null) {
                projectEntity.setEnd(toSqlDate(project.getEnd()));
            }

            return projectDao.addProject(projectEntity);

        } catch (Exception exception) {

            logger.info(exception);
            return false;
        }
    }

    @Override
    public boolean updateProject(Project project) {
        try {
            ProjectEntity projectEntity =
                    projectDao.getProjectById(project.getId());

            if (projectEntity != null) {

                Set<EmployeeEntity> projectEmployees = new HashSet<>();
                project.getMembers().forEach(member ->
                        projectEmployees.add(employeeDao
                                .getEmployeeById(member.getId())));

                projectEntity.setGroup(
                        groupDao.getGroupById(project.getGroupId()));
                projectEntity.setNumber(project.getNumber());
                projectEntity.setName(project.getName());
                projectEntity.setCustomer(project.getCustomer());
                projectEntity.setStatus(toDaoStatus(project.getStatus()));
                projectEntity.setEnrolledEmployees(projectEmployees);
                projectEntity.setStart(toSqlDate(project.getStart()));
                if (project.getEnd() != null) {
                    projectEntity.setEnd(toSqlDate(project.getEnd()));
                }

                return projectDao.updateProject(projectEntity);
            }
            return false;

        } catch (Exception exception) {

            logger.info(exception);
            return false;
        }
    }

    @Override
    public boolean deleteProject(long id) {
        try {
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

        } catch (Exception exception) {

            logger.info(exception);
            return false;
        }
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
            project.setGroupId(projectEntity.getGroup().getId());
            project.setStatus(toStatus(projectEntity.getStatus()));
            project.setStart(toUtilDate(projectEntity.getStart()));
            if (projectEntity.getEnd() != null) {
                project.setEnd(toUtilDate(projectEntity.getEnd()));
            }

            EmployeeEntity groupLeader = projectEntity
                    .getGroup()
                    .getGroupLeader();

            project.setGroupLeaderId(groupLeader.getId());
            project.setGroupLeaderName(
                    groupLeader.getVisa() + " - " +
                            groupLeader.getLastName() + " " +
                            groupLeader.getFirstName());

            Set<Employee> employees = new HashSet<>();

            projectEntity.getEnrolledEmployees().forEach(employeeEntity -> {
                Employee employee = new Employee();

                employee.setId(employeeEntity.getId());
                employee.setVisa(employeeEntity.getVisa());
                employee.setFirstName(employeeEntity.getFirstName());
                employee.setLastName(employeeEntity.getLastName());

                employees.add(employee);
            });

            project.setMembers(employees);

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
            project.setGroupId(projectEntity.getGroup().getId());
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


    private Set<Set<EmployeeEntity>> memberDifferences(
            Set<EmployeeEntity> oldList, Set<EmployeeEntity> newList) {

        Set<EmployeeEntity> removeList = new HashSet<>(oldList);
        Set<EmployeeEntity> addList = new HashSet<>(newList);

        // removeAll : elements in difference
        // retainAll : elements in commons
        removeList.removeAll(newList);
        addList.removeAll(oldList);

        Set<Set<EmployeeEntity>> result = new HashSet<>();
        result.add(removeList); // get element 0
        result.add(addList);    // get element 1

        logger.info("Remove List");
        removeList.forEach(logger::info);
        logger.info("Add List");
        addList.forEach(logger::info);

        return result;
    }
}