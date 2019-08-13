package com.bht.pim.service.impl;

import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.service.EmployeeService;
import com.google.protobuf.Int64Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub stub;

    // get a specific employee
    @Override
    public EmployeeInfo getEmployeeById(long id) {
        return stub.getEmployeeById(Int64Value.newBuilder()
                .setValue(id)
                .build());
    }
}
