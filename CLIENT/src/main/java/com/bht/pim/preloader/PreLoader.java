package com.bht.pim.preloader;

import java.io.IOException;
import java.util.Objects;

import com.bht.pim.util.ImageUtil;
import com.bht.pim.util.LoggingUtil;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author bht
 */
@Log4j
public class PreLoader extends Preloader {

    private static final String PRELOADER_PATH = "com/bht/pim/preloader/PreLoader.fxml";
    private static final int PANE_WIDTH = 500;
    private static final int PANE_HEIGHT = 300;
    private static Stage preLoaderStage;

    @Override
    public synchronized void start(Stage primaryStage) throws IOException {
        log.info(LoggingUtil.format("INFO", "PreLoader", "show preloader pane"));
        preLoaderStage = primaryStage;
        initPreLoaderStage();
    }

    private void initPreLoaderStage() throws IOException {
        preLoaderStage.setScene(getPreloaderScene());
        preLoaderStage.setWidth(PANE_WIDTH);
        preLoaderStage.setHeight(PANE_HEIGHT);
        preLoaderStage.setResizable(false);
        preLoaderStage.initStyle(StageStyle.UNDECORATED);
        preLoaderStage.setAlwaysOnTop(true);
        preLoaderStage.getIcons().add(ImageUtil.getImage("icon"));
        preLoaderStage.show();
        preLoaderStage.centerOnScreen();
    }

    private Scene getPreloaderScene() throws IOException {
        return new Scene(
                FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(PRELOADER_PATH))),
                PANE_WIDTH,
                PANE_HEIGHT);
    }

    // preloader pane will be hide on workbench finished initialing
    public static void closePreloader() {
        log.info(LoggingUtil.format("INFO", "PreLoader", "hide preloader pane"));

        if (preLoaderStage != null) {
            preLoaderStage.close();
            preLoaderStage = null;
        }
    }
}