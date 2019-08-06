package com.bht.pim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.Objects;

@Log4j
public class Client extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    private static void showWindow(Stage window) throws IOException {

        primaryStage = window;

        ClassLoader classLoader = Client.class.getClassLoader();

        window.setTitle("Project Information Management");
        window.getIcons().add(
                new Image(Objects.requireNonNull(classLoader
                        .getResourceAsStream("pictures/icon.png"))));

        FXMLLoader fxmlLoader = new FXMLLoader(Client.class
                .getResource("fragment/project/ProjectList.fxml"));

        Parent rootNode = fxmlLoader.load();
        Scene scene = new Scene(rootNode, 1100, 600);

        window.setMinWidth(1120);
        window.setMinHeight(630);
        window.setResizable(true);
        window.setScene(scene);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }

    @Override
    public void init() throws Exception {
        log.info("<<< PIM CLIENT - ON INIT  >>>");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("<<< PIM CLIENT - ON START >>>");
        showWindow(primaryStage);
    }

    @Override
    public void stop() {
        log.info("<<< PIM CLIENT - ON STOP  >>>");
    }
}