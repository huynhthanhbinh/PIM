package com.bht.pim.service.impl;

import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeListServiceGrpc;
import com.bht.pim.service.EmployeeListService;
import com.google.protobuf.Empty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeListServiceImpl implements EmployeeListService {

    @Autowired
    private EmployeeListServiceGrpc.EmployeeListServiceBlockingStub stub;

    // Employee List get response from server
    @Override
    public ObservableList<Employee> getAllEmployees() {
        return FXCollections.observableArrayList(stub
                .getEmployeeList(Empty.getDefaultInstance())
                .getEmployeesList());
    }
}
