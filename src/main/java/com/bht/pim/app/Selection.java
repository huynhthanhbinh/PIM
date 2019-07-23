package com.bht.pim.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Selection extends Application {

    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();

        window = primaryStage;

        window.setTitle("Project Information Management");
        window.getIcons().add(
                new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream("pictures/icon.png"))));

        String[] roles = {"Developer", "Tester", "Manager"};
        String[] options = {"Java", ".NET", "Android", "iOS"};

        List<CheckBox> checkBoxes = new ArrayList<>();
        List<RadioButton> radioButtons = new ArrayList<>();

        ToggleGroup groupOptions = new ToggleGroup(); // to group all radio buttons


        for (String role : roles) {
            CheckBox checkBox = new CheckBox(role);

            if (role.equals(roles[0])) {
                checkBox.setSelected(true);
            }

            checkBoxes.add(checkBox);
        }

        for (String option : options) {
            RadioButton radioButton = new RadioButton(option);
            radioButton.setToggleGroup(groupOptions);

            if (option.equals(options[0])) {
                radioButton.setSelected(true);
            }

            radioButtons.add(radioButton);
        }

        Button bSubmit = new Button("Submit");

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));

        VBox layout1 = new VBox();
        layout1.setSpacing(5);
        checkBoxes.forEach(
                checkBox -> layout1.getChildren().add(checkBox));


        VBox layout2 = new VBox();
        layout2.setSpacing(5);
        radioButtons.forEach(
                radioButton -> layout2.getChildren().add(radioButton));

        layout.setLeft(layout1);
        layout.setRight(layout2);
        layout.setBottom(bSubmit);

        Scene scene = new Scene(layout, 370, 150);

        window.setResizable(false);
        window.setScene(scene);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
}
