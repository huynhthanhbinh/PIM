package com.bht.pim.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Objects;

public class GridPanes extends Application {

    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ClassLoader classLoader = getClass().getClassLoader();

        window = primaryStage;

        window.setTitle("Project Information Management");
        window.getIcons().add(
                new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream("pictures/icon.png"))));

        GridPane grid = new GridPane();

        // Same size as window
        // So first, let's set the padding (from the window border)
        grid.setPadding(new Insets(
                10, 10, 10, 10));

        // Then we need to set the the
        // HGap, VGap ~ cellPadding, cellSpacing of each node inside
        // Remind H-Horizontal~Row, V-Vertical~Column
        grid.setHgap(10); // spacing horizontally
        grid.setVgap(8);  // spacing vertically

        Label lUsername = new Label("Username");
        Label lPassword = new Label("Password");

        TextField iUsername = new TextField(); // username input
        TextField iPassword = new TextField(); // password input

        // Run the program to see the difference between Text & PromptText
        // PromptText ~ PlaceHolder in HTML input type = "text"
        iUsername.setText("Richard");
        iPassword.setPromptText("123456789");

        GridPane.setConstraints(lUsername, 0, 0);
        GridPane.setConstraints(iUsername, 1, 0);
        GridPane.setConstraints(lPassword, 0, 1);
        GridPane.setConstraints(iPassword, 1, 1);

        Button bLogin = new Button("Log in");
        Button bForgot = new Button("Forgot account");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(bLogin, bForgot);
        hBox.setSpacing(5);

        GridPane.setConstraints(hBox, 1, 2);

        grid.getChildren().addAll(
                lUsername, iUsername,
                lPassword, iPassword,
                hBox
        );

        Scene scene = new Scene(grid, 350, 150);
        window.setScene(scene);
        window.show();
    }
}
