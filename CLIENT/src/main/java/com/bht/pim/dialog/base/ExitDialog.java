package com.bht.pim.dialog.base;

import java.io.IOException;

import org.springframework.context.annotation.DependsOn;

import com.bht.pim.base.BaseDialog;
import com.bht.pim.dialog.content.ExitDialogContent;
import com.bht.pim.fragment.children.confirm.Confirmable;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.LoadingUtil;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import javafx.util.Pair;
import lombok.Setter;

/**
 *
 * @author bht
 */
@DependsOn("languageProperty")
public final class ExitDialog extends BaseDialog implements Confirmable {

    private static final String CONTENT_PATH = "dialog/content/ExitDialogContent.fxml";

    @FXML
    private ExitDialogContent exitDialogContent;
    @Setter
    private WindowEvent exitEvent;
    @Setter
    private EventHandler<WindowEvent> closeAppEventHandler;

    @Override
    public void initialize() throws IOException {
        super.initialize();
        initAllLabels();
        bClose.setVisible(false);
        setDialogTitle("label.dialog.exit.title");
        setDialogSize(600, 200);
    }

    @Override
    protected Pane getDialogContent() throws IOException {
        Pair<AnchorPane, ExitDialogContent> loader = LoadingUtil.loadFXML(CONTENT_PATH);
        exitDialogContent = loader.getValue();
        return loader.getKey();
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(exitDialogContent.getBSubmit().textProperty(), "label.dialog.exit.yes");
        LanguageUtil.initLabel(exitDialogContent.getBCancel().textProperty(), "label.dialog.exit.no");
    }

    @Override
    protected void addAllEventListeners() {
        super.addAllEventListeners();
        exitDialogContent.getBSubmit().setOnMouseClicked(this::onSubmit);
        exitDialogContent.getBCancel().setOnMouseClicked(this::onCancel);
    }

    @Override
    public void onSubmit(MouseEvent event) {
        closeAppEventHandler.handle(exitEvent);
    }

    @Override
    public void onCancel(MouseEvent event) {
        hide();
    }
}