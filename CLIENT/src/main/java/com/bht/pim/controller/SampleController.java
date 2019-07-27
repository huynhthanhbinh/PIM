package com.bht.pim.controller;

import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class SampleController {

    public Label helloWorld;

    public void sayHelloWorld(/*ActionEvent actionEvent*/) {
        helloWorld.setText(" Hello World ");
    }
}
