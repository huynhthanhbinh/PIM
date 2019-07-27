package com.bht.pim.dao;

import com.bht.pim.entity.EmployeeEntity;

import java.util.List;


public interface EmployeeDao {

    // get all employees
    List<EmployeeEntity> getAllEmployees();

    // get an employee
    EmployeeEntity getEmployeeById(long id);
}
