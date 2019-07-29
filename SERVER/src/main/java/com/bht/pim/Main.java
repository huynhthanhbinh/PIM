package com.bht.pim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

@SpringBootApplication
public class Main extends Application {
    private Logger logger = Logger.getLogger(Main.class);
    private ConfigurableApplicationContext applicationContext;
    private Parent rootNode;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(Main.class);

        logger.info("<<< PIM SERVER - ON INIT  >>>");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getClassLoader()
                .getResource("templates/sample.fxml"));

        fxmlLoader.setControllerFactory(applicationContext::getBean);

        rootNode = fxmlLoader.load();
    }


    @Override
    public void start(Stage primaryStage) {
        logger.info("<<< PIM SERVER - ON START >>>");

        ClassLoader classLoader = getClass().getClassLoader();

        primaryStage.setTitle("Project Information Management");
        primaryStage.getIcons().add(
                new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream("pictures/icon.png"))));

        Scene scene = new Scene(rootNode, 800, 600);

        primaryStage.setScene(scene);
        showWindow(primaryStage);
    }


    @Override
    public void stop() {
        logger.info("<<< PIM SERVER - ON STOP  >>>");

        applicationContext.close();
    }


    private void showWindow(Stage window) {
        logger.info("<<< PIM SERVER - ON SHOW  >>>");

        window.setResizable(true);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
}
