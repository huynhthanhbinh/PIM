package com.bht.pim.base;

import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.workbench.FXWorkbench;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bht.pim.dialog.base.ExitDialog;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
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


    @Override
    public final void handleInitialLayout(Message<Event, Object> message, WorkbenchLayout<Node> layout, Stage stage) {
        shareContext();
        initAppLayout(layout);
        addCloseRequestHandler(stage);
        registerShutdownHook();
    }

    @Override
    public final void postHandle(FXComponentLayout componentLayout) {
        Pane appLayout = componentLayout.getGlassPane();
        LAYOUT.setAlignment(Pos.CENTER);
        LAYOUT.prefWidthProperty().bind(appLayout.widthProperty());
        LAYOUT.prefHeightProperty().bind(appLayout.heightProperty());
    }

    protected abstract void shareContext();

    private void initAppLayout(WorkbenchLayout<Node> layout) {
        layout.setWorkbenchXYSize(1280, 700);
        layout.setStyle(StageStyle.DECORATED);
        layout.setMenuEnabled(false);
    }

    private void addCloseRequestHandler(Stage stage) {
        exitDialog.setCloseAppEventHandler(stage.getOnCloseRequest());
        stage.setOnCloseRequest(this::onCloseRequest);
    }

    /**
     * for bean destruction of spring @PreDestroy
     * if we not do this, method with @PreDestroy will not be executed
     */
    private void registerShutdownHook() {
        applicationContext.registerShutdownHook();
    }

    private void onCloseRequest(WindowEvent event) {
        event.consume();
        showExitDialog(event);
    }

    private void showExitDialog(WindowEvent event) {
        exitDialog.setExitEvent(event);
        if (!exitDialog.isShowing) {
            exitDialog.show();
        }
    }
}