package com.bht.pim.service;

import com.bht.pim.base.BaseBean;
import com.bht.pim.dto.EmployeeDto;

import javafx.collections.ObservableList;

/**
 * @author bht
 */
public interface EmployeeService extends BaseBean {

    // get a specific employee
    EmployeeDto getEmployeeById(long id);

    // Get project row count(*)
    long getNumberOfEmployees();

    // Employee List get response from server
    ObservableList<EmployeeDto> getEmployeeList(int maxRow, int pageIndex);
}
