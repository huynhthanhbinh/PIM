package com.bht.pim.dialog.content;

import com.bht.pim.base.BaseDialogContent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Getter;

/**
 *
 * @author bht
 */
@Getter
public class ExitDialogContent extends BaseDialogContent {

    @FXML
    private Button bSubmit;
    @FXML
    private Button bCancel;
}