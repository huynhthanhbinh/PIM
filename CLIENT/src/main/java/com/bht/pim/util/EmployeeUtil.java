package com.bht.pim.util;

import com.bht.pim.dto.employees.Employee;
import com.bht.pim.dto.employees.EmployeeList;
import com.bht.pim.dto.employees.EmployeeListServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

public class EmployeeUtil {

    private static Logger logger = Logger.getLogger(EmployeeUtil.class);

    private EmployeeUtil() {
    }

    // Employee List get response from server
    public static ObservableList<Employee> getAllEmployees(Channel channel) {

        // Get employee list =======================================
        EmployeeListServiceGrpc.EmployeeListServiceBlockingStub stub =
                EmployeeListServiceGrpc.newBlockingStub(channel);

        EmployeeList employeeList = stub.getEmployeeList(Empty.getDefaultInstance());

        return FXCollections.observableArrayList(employeeList.getEmployeesList());
    }
}
