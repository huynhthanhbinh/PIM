package com.bht.pim.util;

import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.base.BasePerspective;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.notification.NotificationStyle;

import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import lombok.extern.log4j.Log4j;

/**
 * @author bht
 *
 * purpose:
 * + run on runtime
 * + to view screen structure detail
 * + to view what are on the screen
 * + shortcut key: Ctrl + Alt + Shift + G
 * + G stands for graphics
 */
@Log4j
@Component
public final class GraphicUtil {

    private GraphicUtil() {
    }

    /**
     * Keycode combination: Ctrl + Alt + Shift + G
     */
    public static final KeyCodeCombination VIEW_GRAPHICS_KEY_COMBINATION = new KeyCodeCombination(
            KeyCode.G,
            KeyCombination.SHIFT_DOWN,
            KeyCodeCombination.ALT_DOWN,
            KeyCodeCombination.CONTROL_DOWN);

    /**
     * Method view graphics
     * + log current perspective
     * + log components of current perspective
     * + log all fragments on screen
     */
    public static void viewGraphics() {
        StringBuilder graphic = new StringBuilder();
        BasePerspective currentPerspective = AppConfiguration.PERSPECTIVE_PROPERTY.get();
        graphic.append("\n").append(currentPerspective.getClass().getSimpleName()).append("\n");
        currentPerspective.getChildComponents().forEach(component -> {
            graphic.append("\t").append(component.getClass().getSimpleName()).append("\n");
            BaseComponentFragment currentFragment = component.getCurrentFragment().getController();
            graphic.append("\t\t").append(currentFragment.getClass().getSimpleName()).append("\n");
            currentFragment.getChildrenFragments().stream()
                    .map(childFragment -> childFragment.getClass().getSimpleName())
                    .forEach(childFragmentClass ->
                            graphic.append("\t\t\t").append(childFragmentClass).append("\n"));
        });
        graphic.append("\n");
        System.out.println(graphic.toString());
        NotificationUtil.showNotification(NotificationStyle.INFO, Pos.CENTER, graphic.toString());
    }
}
