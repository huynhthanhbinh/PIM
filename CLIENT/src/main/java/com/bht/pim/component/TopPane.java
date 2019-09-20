package com.bht.pim.component;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.menu.TopMenuFragment;

import javafx.event.Event;
import javafx.scene.Node;

/**
 * @author bht
 */
@View(id = TopPane.ID, name = "TopPane",
        initialTargetLayoutId = TopPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class TopPane extends BaseComponent {

    public static final String ID = "topComponent";
    public static final String CONTAINER = "PTop";

    @Resource
    private Context context;

    @Override
    protected void initComponent() {
        componentContext = context;
    }

    @Override
    protected void initLayout() {
        setMinHeight(100);
        setMaxHeight(100);
        setPrefSize(1260, 100);
        prefWidthProperty().bind(context.getComponentLayout()
                .getGlassPane().widthProperty().subtract(20));
    }

    @Override
    protected void loadFragments() {
        ManagedFragmentHandler<TopMenuFragment> topMenuFragment = registerComponentFragment(TopMenuFragment.class);
        getChildren().add(topMenuFragment.getFragmentNode());
        currentFragment = topMenuFragment;
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {
        return this; // otherwise, it won't show UI
    }
}
