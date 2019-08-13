package com.bht.pim.service;

import com.bht.pim.proto.employees.Employee;
import javafx.collections.ObservableList;

public interface EmployeeListService {

    // Employee List get response from server
    ObservableList<Employee> getAllEmployees();
}
