package com.bht.pim.fragment.employee;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;


@Controller
public class EmployeeInfo {

    private Logger logger = Logger.getLogger(EmployeeInfo.class);

    // Get 1 employee info ====================================

//    EmployeeServiceGrpc.EmployeeServiceBlockingStub stub =
//            EmployeeServiceGrpc.newBlockingStub(channel);
//
//    EmployeeId employeeId = EmployeeId.newBuilder()
//            .setId(2)
//            .build();
//
//    EmployeeInfo employee = stub.getEmployeeById(employeeId);
//
//
//    logger.info(employee);
//
//    employee.getEnrolledProjectsList().forEach(project -> logger.info(project.getName()));
}
