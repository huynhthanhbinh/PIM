package com.bht.pim.component;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponent;
import com.bht.pim.fragment.menu.TopMenuFragment;
import com.bht.pim.message.impl.PerspectiveShowing;

import javafx.event.Event;
import javafx.scene.Node;

/**
 * different from the others, TopPane is used on 2 different perspectives
 *
 * @author bht
 */
@View(id = TopPane.ID, name = TopPane.ID,
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
        registerComponentFragment(TopMenuFragment.class);
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {

        if (message.isMessageBodyTypeOf(PerspectiveShowing.class)) {
            switchComponentFragment(this, TopMenuFragment.class);
        }

        return this; // otherwise, it won't show UI
    }
}