package com.bht.pim.base;

import java.io.IOException;

import com.bht.pim.AppConfiguration;
import com.bht.pim.annotation.InheritedComponent;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.LoadingUtil;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;

/**
 * For using multilingual,
 * all of this's subclasses have to define an annotation :
 * {@code @Lazy} or {@code @DependsOn("languageProperty")}
 * Otherwise, subclasses will be create first
 * while bean LanguageProperty has not been created yet !!
 * Therefore, we cannot use multilingual
 *
 * @author bht
 */
@InheritedComponent
public abstract class BaseDialog extends VBox implements BaseBean {

    private static final VBox DIALOG_BOUND = BaseWorkbench.LAYOUT;
    private ImageView icon;
    private Label title;
    private AnchorPane titleBar;
    protected Button bClose;

    @Getter
    protected boolean isShowing;

    @Override
    public void initialize() throws IOException {
        BaseBean.super.initialize();
        addStyleSheet();
        initIcon();
        initLabel();
        initButtonClose();
        initTitleBar();
        initDialog();
    }

    public final void show() {
        AppConfiguration.PERSPECTIVE_PROPERTY.get().showModalDialog(getDialogInBound());
        isShowing = true;
    }

    public final void hide() {
        AppConfiguration.PERSPECTIVE_PROPERTY.get().hideModalDialog();
        isShowing = false;
    }

    private VBox getDialogInBound() {
        clearCurrentLayout();
        addAllEventListeners();
        boundCurrentDialog();
        return DIALOG_BOUND;
    }

    private void addStyleSheet() {
        getStylesheets().add("/com/bht/pim/dialog/style/DialogStyle.css");
    }

    private void initIcon() {
        icon = new ImageView(LoadingUtil.loadImage("icon_inverse"));
        icon.setPreserveRatio(true);
        icon.setFitHeight(20);
        AnchorPane.setTopAnchor(icon, 5.0);
        AnchorPane.setLeftAnchor(icon, 3.0);
    }

    private void initLabel() {
        title = new Label();
        title.setId("title");
        AnchorPane.setTopAnchor(title, 8.0);
        AnchorPane.setLeftAnchor(title, 30.0);
    }

    private void initButtonClose() {
        bClose = new Button();
        bClose.setId("bClose");
        AnchorPane.setTopAnchor(bClose, 2.0);
        AnchorPane.setRightAnchor(bClose, 2.0);
        AnchorPane.setBottomAnchor(bClose, 2.0);
        bClose.setText("X");
    }

    private void initTitleBar() {
        titleBar = new AnchorPane();
        titleBar.setId("titleBar");
        titleBar.getChildren().addAll(icon, title, bClose);
    }

    private void initDialog() throws IOException {
        setId("dialog");
        setAlignment(Pos.TOP_CENTER);
        getChildren().add(titleBar);
        getChildren().add(getDialogContent());
    }

    private void clearCurrentLayout() {
        DIALOG_BOUND.getChildren().clear();
    }

    private void boundCurrentDialog() {
        DIALOG_BOUND.getChildren().add(this);
    }

    protected void addAllEventListeners() {
        addButtonCloseEventListener();
        addDialogBoundEventListener();
    }

    private void addButtonCloseEventListener() {
        bClose.setOnMouseClicked(event -> hide());
    }

    private void addDialogBoundEventListener() {
        DIALOG_BOUND.setOnMouseClicked(event -> {
            if (!isHover()) {
                hide(); // ==> close dialog if click outside !
            }
        });
    }

    protected final void setDialogSize(int width, int height) {
        setMaxSize(width, height);
        setPrefSize(width, height);
        titleBar.setPrefSize(width, 30);
    }

    protected final void setDialogTitle(String dialogTitleKey) {
        LanguageUtil.initLabel(title.textProperty(), dialogTitleKey);
    }

    protected abstract Pane getDialogContent() throws IOException;
}