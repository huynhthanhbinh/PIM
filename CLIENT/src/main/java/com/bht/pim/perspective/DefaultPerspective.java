package com.bht.pim.perspective;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.base.BasePerspective;
import com.bht.pim.component.BottomPane;
import com.bht.pim.component.TopPane;
import com.bht.pim.configuration.AppConfiguration;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author bht
 */
@Perspective(id = DefaultPerspective.ID, name = "PerspectiveDefault",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/perspective/DefaultPerspective.fxml",
        components = {TopPane.ID, BottomPane.ID})
public final class DefaultPerspective extends BasePerspective {

    public static final String ID = "idDefaultPerspective";

    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane topPane;
    @FXML
    private HBox bottomPane;
    @Resource
    private Context context;

    @Override
    protected void getContext() {
        perspectiveContext = context;
    }

    @Override
    protected void onShowed() {
        // ...
    }

    @Override
    protected void onCreated(PerspectiveLayout perspectiveLayout, FXComponentLayout layout) {
        // Register root component
        perspectiveLayout.registerRootComponent(rootPane);
        // Register other components
        perspectiveLayout.registerTargetLayoutComponent(
                TopPane.CONTAINER, topPane);
        perspectiveLayout.registerTargetLayoutComponent(
                BottomPane.CONTAINER, bottomPane);

        layout.getGlassPane().setMinWidth(1280);
        layout.getGlassPane().setMinHeight(720);
    }

    @Override
    protected void handleMessage(Message<Event, Object> message) {
        if (message.getMessageBody().equals("init")) {
            context.send(TopPane.ID, this);
            context.send(BottomPane.ID, this);
            return;
        }
        context.send(BottomPane.ID, message.getMessageBody());
    }
}