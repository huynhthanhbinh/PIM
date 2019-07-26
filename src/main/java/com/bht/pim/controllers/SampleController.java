package com.bht.pim.controllers;

// lib for using label in FX

import com.bht.pim.services.EmployeeService;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SampleController {

    @Autowired
    EmployeeService employeeService;

    public Label helloWorld;

    public void sayHelloWorld(/*ActionEvent actionEvent*/) {
        helloWorld.setText(" Hello World ");

        employeeService.getAllEmployees();
    }
}
