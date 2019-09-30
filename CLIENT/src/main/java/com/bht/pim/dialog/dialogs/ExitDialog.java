package com.bht.pim.dialog.dialogs;

import org.springframework.context.annotation.DependsOn;

import com.bht.pim.base.BaseDialog;

/**
 *
 * @author bht
 */
@DependsOn({"languageProperty", "exitDialogContent"})
public final class ExitDialog extends BaseDialog {

    @Override
    public void initialize() {
        super.initialize();
        setDialogTitle("label.title.dialog.help");
        setDialogSize(600, 200);
    }
}