package com.bht.pim.app.practice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Objects;

public class ChoiceBoxes extends Application {

    private Logger logger = Logger.getLogger(Selection.class);

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

        String[] options = {"Java", ".NET", "Android", "iOS"};

        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        choiceBox.getItems().addAll(options);

        Button bSubmit = new Button("Submit");
        bSubmit.setOnAction(event -> {
            String option = choiceBox.getValue();
            logger.info("Your option is: " + option);
        });

        ImageView icon = new ImageView();

        choiceBox.getSelectionModel()
                .selectedItemProperty().addListener(observableValue -> {

            if (choiceBox.getSelectionModel() != null) {
                String option = choiceBox.getSelectionModel().getSelectedItem();

                Image image = new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream(
                                "pictures/" + option + ".png")),
                        150, 150, true, true);

                icon.setImage(image);
                logger.info(option);
            }
        });

        choiceBox.setValue(options[0]);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(choiceBox, bSubmit);

        HBox layout = new HBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(vbox, icon);

        Scene scene = new Scene(layout, 400, 200);
        window.setScene(scene);
        showWindow(window);
    }

    private void showWindow(Stage window) {
        window.setResizable(false);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
}
