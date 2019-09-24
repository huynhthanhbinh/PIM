package com.bht.pim.base;

import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.workbench.FXWorkbench;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author bht
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public abstract class BaseWorkbench implements FXWorkbench, BaseBean {

    public static final VBox LAYOUT = new VBox();

    @Autowired
    private AnnotationConfigApplicationContext applicationContext;

    @Override
    public final void handleInitialLayout(
            Message<Event, Object> message,
            WorkbenchLayout<Node> layout, Stage stage) {

        layout.setWorkbenchXYSize(1280, 700);
        layout.setStyle(StageStyle.DECORATED);
        layout.setMenuEnabled(false);

        applicationContext.registerShutdownHook(); // for bean destruction of spring @PreDestroy
    }

    @Override
    public final void postHandle(FXComponentLayout componentLayout) {

        LAYOUT.setAlignment(Pos.CENTER);
        LAYOUT.prefWidthProperty().bind(componentLayout.getGlassPane().widthProperty());
        LAYOUT.prefHeightProperty().bind(componentLayout.getGlassPane().heightProperty());
    }
}