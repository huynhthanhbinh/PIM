package com.bht.pim.dialog.factory;

import com.bht.pim.base.BaseDialog;
import com.bht.pim.workbench.PimWorkbench;

import javafx.scene.layout.VBox;

/**
 *
 * @author bht
 */
public final class DialogFactory {

    private static final VBox LAYOUT = PimWorkbench.LAYOUT;

    public static <D extends BaseDialog> VBox getDialog(Class<D> dialogClass) {
        try {
            return (VBox) dialogClass.getField("instance").get(VBox.class);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }
}
