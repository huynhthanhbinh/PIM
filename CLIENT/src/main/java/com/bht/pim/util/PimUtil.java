package com.bht.pim.util;

import java.util.Objects;

import org.jacpfx.rcp.context.Context;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * @author bht
 */
public final class PimUtil {

    private PimUtil() {
    }

    public static Image getImage(String path) {
        return new Image(Objects.requireNonNull(PimUtil.class.getClassLoader()
                .getResourceAsStream("pictures/" + path + ".png")));
    }

    public static void alignPane(VBox mainPane, Context context) {
        mainPane.setAlignment(Pos.CENTER);
        mainPane.prefWidthProperty().bind(context
                .getComponentLayout()
                .getGlassPane()
                .widthProperty()
                .subtract(227));
    }
}