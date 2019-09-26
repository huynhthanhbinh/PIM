package com.bht.pim.preloader;

import java.io.IOException;
import java.util.Objects;

import com.bht.pim.util.ImageUtil;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author bht
 */
public class PreLoader extends Preloader {

    private static final String PRELOADER_PATH = "com/bht/pim/preloader/PreLoader.fxml";
    private static Stage loaderStage;

    @Override
    public synchronized void start(Stage primaryStage) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects
                .requireNonNull(getClass().getClassLoader().getResource(PRELOADER_PATH)));
        Scene scene = new Scene(anchorPane, 500, 300);

        loaderStage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.getIcons().add(ImageUtil.getImage("icon"));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static void closePreloader() {
        if (loaderStage != null) {
            loaderStage.close();
            loaderStage = null;
        }
    }
}