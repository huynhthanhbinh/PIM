package com.bht.pim.perspective;

import com.bht.pim.base.BasePerspective;
import com.bht.pim.component.LeftPane;
import com.bht.pim.component.MainPane;
import com.bht.pim.component.TopPane;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.handler.PimErrorHandler;
import com.bht.pim.message.impl.PerspectiveShowing;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.context.Context;

/**
 * @author bht
 */
@Perspective(id = AppConfiguration.PERSPECTIVE_PIM, name = "PerspectivePIM",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/perspective/PIM.fxml",
        components = {TopPane.ID, LeftPane.ID, MainPane.ID})
public class PIM extends BasePerspective {

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
    protected void getContext() {
        perspectiveContext = context;
    }

    @Override
    protected void onShowed() {
        // check if connection is lost ? show error : continue
        PerspectiveShowing perspectiveShowing = new PerspectiveShowing(PIM.class);
        context.send(MainPane.ID, perspectiveShowing);
    }

    @Override
    protected void onCreated(final PerspectiveLayout perspectiveLayout, final FXComponentLayout layout) {
        // using for handling error / exception
        // to send message to perspective default
        PimErrorHandler.CONTEXT_PROPERTY.set(context);

        // Register root component
        perspectiveLayout.registerRootComponent(rootPane);
        // Register other components
        perspectiveLayout.registerTargetLayoutComponent(
                TopPane.CONTAINER, topPane);
        perspectiveLayout.registerTargetLayoutComponent(
                LeftPane.CONTAINER, leftPane);
        perspectiveLayout.registerTargetLayoutComponent(
                MainPane.CONTAINER, mainPane);

        layout.getGlassPane().setMinWidth(1280);
        layout.getGlassPane().setMinHeight(720);
    }

    @Override
    protected void handleMessage(Message<Event, Object> message) {
        // ...
    }
}
