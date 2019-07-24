package com.bht.pim.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


// List View can select multiple items
public class ListViews extends Application {

    private Logger logger = Logger.getLogger(ListViews.class);

    private Stage window;
    private List<String> before;
    private List<String> after;

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

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        VBox layout = new VBox();

        List<String> options = Arrays.asList("Java", ".NET", "Android", "iOS");
        List<ImageView> imageList = new ArrayList<>();

        for (String s : options) {
            Image image = new Image(Objects.requireNonNull(
                    classLoader.getResourceAsStream(
                            "pictures/" + s + ".png")),
                    90, 90, true, true);

            ImageView imageView = new ImageView(image);
            imageView.setVisible(false);
            imageList.add(imageView);
        }

        hBox.getChildren().addAll(imageList);

        ListView<String> listView = new ListView<>();

        listView.getItems().addAll(options);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listView.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldOption, newOption) -> {
                    //logger.info(newOption);
                });

        before = new ArrayList<>();

        listView.setOnMouseClicked(event -> {
            after = listView.getSelectionModel().getSelectedItems();

            logger.info("Before: " + before);
            logger.info("After : " + after);

            difference(before, after).forEach(dif -> {
                if (before.contains(dif)) {
                    logger.info("Remove: " + dif);

                    for (int i = 0; i < options.size(); i++) {
                        if (dif.equals(options.get(i))) {
                            imageList.get(i).setVisible(false);
                        }
                    }

                } else {
                    logger.info("Add   : " + dif);

                    for (int i = 0; i < options.size(); i++) {
                        if (dif.equals(options.get(i))) {
                            imageList.get(i).setVisible(true);
                        }
                    }
                }
            });

            before = new ArrayList<>(after);
            logger.info("");
        });

        listView.setOnKeyReleased(event -> {
            after = listView.getSelectionModel().getSelectedItems();

            logger.info("Before: " + before);
            logger.info("After : " + after);

            difference(before, after).forEach(dif -> {
                if (before.contains(dif)) {
                    logger.info("Remove: " + dif);

                    for (int i = 0; i < options.size(); i++) {
                        if (dif.equals(options.get(i))) {
                            imageList.get(i).setVisible(false);
                        }
                    }

                } else {
                    logger.info("Add   : " + dif);

                    for (int i = 0; i < options.size(); i++) {
                        if (dif.equals(options.get(i))) {
                            imageList.get(i).setVisible(true);
                        }
                    }
                }
            });

            before = new ArrayList<>(after);
            logger.info("");
        });

        Button bSubmit = new Button("Submit");
        bSubmit.setOnAction(event -> {
            List<String> choices = listView.getSelectionModel().getSelectedItems();
            logger.info("Selected options :");
            choices.forEach(logger::info);
        });

        layout.setPadding(new Insets(20));
        layout.setSpacing(10);

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

    private List<String> difference(
            List<String> before, List<String> after) {

        List<String> list1 = new ArrayList<>(before);
        List<String> list2 = new ArrayList<>(after);

        list1.removeAll(after);
        list2.removeAll(before);
        list1.addAll(list2);

        return list1;
    }
}
