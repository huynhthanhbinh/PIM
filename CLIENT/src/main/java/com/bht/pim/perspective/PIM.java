package com.bht.pim.perspective;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.handler.PimErrorHandler;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.perspective.FXPerspective;

@Log4j
@Perspective(id = AppConfiguration.PERSPECTIVE_PIM, name = "PerspectivePIM",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/perspective/PIM.fxml",
        components = {
                AppConfiguration.COMPONENT_TOP,
                AppConfiguration.COMPONENT_LEFT,
                AppConfiguration.COMPONENT_MAIN,})
public class PIM implements FXPerspective {

    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane topPane;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane mainPane;
    @Resource
    private Context context;

    @Override
    public void handlePerspective(Message<Event, Object> message,
                                  PerspectiveLayout perspectiveLayout) {
        log.info("On handle perspective: " + perspectiveLayout.getClass().getSimpleName() +
                " >>> in: " + context.getId());
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout componentLayout) {
        log.info("[DESTROY] FXPerspective: " + context.getId());
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        log.info("[SHOW] FXPerspective: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        log.info("[HIDE] FXPerspective: " + context.getId());
    }

    @PostConstruct
    public void onStartPerspective(final PerspectiveLayout perspectiveLayout,
                                   final FXComponentLayout layout) {

        // using for handling error / exception
        // to send message to perspective default
        PimErrorHandler.CONTEXT_PROPERTY.set(context);

        // Register root component
        perspectiveLayout.registerRootComponent(rootPane);
        // Register other components
        perspectiveLayout.registerTargetLayoutComponent(
                AppConfiguration.TARGET_CONTAINER_TOP, topPane);
        perspectiveLayout.registerTargetLayoutComponent(
                AppConfiguration.TARGET_CONTAINER_LEFT, leftPane);
        perspectiveLayout.registerTargetLayoutComponent(
                AppConfiguration.TARGET_CONTAINER_MAIN, mainPane);

        layout.getGlassPane().setMinWidth(1280);
        layout.getGlassPane().setMinHeight(720);
    }
}
