package com.bht.pim.perspective;

import com.bht.pim.configuration.AppConfiguration;
import javafx.event.Event;
import javafx.fxml.FXML;
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

import java.util.ResourceBundle;

@Log4j
@Perspective(id = AppConfiguration.PERSPECTIVE, name = "Perspective",
        resourceBundleLocation = "bundles.languageBundle",
        viewLocation = "/com/bht/pim/perspective/PIM.fxml",
        components = {
                AppConfiguration.COMPONENT_TOP,
                AppConfiguration.COMPONENT_LEFT,
                AppConfiguration.COMPONENT_MAIN,})
public class PIM implements FXPerspective {

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
    @Resource
    private ResourceBundle bundle;

    @Override
    public void handlePerspective(Message<Event, Object> message,
                                  PerspectiveLayout perspectiveLayout) {
        log.info("On handle perspective: " + perspectiveLayout + " in " + context.getId());
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        log.info("[SHOW] FXComponentLayout: " + componentLayout + " in: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        log.info("[HIDE] FXComponentLayout: " + componentLayout + " in: " + context.getId());
    }

    @PreDestroy
    public void onTearDownPerspective(final FXComponentLayout arg0) {
    }

    @PostConstruct
    public void onStartPerspective(final PerspectiveLayout perspectiveLayout,
                                   final FXComponentLayout layout,
                                   final ResourceBundle resourceBundle) {
        // Register root component
        perspectiveLayout.registerRootComponent(rootPane);
        // Register other components
        perspectiveLayout.registerTargetLayoutComponent(
                AppConfiguration.TARGET_CONTAINER_TOP, topPane);
        perspectiveLayout.registerTargetLayoutComponent(
                AppConfiguration.TARGET_CONTAINER_LEFT, leftPane);
        perspectiveLayout.registerTargetLayoutComponent(
                AppConfiguration.TARGET_CONTAINER_MAIN, mainPane);
    }
}
