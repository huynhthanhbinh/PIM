package com.bht.pim.workbench;

import com.bht.pim.configuration.AppConfiguration;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.workbench.Workbench;
import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.workbench.FXWorkbench;

@Log4j
@Workbench(id = "workbench", name = "workbench",
        perspectives = {
                AppConfiguration.PERSPECTIVE,
                AppConfiguration.PERSPECTIVE_LOGIN})
public class PimWorkbench implements FXWorkbench {

    @Override
    public void handleInitialLayout(
            Message<Event, Object> message,
            WorkbenchLayout<Node> layout, Stage stage) {

        log.info("<<< PIM Client - On handleInitialLayout >>>");
        layout.setWorkbenchXYSize(1280, 700);
        layout.setStyle(StageStyle.DECORATED);
        layout.setMenuEnabled(false);
        /*layout.registerToolBar(ToolbarPosition.NORTH);*/
    }

    @Override
    public void postHandle(FXComponentLayout fxComponentLayout) {
        log.info("<<< PIM Client - On postHandle >>>");
    }
}
