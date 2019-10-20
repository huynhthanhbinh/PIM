package com.bht.pim.perspective;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BasePerspective;
import com.bht.pim.component.LeftPane;
import com.bht.pim.component.MainPane;
import com.bht.pim.component.TopPane;
import com.bht.pim.message.impl.PerspectiveShowing;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author bht
 */
@Perspective(id = PimPerspective.ID, name = PimPerspective.ID,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/perspective/PimPerspective.fxml",
        components = {TopPane.ID, LeftPane.ID, MainPane.ID})
public final class PimPerspective extends BasePerspective {

    public static final String ID = "pimPerspective";

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
        PerspectiveShowing perspectiveShowing = new PerspectiveShowing();
        context.send(MainPane.ID, perspectiveShowing);
        context.send(LeftPane.ID, "selectDefault");
    }

    @Override
    protected void onCreated(final PerspectiveLayout perspectiveLayout, final FXComponentLayout layout) {
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
        if (message.getMessageBody().equals("init")) {
            context.send(TopPane.ID, this);
            context.send(LeftPane.ID, this);
            context.send(MainPane.ID, this);
        }
    }
}