package com.bht.pim.service;

import com.bht.pim.proto.employees.Employee;
import javafx.collections.ObservableList;

public interface EmployeeService {

    // get a specific employee
    Employee getEmployeeById(long id);

    // Employee List get response from server
    ObservableList<Employee> getAllEmployees();
}
