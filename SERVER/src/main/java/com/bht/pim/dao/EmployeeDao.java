package com.bht.pim.dao;

import com.bht.pim.entity.EmployeeEntity;

import java.util.List;


public interface EmployeeDao {

    // get all employees
    List<EmployeeEntity> getEmployeeList(int maxRow, int pageIndex);

    // get an employee
    EmployeeEntity getEmployeeById(long id);

    // Get project row count(*)
    long getNumberOfEmployees();
}
