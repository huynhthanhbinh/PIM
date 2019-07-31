//https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm

package com.bht.pim.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Objects;

public class ComboBoxes extends Application {

    private static final String error = "error";

    private Logger logger = Logger.getLogger(Selection.class);

    private Stage window;

    private boolean found = false;

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
        ImageView icon = new ImageView();

        Label label = new Label();
        label.getStyleClass().add(error);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(options);


        comboBox.getSelectionModel().selectedItemProperty().addListener(observableValue -> {

            if (comboBox.getSelectionModel() != null) {
                String option = comboBox.getSelectionModel().getSelectedItem();

                comboBox.getEditor().getStyleClass().remove(error);

                try {
                    Image image = new Image(Objects.requireNonNull(
                            classLoader.getResourceAsStream(
                                    "pictures/" + option + ".png")),
                            150, 150, true, true);

                    icon.setImage(image);
                    label.setText("");
                    logger.info(option);

                    found = true;

                } catch (NullPointerException exception) {
                    comboBox.getEditor().getStyleClass().add(error);

                    Image image = new Image(Objects.requireNonNull(
                            classLoader.getResourceAsStream(
                                    "pictures/warning.png")),
                            150, 150, true, true);

                    icon.setImage(image);
                    label.setText("Option not found !");
                    logger.warn("Null pointer !");

                    found = false;
                }
            }
        });

        comboBox.setEditable(true);
        comboBox.setValue(options[0]);

        Button bSubmit = new Button("Submit");
        bSubmit.setOnAction(event -> {
            if (found) {
                String option = comboBox.getValue();
                logger.info("Your option is: " + option);
            } else {
                event.consume();
            }
        });

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(comboBox, bSubmit, label);

        HBox layout = new HBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(vbox, icon);

        Scene scene = new Scene(layout, 400, 200);

        scene.getStylesheets().add(Objects.requireNonNull(
                classLoader.getResource("css/form.css")).toExternalForm());

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
