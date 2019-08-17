package com.bht.pim.service;

import com.bht.pim.dto.EmployeeDto;
import javafx.collections.ObservableList;

public interface EmployeeService {

    // get a specific employee
    EmployeeDto getEmployeeById(long id);

    // Employee List get response from server
    ObservableList<EmployeeDto> getAllEmployees();
}
