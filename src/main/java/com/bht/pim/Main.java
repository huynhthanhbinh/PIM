package com.bht.pim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Parent rootNode;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(Main.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("sample.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
    }
}
