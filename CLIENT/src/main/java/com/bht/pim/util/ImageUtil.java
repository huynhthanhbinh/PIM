package com.bht.pim.util;

import java.util.Objects;

import javafx.scene.image.Image;

/**
 *
 * @author bht
 */
public final class ImageUtil {

    private ImageUtil() {
    }

    /**
     * purpose: get image more easy by calling util
     * warning: image must be in .png format
     *
     * @param name name of image in resource folder
     * @return new image getting from project's resource folder
     */
    public static Image getImage(String name) {
        return new Image(Objects.requireNonNull(ImageUtil.class
                        .getClassLoader().getResourceAsStream("pictures/" + name + ".png"),
                "Image does not exist or inputted image's name is incorrect !"));
    }
}
