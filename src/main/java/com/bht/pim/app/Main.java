package com.bht.pim.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/*
* First thing to do for every main class of FX application
* is to extends Application class
* get functionality from the class Application of package javafx
*/
public class Main extends Application {

    /*
    * launch method setting everything up, and then
    * it calls a method names start to run the application
    * We need to @Override it from parent class Application
     */
    public static void main(String[] args) {
        launch(args);
    }


    /*
    * FX terminology ???
    * The main window --> primaryStage
    * The entire window --> stage
    * Content inside window --> scene
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();

        Parent root = FXMLLoader
                .load(Objects.requireNonNull(
                        classLoader.getResource("sample.fxml")));

        Scene scene = new Scene(root, 400, 200);

        /*
        * A layout means how we arrange all the things in the window
        * How we configure it ?
        * Since FX 2.0 we have 2 ways to configure :
        *       one is using java code
        *       the other is using XML using a file format fxml
         */

        primaryStage.setTitle("Project Information Management");
        primaryStage.getIcons().add(
                new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream("pictures/icon.png"))));

        primaryStage.setResizable(true);
        primaryStage.setMinWidth(250);
        primaryStage.setMinHeight(150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
