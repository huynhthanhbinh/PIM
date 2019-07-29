package com.bht.pim.controller;

// lib for using label in FX

import com.bht.pim.proto.employee.EmployeeId;
import com.bht.pim.proto.employee.EmployeeInfo;
import com.bht.pim.proto.employee.EmployeeServiceGrpc;
import com.bht.pim.service.EmployeeService;
import com.bht.pim.service.GroupService;
import com.bht.pim.service.ProjectService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SampleController {

    private Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    GroupService groupService;

    @Autowired
    ProjectService projectService;

    @FXML
    Label helloWorld;

    public void sayHelloWorld(/*ActionEvent actionEvent*/) {

        helloWorld.setText(" Hello World ");

        employeeService.getAllEmployees().forEach(logger::info);
        groupService.getAllGroups().forEach(logger::info);
        projectService.getAllProjects().forEach(logger::info);

        projectService.getProjectById(4).printInfo();
        groupService.getGroupById(1).printInfo();
        employeeService.getEmployeeById(2).printInfo();

        logger.info("All projects number exist: ");
        projectService.getAllProjectsNumber().forEach(logger::info);

        final ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9999)
                .usePlaintext()
                .build();

        EmployeeServiceGrpc.EmployeeServiceBlockingStub stub =
                EmployeeServiceGrpc.newBlockingStub(channel);

        //EmployeeInfo employeeInfo = EmployeeInfo.getDefaultInstance();

        EmployeeId employeeId = EmployeeId.newBuilder()
                .setId(2)
                .build();

        EmployeeInfo employee = stub.getEmployeeById(employeeId);

        logger.info(employee);
    }
}
