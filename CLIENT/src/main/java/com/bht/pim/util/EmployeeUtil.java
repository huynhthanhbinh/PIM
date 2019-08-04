package com.bht.pim.util;

import com.bht.pim.proto.employee.Employee;
import com.bht.pim.proto.employee.EmployeeList;
import com.bht.pim.proto.employee.EmployeeListServiceGrpc;
import com.bht.pim.proto.employee.NoParam;
import io.grpc.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

public class EmployeeUtil {

    private static Logger logger = Logger.getLogger(EmployeeUtil.class);

    private EmployeeUtil() {
    }

    // Employee List get response from server
    public static ObservableList<Employee> getEmployeeList(Channel channel) {

        // Get employee list =======================================
        EmployeeListServiceGrpc.EmployeeListServiceBlockingStub stub =
                EmployeeListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        EmployeeList employeeList = stub.getEmployeeList(noParam);

        return FXCollections.observableArrayList(employeeList.getEmployeeListList());
    }
}
