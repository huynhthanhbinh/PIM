package com.bht.pim.util;

import org.springframework.stereotype.Component;

import com.bht.pim.base.BasePerspective;
import com.bht.pim.base.ParentFragment;
import com.bht.pim.configuration.AppConfiguration;

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
public final class ViewUtil {
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
        BasePerspective currentPerspective = AppConfiguration.PERSPECTIVE_PROPERTY.get();
        System.out.println("\n" + currentPerspective.getClass().getSimpleName());
        currentPerspective.getChildComponents().forEach(component -> {
            System.out.println("\t" + component.getClass().getSimpleName());
            Object currentFragment = component.getCurrentFragment().getController();
            System.out.println("\t\t" + currentFragment.getClass().getSimpleName());
            if (currentFragment instanceof ParentFragment) {
                ((ParentFragment) currentFragment).getChildFragments().stream()
                        .map(childFragment -> childFragment.getClass().getSimpleName())
                        .forEach(childFragmentClass ->
                                System.out.println("\t\t\t" + childFragmentClass));
            }
        });
        System.out.println();
    }
}
