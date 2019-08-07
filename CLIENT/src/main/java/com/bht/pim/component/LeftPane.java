package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;

import java.util.ResourceBundle;

@Log4j
@DeclarativeView(id = AppConfiguration.COMPONENT_LEFT,
        name = "LeftPane",
        resourceBundleLocation = "bundles.languageBundle",
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_LEFT,
        viewLocation = "/com/bht/pim/component/LeftPane.fxml")
public class LeftPane implements FXComponent {

    @FXML
    public AnchorPane leftPane;

    @Resource
    private Context context;

    @Resource
    private ResourceBundle bundle;

    @Override
    public Node handle(Message<Event, Object> message) throws Exception {
        return null;
    }

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) throws Exception {
        return null;
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout arg0,
                                 final ResourceBundle resourceBundle) {
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout arg0) {
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        log.info("[SHOW] FXComponentLayout: " + componentLayout + " in: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        log.info("[HIDE] FXComponentLayout: " + componentLayout + " in: " + context.getId());
    }
}
