package com.bht.pim.dialog.base;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.DependsOn;

import com.bht.pim.base.BaseDialog;
import com.bht.pim.dialog.content.HelpDialogContent;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.LoadingUtil;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author bht
 */
@Log4j
@DependsOn("languageProperty")
public final class HelpDialog extends BaseDialog {

    private static final String CONTENT_PATH = "dialog/content/HelpDialogContent.fxml";

    @FXML
    private HelpDialogContent helpDialogContent;

    @Override
    public void initialize() throws IOException {
        super.initialize();
        initAllLabels();
        setDialogTitle("label.dialog.help.title");
        setDialogSize(350, 180);
    }

    @Override
    protected Pane getDialogContent() throws IOException {
        Pair<AnchorPane, HelpDialogContent> loader = LoadingUtil.loadFXML(CONTENT_PATH);
        helpDialogContent = loader.getValue();
        return loader.getKey();
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(helpDialogContent.getLVersion().textProperty(), "label.dialog.help.version");
        LanguageUtil.initLabel(helpDialogContent.getLRelease().textProperty(), "label.dialog.help.release");
        LanguageUtil.initLabel(helpDialogContent.getLAuthor().textProperty(), "label.dialog.help.author");
    }

    @Override
    protected void addAllEventListeners() {
        super.addAllEventListeners();
        helpDialogContent.getGithub().setOnMouseClicked(event -> {
            try {
                URI uri = new URI("http://" + helpDialogContent.getGithub().getText());
                Desktop.getDesktop().browse(uri);

            } catch (IOException | URISyntaxException e) {
                log.error(e);
            }
        });
    }
}