package com.bht.pim.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Objects;

/*
* First thing to do for every main class of FX application
* is to extends Application class
* get functionality from the class Application of package javafx
*/
public class Main extends Application implements EventHandler<ActionEvent> {

    private Logger logger = Logger.getLogger(Main.class);
    private Button myButton;

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

        /*Parent root = FXMLLoader
                .load(Objects.requireNonNull(
                        classLoader.getResource("sample.fxml")));*/

        myButton = new Button();
        myButton.setText("Say 'Hello World'");
        myButton.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(myButton);

        Scene scene = new Scene(layout, 400, 200);
        scene.getStylesheets().add(Objects.requireNonNull(
                classLoader.getResource("sample.css")).toExternalForm());

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


    /*
    It will be called whenever user interact with the UI
        + click a myButton --> onClick
        + ...
    */
    @Override
    public void handle(ActionEvent event) {

        if (event.getSource().equals(myButton)) {
            logger.info("Button say Hello World is clicked !");
        }
    }
}
