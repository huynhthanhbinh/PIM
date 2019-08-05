package com.bht.pim.util;

import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeList;
import com.bht.pim.proto.employees.EmployeeListServiceGrpc;
import com.bht.pim.proto.employees.NoParam;
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

        NoParam noParam = NoParam.newBuilder().build();

        EmployeeList employeeList = stub.getEmployeeList(noParam);

        logger.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        logger.info(employeeList);
        logger.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        return FXCollections.observableArrayList(employeeList.getEmployeesList());
    }
}
