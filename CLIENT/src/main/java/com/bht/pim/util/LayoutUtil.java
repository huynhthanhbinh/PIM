package com.bht.pim.util;

import org.jacpfx.rcp.context.Context;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 *
 * @author bht
 */
public final class LayoutUtil {

    private LayoutUtil() {
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