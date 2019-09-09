package com.bht.pim.workbench;

import javax.annotation.PostConstruct;

import org.jacpfx.api.annotations.workbench.Workbench;
import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.workbench.FXWorkbench;

import com.bht.pim.configuration.AppConfiguration;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.log4j.Log4j;

/**
 * @author bht
 */
@Log4j
@Workbench(id = "workbench", name = "workbench",
        perspectives = {
                AppConfiguration.PERSPECTIVE_PIM,
                AppConfiguration.PERSPECTIVE_DEFAULT})
public final class PimWorkbench implements FXWorkbench {

    @PostConstruct
    private void onBeanCreation() {
        log.info("[SPRING] BeanCreation: " + getClass().getSimpleName());
    }

    @Override
    public void handleInitialLayout(
            Message<Event, Object> message,
            WorkbenchLayout<Node> layout, Stage stage) {

        log.info("[INIT]   FXWorkbench:  " + getClass().getSimpleName());
        layout.setWorkbenchXYSize(1280, 700);
        layout.setStyle(StageStyle.DECORATED);
        layout.setMenuEnabled(false);
    }

    @Override
    public void postHandle(FXComponentLayout fxComponentLayout) {
        log.info("[HANDLE] FXWorkbench:  " + getClass().getSimpleName());
    }
}
