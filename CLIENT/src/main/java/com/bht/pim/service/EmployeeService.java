package com.bht.pim.service;

import com.bht.pim.proto.employees.EmployeeInfo;

public interface EmployeeService {

    // get a specific employee
    EmployeeInfo getEmployeeById(long id);
}
