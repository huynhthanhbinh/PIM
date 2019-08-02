package com.bht.pim.app.practice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class EmbeddedLayouts extends Application {

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

        // 1st layout
        HBox topMenu = new HBox();

        Image image = new Image(Objects.requireNonNull(
                classLoader.getResourceAsStream("pictures/logo.png")),
                75, 75, true, true);

        // Image is not a node, so we need to assign to ImageView
        ImageView logo = new ImageView(image);

        Label label = new Label("Project Information Management");

        topMenu.getChildren().addAll(logo, label);

        // 2nd layout
        VBox leftMenu = new VBox();

        Label lProjectList = new Label("PROJECT LIST");
        Label lNew = new Label("New");
        Label lProject = new Label("Project");
        Label lCustomer = new Label("Customer");
        Label lSupplier = new Label("Supplier");

        leftMenu.getChildren().addAll(
                lProjectList, lNew,
                lProject, lCustomer, lSupplier
        );

        // embedded 2 layouts into a borderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);

        Scene scene = new Scene(borderPane, 400, 500);
        window.setScene(scene);
        window.show();
    }
}
