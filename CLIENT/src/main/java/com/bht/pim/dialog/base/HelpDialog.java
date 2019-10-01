package com.bht.pim.dialog.base;

import java.io.IOException;

import org.springframework.context.annotation.DependsOn;

import com.bht.pim.base.BaseDialog;
import com.bht.pim.util.LoadingUtil;

import javafx.scene.layout.Pane;

/**
 *
 * @author bht
 */
@DependsOn("languageProperty")
public final class HelpDialog extends BaseDialog {

    private static final String CONTENT_PATH = "dialog/content/HelpDialogContent.fxml";

    @Override
    public void initialize() throws IOException {
        super.initialize();
        setDialogTitle("label.dialog.help.title");
        setDialogSize(500, 300);
    }

    @Override
    protected Pane getDialogContent() throws IOException {
        return LoadingUtil.loadFXML(CONTENT_PATH).getKey();
    }
}