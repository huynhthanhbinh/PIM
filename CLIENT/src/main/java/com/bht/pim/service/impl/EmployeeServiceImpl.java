package com.bht.pim.service.impl;

import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.service.EmployeeService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub stub;

    // get a specific employee
    @Override
    public Employee getEmployeeById(long id) {
        return stub.getEmployeeById(Int64Value.newBuilder()
                .setValue(id)
                .build());
    }

    // Employee List get response from server
    @Override
    public ObservableList<Employee> getAllEmployees() {
        return FXCollections.observableArrayList(stub
                .getEmployeeList(Empty.getDefaultInstance())
                .getEmployeesList());
    }
}

