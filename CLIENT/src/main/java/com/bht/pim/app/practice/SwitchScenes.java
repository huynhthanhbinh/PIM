/*
 * @Author  : BHT
 * @Company : ELCA VN
 */

package com.bht.pim.app.practice;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class SwitchScenes extends Application {

    private Stage window;
    private Scene scene1;
    private Scene scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ClassLoader classLoader = getClass().getClassLoader();

        // Assign the main window to window variable
        window = primaryStage;

        Label label1 = new Label("This is the 1st scene");
        Label label2 = new Label("This is the 2nd scene");

        Button button1 = new Button("Go to 2nd scene");
        Button button2 = new Button("Go to 1st scene");

        button1.setOnAction(event -> window.setScene(scene2));
        button2.setOnAction(event -> window.setScene(scene1));

        VBox layout1 = new VBox(20);
        HBox layout2 = new HBox(20);

        layout1.getChildren().addAll(label1, button1);
        layout2.getChildren().addAll(label2, button2);

        layout1.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);

        scene1 = new Scene(layout1, 200, 400);
        scene2 = new Scene(layout2, 400, 200);

        window.setScene(scene1);

        window.setTitle("Project Information Management");
        window.getIcons().add(
                new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream("pictures/icon.png"))));
        window.show();
    }
}
