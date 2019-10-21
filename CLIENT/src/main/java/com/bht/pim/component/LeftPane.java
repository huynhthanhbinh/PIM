package com.bht.pim.component;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponent;
import com.bht.pim.fragment.menu.LeftMenuFragment;
import com.bht.pim.message.impl.PerspectiveShowing;

import javafx.event.Event;
import javafx.scene.Node;

/**
 *
 * @author bht
 */
@View(id = LeftPane.ID, name = LeftPane.ID,
        initialTargetLayoutId = LeftPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class LeftPane extends BaseComponent {

    public static final String ID = "leftComponent";
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
        leftMenuFragment = registerComponentFragment(LeftMenuFragment.class);
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {

        if (message.isMessageBodyTypeOf(PerspectiveShowing.class)) {
            leftMenuFragment.getController().onShowed();
            switchComponentFragment(this, LeftMenuFragment.class);
        }

        return this; // otherwise, it won't show UI
    }
}