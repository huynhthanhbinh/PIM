package com.bht.pim.app.practice;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Objects;

public class CloseProgram extends Application {

    private Logger logger = Logger.getLogger(CloseProgram.class);
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

        window.setOnCloseRequest(event -> {
            // waiting for the response ! vital !
            // stop this event from this event handler definition !
            // As usual, close handler will close the application
            // on close request, although any function call after
            event.consume();
            closeProgram();
        });

        Button button = new Button("Close Program");
        button.setOnAction(event -> closeProgram());


        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 100);
        window.setScene(scene);
        window.show();
    }

    private void closeProgram() {

        if (ConfirmBox.display(
                "Exit warning", "Are you sure ?")) {

            logger.info("File has been saved !");
            logger.warn("Closing the program !");
            window.close();
        }
    }
}
