package com.bht.pim.util;

import org.springframework.stereotype.Component;

import com.bht.pim.base.BasePerspective;
import com.bht.pim.configuration.AppConfiguration;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import lombok.extern.log4j.Log4j;

/**
 * @author bht
 */
@Log4j
@Component
public final class ViewUtil {
    public static final KeyCodeCombination VIEW_GRAPHICS_KEY_COMBINATION = new KeyCodeCombination(
            KeyCode.G,
            KeyCombination.SHIFT_DOWN,
            KeyCodeCombination.ALT_DOWN,
            KeyCodeCombination.CONTROL_DOWN);

    public static void viewGraphics() {
        BasePerspective currentPerspective = AppConfiguration.PERSPECTIVE_PROPERTY.get();
        System.out.println("\n" + currentPerspective.getClass().getSimpleName());
        currentPerspective.getChildComponents().forEach(component -> {
            System.out.println("\t" + component.getClass().getSimpleName());
        });
        System.out.println();
    }
}
