package com.bht.pim.dialog.dialogs;

import com.bht.pim.base.BaseDialog;

import javafx.scene.layout.VBox;

/**
 * make a SINGLETON object in java
 *
 * @author bht
 */
public final class HelpDialog extends BaseDialog {

    private static HelpDialog helpDialog;

    private HelpDialog() {
    }

    @Override
    public VBox getInstance() {
        return null;
    }
//
//    private static synchronized HelpDialog getInstance() {
//        if (helpDialog == null) {
//            helpDialog = new HelpDialog();
//        }
//        return helpDialog;
//    }


}
