package com.bht.pim.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ClassLoader classLoader  = getClass().getClassLoader();

        Parent root = FXMLLoader
                .load(Objects.requireNonNull(
                        classLoader.getResource("sample.fxml")));

        Scene scene = new Scene(root, 500, 250);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
