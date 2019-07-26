package com.bht.pim.daos;

import com.bht.pim.entities.EmployeeEntity;

import java.util.List;


public interface EmployeeDao {

    // get all employees
    List<EmployeeEntity> getAllEmployees();

    // get an employee
    EmployeeEntity getEmployeeById(long id);
}
