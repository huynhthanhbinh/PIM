package com.bht.pim.service.impl;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dto.Employee;
import com.bht.pim.dto.Project;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.service.EmployeeService;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@GRpcService
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public Employee getEmployeeById(long id) {
        EmployeeEntity employeeEntity =
                employeeDao.getEmployeeById(id);

        if (employeeEntity != null) {
            Employee employee = new Employee();

            employee.setId(employeeEntity.getId());
            employee.setVisa(employeeEntity.getVisa());
            employee.setLastName(employeeEntity.getLastName());
            employee.setFirstName(employeeEntity.getFirstName());
            employee.setBirthday(toUtilDate(employeeEntity.getBirthday()));

            Set<Project> projects = new HashSet<>();

            employeeEntity.getEnrolledProjects().forEach(projectEntity -> {
                Project project = new Project();

                project.setId(projectEntity.getId());
                project.setNumber(projectEntity.getNumber());
                project.setName(projectEntity.getName());
                project.setCustomer(projectEntity.getCustomer());

                projects.add(project);
            });

            employee.setEnrolledProjects(projects);

            return employee;
        }

        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<EmployeeEntity> employeeEntities =
                employeeDao.getAllEmployees();

        List<Employee> employees = new ArrayList<>();

        employeeEntities.forEach(employeeEntity -> {
            Employee employee = new Employee();

            employee.setId(employeeEntity.getId());
            employee.setVisa(employeeEntity.getVisa());
            employee.setFirstName(employeeEntity.getFirstName());
            employee.setLastName(employeeEntity.getLastName());
            employee.setBirthday(toUtilDate(employeeEntity.getBirthday()));

            employees.add(employee);
        });

        return employees;
    }

    private Date toUtilDate(java.sql.Date date) {
        return new Date(date.getTime());
    }
}
