package com.bht.pim.services.impl;

import com.bht.pim.daos.EmployeeDao;
import com.bht.pim.entities.EmployeeEntity;
import com.bht.pim.models.Employee;
import com.bht.pim.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

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
