package com.bht.pim.workbench;

import org.jacpfx.api.annotations.workbench.Workbench;
import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.workbench.FXWorkbench;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bht.pim.base.BaseBean;
import com.bht.pim.perspective.DefaultPerspective;
import com.bht.pim.perspective.PimPerspective;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author bht
 */
@Log4j
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@Workbench(id = PimWorkbench.ID, name = PimWorkbench.ID,
        perspectives = {
                PimPerspective.ID,
                DefaultPerspective.ID})
public final class PimWorkbench implements BaseBean, FXWorkbench {

    public static final String ID = "pimWorkbench";
    public static final VBox LAYOUT = new VBox();

    @Autowired
    private AnnotationConfigApplicationContext applicationContext;


    @Override
    public void handleInitialLayout(
            Message<Event, Object> message,
            WorkbenchLayout<Node> layout, Stage stage) {

        //log.info(LoggingUtil.format("INIT", "FXWorkbench", getClass().getSimpleName()));
        layout.setWorkbenchXYSize(1280, 700);
        layout.setStyle(StageStyle.DECORATED);
        layout.setMenuEnabled(false);

        applicationContext.registerShutdownHook(); // for bean destruction of spring @PreDestroy
    }


    @Override
    public void postHandle(FXComponentLayout componentLayout) {
        //log.info(LoggingUtil.format("HANDLE", "FXWorkbench", getClass().getSimpleName()));

        LAYOUT.setAlignment(Pos.CENTER);
        LAYOUT.prefWidthProperty().bind(componentLayout.getGlassPane().widthProperty());
        LAYOUT.prefHeightProperty().bind(componentLayout.getGlassPane().heightProperty());
    }
}