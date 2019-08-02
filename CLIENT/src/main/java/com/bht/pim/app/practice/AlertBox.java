package com.bht.pim.app.practice;

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

import java.util.Objects;

public class AlertBox extends Application {

    private Stage window;


    public static void main(String[] args) {
        launch(args);
    }

    private static void display(String title, String msg) {

        Stage window = new Stage();

        // using this modality to
        // block other user input event on other window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(msg);

        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(event -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);

        // differ from show(), temporarily block processing of current event
        // show this stage, wait for it to be closed
        // before returning to the caller
        // usually use in application with threads -->  Multithreading
        window.showAndWait();
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
        button.setOnAction(event ->
                AlertBox.display("Notice Window", "This is an alert box !"));


        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 100);
        window.setScene(scene);
        window.show();
    }
}
