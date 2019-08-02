package com.bht.pim.util;

import com.bht.pim.proto.employee.Employee;
import com.bht.pim.proto.employee.EmployeeList;
import com.bht.pim.proto.employee.EmployeeListServiceGrpc;
import com.bht.pim.proto.employee.NoParam;
import io.grpc.Channel;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeUtil {

    private static Logger logger = Logger.getLogger(EmployeeUtil.class);

    private EmployeeUtil() {
    }

    // Employee List get response from server
    public static List<String> getEmployeeList(Channel channel) {

        // Get employee list =======================================
        EmployeeListServiceGrpc.EmployeeListServiceBlockingStub stub =
                EmployeeListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        EmployeeList employeeList = stub.getEmployeeList(noParam);

        return employeeList.getEmployeeListList().stream()
                .map(EmployeeUtil::toEmployeeInfo)
                .collect(Collectors.toList());
    }

    // mapping employee to employee info
    private static String toEmployeeInfo(Employee employee) {
        return "id=" + employee.getId() + " | " + employee.getVisa() + " - "
                + employee.getLastName() + " " + employee.getFirstName();
    }
}
