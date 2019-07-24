package com.bht.pim.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;


// List View can select multiple items
public class ListViews extends Application {

    private Logger logger = Logger.getLogger(ListViews.class);

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

        String[] options = {"Java", ".NET", "Android", "iOS"};

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(options);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button bSubmit = new Button("Submit");
        bSubmit.setOnAction(event -> {
            List<String> choices = listView.getSelectionModel().getSelectedItems();
            choices.forEach(logger::info);
        });

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        VBox layout = new VBox();

        layout.setPadding(new Insets(20));

        vBox.getChildren().addAll(listView);
        layout.getChildren().addAll(vBox, hBox, bSubmit);

        Scene scene = new Scene(layout, 400, 400);

        scene.getStylesheets().add(Objects.requireNonNull(
                classLoader.getResource("form.css")).toExternalForm());

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
