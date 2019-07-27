package com.bht.pim.controller;

// lib for using label in FX

import com.bht.pim.service.EmployeeService;
import com.bht.pim.service.GroupService;
import com.bht.pim.service.ProjectService;
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
    }
}
