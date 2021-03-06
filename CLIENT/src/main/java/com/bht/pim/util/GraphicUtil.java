package com.bht.pim.util;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.base.BasePerspective;
import com.bht.pim.notification.JFXNotificationType;

import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

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
        NotificationUtil.showNotification(JFXNotificationType.INFO, Pos.CENTER, graphic.toString());
    }
}