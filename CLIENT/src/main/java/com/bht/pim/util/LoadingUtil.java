package com.bht.pim.util;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

/**
 *
 * @author bht
 */
public final class LoadingUtil {

    private static final String SOURCE_ROOT_PATH = "com/bht/pim/";

    private LoadingUtil() {
    }

    /**
     * purpose: get image more easy by calling util
     * warning: image must be in .png format
     * @param name name of image in resource folder
     * @return new image getting from project's resource folder
     */
    public static Image loadImage(String name) {
        return new Image(Objects.requireNonNull(LoadingUtil.class
                        .getClassLoader().getResourceAsStream("pictures/" + name + ".png"),
                "Image does not exist or inputted image's name is incorrect !"));
    }

    /**
     * @param pathFromSourceRoot path exclude SOURCE_ROOT_PATH
     * @param <P> extends Pane
     * @param <C> pane's controller
     * @return new Pane from FXML eg. VBox, HBox, GridPane,...
     * @throws IOException if cannot find this FXML
     */
    public static <P extends Pane, C> Pair<P, C> loadFXML(String pathFromSourceRoot) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LoadingUtil.class
                        .getClassLoader().getResource(SOURCE_ROOT_PATH + pathFromSourceRoot),
                "FXML does not exist or inputted path is incorrect !"));

        return new Pair<>(loader.load(), loader.getController());
    }
}