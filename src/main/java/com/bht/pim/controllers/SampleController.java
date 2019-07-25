package com.bht.pim.controllers;

// lib for using label in FX

import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class SampleController {
    public Label helloWorld;

    public void sayHelloWorld(/*ActionEvent actionEvent*/) {
        helloWorld.setText(" Hello World ");
    }
}
