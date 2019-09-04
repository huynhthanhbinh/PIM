package com.bht.pim.component;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.menu.LeftMenuFragment;

import javafx.event.Event;
import javafx.scene.Node;

/**
 * @author bht
 */
@Controller
@View(id = LeftPane.ID, name = "LeftPane",
        initialTargetLayoutId = LeftPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class LeftPane extends BaseComponent {

    public static final String ID = "idcLeft";
    public static final String CONTAINER = "PLeft";

    private ManagedFragmentHandler<LeftMenuFragment> leftMenuFragment;

    @Resource
    private Context context;

    @Override
    protected void initComponent() {
        componentContext = context;
    }

    @Override
    protected void initLayout() {
        setMinSize(200, 600);
        setPrefSize(200, 600);
    }

    @Override
    protected void loadFragments() {
        leftMenuFragment = context.getManagedFragmentHandler(LeftMenuFragment.class);
        getChildren().add(leftMenuFragment.getFragmentNode());
        currentFragment = leftMenuFragment;
    }

    @Override
    protected void createFragmentList() {
        // ...
    }

    @Override
    protected void assignChildren() {
        // ...
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {
        if (message.messageBodyEquals("selectDefault")) {
            leftMenuFragment.getController().onShowed();
        }
        return this; // otherwise, it won't show UI
    }
}
