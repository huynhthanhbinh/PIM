package com.bht.pim.app.practice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Objects;

public class LoginForm extends Application {

    private static final String error = "error";

    private Logger logger = Logger.getLogger(LoginForm.class);

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
        grid.setAlignment(Pos.CENTER);

        // Same size as window
        // So first, let's set the padding (from the window border)
        grid.setPadding(new Insets(
                20, 20, 20, 20));

        // Then we need to set the the
        // HGap, VGap ~ cellPadding, cellSpacing of each node inside
        // Remind H-Horizontal~Row, V-Vertical~Column
        grid.setHgap(10); // spacing horizontally
        grid.setVgap(8);  // spacing vertically

        Label lUsername = new Label("Username");
        Label lPassword = new Label("Password");

        TextField iUsername = new TextField(); // username input
        PasswordField iPassword = new PasswordField(); // password input

        // Run the program to see the difference between Text & PromptText
        // PromptText ~ PlaceHolder in HTML input type = "text"
        iUsername.setText("Richard");
        iPassword.setPromptText("Your password");

        GridPane.setConstraints(lUsername, 0, 0);
        GridPane.setConstraints(iUsername, 1, 0);
        GridPane.setConstraints(lPassword, 0, 1);
        GridPane.setConstraints(iPassword, 1, 1);

        Button bLogin = new Button("Log in");
        Button bForgot = new Button("Forgot account");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(bLogin, bForgot);
        hBox.setSpacing(5);

        Label label = new Label();
        GridPane.setConstraints(label, 1, 3);
        grid.getChildren().add(label);
        label.getStyleClass().add(error);

        bLogin.setOnAction(event -> {
            logger.info("Username input: " + iUsername.getText());
            logger.info("Password input: " + iPassword.getText());

            // If put remove(classError) at ELSE clause
            // it will catch a bug
            // when user input incorrect continuous several times
            // class classError will be add to styleClass several times
            // therefore, if user input correctly the following try
            // it just remove 1 class classError
            // so the css style of classError will be on the field !
            iPassword.getStyleClass().remove(error);

            if (!isNumber(iPassword.getText())) {
                iPassword.getStyleClass().add(error);
                logger.warn("Catch exception");

                logger.info(iPassword.getStyleClass());
                label.setText("Password is not in number format !");

            } else {
                logger.info(iPassword.getStyleClass());
                label.setText("");
            }
        });

        GridPane.setConstraints(hBox, 1, 2);

        grid.getChildren().addAll(
                lUsername, iUsername,
                lPassword, iPassword,
                hBox
        );

        Scene scene = new Scene(grid, 400, 150);

        scene.getStylesheets().add(Objects.requireNonNull(
                classLoader.getResource("junks/form.css")).toExternalForm());

        window.setMinWidth(370);
        window.setMinHeight(190);
        window.setScene(scene);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    private boolean isNumber(String msg) {
        try {
            int number = Integer.parseInt(msg);
            logger.info("Password is a number !, value is: " + number);
            return true;

        } catch (NumberFormatException e) {
            logger.warn("Password is not a number, value is: " + msg);
            return false;
        }
    }
}
