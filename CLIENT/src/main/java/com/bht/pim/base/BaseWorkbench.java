package com.bht.pim.base;

import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.workbench.FXWorkbench;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bht.pim.AppConfiguration;
import com.bht.pim.dialog.content.ExitDialogContent;
import com.bht.pim.dialog.dialogs.ExitDialog;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author bht
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public abstract class BaseWorkbench implements FXWorkbench, BaseBean {

    static final VBox LAYOUT = new VBox();

    @Autowired
    private AnnotationConfigApplicationContext applicationContext;
    @Autowired
    private ExitDialog exitDialog;
    @Autowired
    private ExitDialogContent exitDialogContent;

    @Override
    public final void handleInitialLayout(Message<Event, Object> message, WorkbenchLayout<Node> layout, Stage stage) {
        shareContext();
        initAppLayout(layout);
        addCloseRequestHandler(stage);
        registerShutdownHook();
    }

    @Override
    public final void postHandle(FXComponentLayout componentLayout) {
        LAYOUT.setAlignment(Pos.CENTER);
        LAYOUT.prefWidthProperty().bind(componentLayout.getGlassPane().widthProperty());
        LAYOUT.prefHeightProperty().bind(componentLayout.getGlassPane().heightProperty());
    }

    protected abstract void shareContext();

    private void initAppLayout(WorkbenchLayout<Node> layout) {
        layout.setWorkbenchXYSize(1280, 700);
        layout.setStyle(StageStyle.DECORATED);
        layout.setMenuEnabled(false);
    }

    private void addCloseRequestHandler(Stage stage) {
        exitDialogContent.setCloseAppEventHandler(stage.getOnCloseRequest());
        stage.setOnCloseRequest(this::onCloseRequest);
    }

    /**
     * for bean destruction of spring @PreDestroy
     */
    private void registerShutdownHook() {
        applicationContext.registerShutdownHook();
    }

    private void onCloseRequest(WindowEvent event) {
        exitDialogContent.setExitEvent(event);
        AppConfiguration.PERSPECTIVE_PROPERTY.get().showModalDialog(exitDialog);
    }
}