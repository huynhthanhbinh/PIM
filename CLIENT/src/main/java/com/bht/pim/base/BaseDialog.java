package com.bht.pim.base;

import org.jacpfx.rcp.context.Context;

import com.bht.pim.annotation.InheritedComponent;
import com.bht.pim.workbench.PimWorkbench;

import javafx.scene.layout.VBox;

/**
 *
 * @author bht
 */
@InheritedComponent
public abstract class BaseDialog extends VBox implements BaseBean {

    private static final VBox DIALOG_BOUND = PimWorkbench.LAYOUT;

    final VBox getDialogInBound(Context context) {
        DIALOG_BOUND.getChildren().clear();
        addEventListener(context);
        return DIALOG_BOUND;
    }

    private void addEventListener(Context context) {
        DIALOG_BOUND.setOnMouseClicked(event -> {
            if (!isHover()) {
                context.hideModalDialog();
            }
        });
    }
}
