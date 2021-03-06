package com.bht.pim.service.impl;

import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.mapper.EmployeeMapper;
import com.bht.pim.proto.employees.EmployeePagination;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.service.EmployeeService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bht
 */
@Service
public final class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub stub;

    // get a specific employee
    @Override
    public EmployeeDto getEmployeeById(long id) {
        return employeeMapper.toEmployeeDto(stub.
                getEmployeeById(Int64Value.newBuilder()
                        .setValue(id)
                        .build()));
    }

    @Override
    public long getNumberOfEmployees() {
        return stub
                .getNumberOfEmployees(Empty.getDefaultInstance())
                .getValue();
    }

    // Employee List get response from server
    @Override
    public ObservableList<EmployeeDto> getEmployeeList(int maxRow, int pageIndex) {
        return FXCollections.observableArrayList(
                employeeMapper.toEmployeeDtoList(stub
                        .getEmployeeList(EmployeePagination.newBuilder()
                                .setMaxRow(maxRow)
                                .setPageIndex(pageIndex)
                                .build())
                        .getEmployeesList()));
    }
}

