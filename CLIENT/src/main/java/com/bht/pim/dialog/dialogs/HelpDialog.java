package com.bht.pim.dialog.dialogs;

import org.springframework.context.annotation.Lazy;

import com.bht.pim.base.BaseDialog;

/**
 *
 * @author bht
 */
@Lazy
public final class HelpDialog extends BaseDialog {

    @Override
    public void initialize() {
        super.initialize();
        setDialogTitle("label.title.dialog.help");
        setDialogSize(500, 300);
    }
}