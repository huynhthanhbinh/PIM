package com.bht.pim.app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Objects;

// This demo is aim to demonstrate
// How windows communicating with the others
// For eg. let we get the confirm of user

public class ConfirmBox extends Application {

    private static boolean confirm;
    private Logger logger = Logger.getLogger(ConfirmBox.class);
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    private static boolean display(String title, String msg) {

        Stage window = new Stage();

        // using this modality to
        // block other user input event on other window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(msg);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(event -> {
            confirm = true;
            window.close();
        });
        noButton.setOnAction(event -> {
            confirm = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);

        // differ from show(), temporarily block processing of current event
        // show this stage, wait for it to be closed
        // before returning to the caller
        // usually use in application with threads -->  Multithreading
        window.showAndWait();

        return confirm;
    }

    @Override
    public void start(Stage primaryStage) {

        ClassLoader classLoader = getClass().getClassLoader();

        window = primaryStage;

        window.setTitle("Project Information Management");
        window.getIcons().add(
                new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream("pictures/icon.png"))));


        Button button = new Button("Click Me");
        button.setOnAction(event -> {
            boolean result = ConfirmBox.display("Confirm Window", "Do you want to ... ?");
            logger.info("Result is : " + result);
        });


        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 100);
        window.setScene(scene);
        window.show();
    }
}
