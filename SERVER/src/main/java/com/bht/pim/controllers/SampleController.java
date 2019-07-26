package com.bht.pim.controllers;

// lib for using label in FX

import com.bht.pim.services.EmployeeService;
import javafx.scene.control.Label;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SampleController {

    private Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    EmployeeService employeeService;

    public Label helloWorld;

    public void sayHelloWorld(/*ActionEvent actionEvent*/) {
        helloWorld.setText(" Hello World ");

        employeeService.getAllEmployees().forEach(logger::info);
    }
}
