package com.bht.pim.service;

import com.bht.pim.dto.Employee;

import java.util.List;

public interface EmployeeService {

    // get a specific employee
    Employee getEmployeeById(long id);

    // get all employees
    List<Employee> getAllEmployees();
}
