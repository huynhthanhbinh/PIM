package com.bht.pim.app;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Objects;

// What is property ? --> Eg: StringProperty instead String
// Why use property ? --> Event-listener + Binding
// Bind vs BindDirectional ?
public class PropertyBinding extends Application {

    private Logger logger = Logger.getLogger(PropertyBinding.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();

        primaryStage.setTitle("Project Information Management");
        primaryStage.getIcons().add(
                new Image(Objects.requireNonNull(classLoader
                        .getResourceAsStream("pictures/icon.png"))));

        IntegerProperty x = new SimpleIntegerProperty(8);
        IntegerProperty y = new SimpleIntegerProperty();

        y.bind(x.multiply(11));

        logger.info("X value: " + x.getValue()); // 8
        logger.info("Y value: " + y.getValue()); // 88

        x.setValue(9); // note that we don't change y

        logger.info("X value: " + x.getValue()); // 9
        logger.info("Y value: " + y.getValue()); // 99

        // Common usage: bind the control
        // with the current window size
        // therefore, its position will change
        // on resize window, etc...

        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 450, 400);

        primaryStage.setScene(scene);
        showWindow(primaryStage);
    }

    private void showWindow(Stage window) {
        window.setResizable(true);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
}
