package com.bht.pim.service.impl;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.mapper.EmployeeMapper;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.service.EmployeeService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    private ObjectProperty<EmployeeServiceGrpc.EmployeeServiceBlockingStub> stubProperty
            = AppConfiguration.EMPLOYEE_SERVICE_STUB_PROPERTY;

    // get a specific employee
    @Override
    public EmployeeDto getEmployeeById(long id) {
        return employeeMapper.toEmployeeDto(stubProperty.get()
                .getEmployeeById(Int64Value.newBuilder()
                        .setValue(id)
                        .build()));
    }

    // Employee List get response from server
    @Override
    public ObservableList<EmployeeDto> getAllEmployees() {
        return FXCollections.observableArrayList(
                employeeMapper.toEmployeeDtoList(stubProperty.get()
                        .getEmployeeList(Empty.getDefaultInstance())
                        .getEmployeesList()));
    }
}

