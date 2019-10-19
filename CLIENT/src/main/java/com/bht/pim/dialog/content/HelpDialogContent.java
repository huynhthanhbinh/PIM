package com.bht.pim.dialog.content;

import com.bht.pim.base.BaseDialogContent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.Getter;

/**
 *
 * @author bht
 */
@Getter
public class HelpDialogContent extends BaseDialogContent {

    @FXML
    private Label author;
    @FXML
    private Label github;

    @FXML
    private Label lVersion;
    @FXML
    private Label lRelease;
    @FXML
    private Label lAuthor;
}