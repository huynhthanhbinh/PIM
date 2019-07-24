package com.bht.pim.app;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TreeViews extends Application {

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

        List<String> developers = Arrays.asList("Java", ".NET", "Android", "iOS");
        List<String> roles = Arrays.asList("Tester", "Manager", "Business Analyst");

        TreeItem<String> root;
        TreeItem<String> developer;
        TreeItem<String> other;

        // Root
        root = new TreeItem<>("ELCA");
        root.setExpanded(true);


        // Developer
        developer = makeBranch("Developer", root);
        developers.forEach(dev -> makeBranch(dev, developer));

        // Other
        other = makeBranch("Other roles", root);
        roles.forEach(role -> makeBranch(role, other));


        // Create Tree
        TreeView<String> tree = new TreeView<>(root);

        // show/hide the root, just show its children
        tree.setShowRoot(true);

        // get selected value
        tree.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.info("Currently select: " +
                                newValue.getValue());
                    }
                });

        StackPane layout = new StackPane();
        layout.getChildren().add(tree);

        Scene scene = new Scene(layout, 350, 250);

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

    private TreeItem<String> makeBranch(
            String branchName, TreeItem<String> parent) {

        TreeItem<String> item = new TreeItem<>(branchName);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
