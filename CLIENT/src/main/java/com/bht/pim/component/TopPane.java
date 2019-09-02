package com.bht.pim.component;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.menu.TopMenuFragment;
import javafx.event.Event;
import javafx.scene.Node;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

/**
 * @author bht
 */
@Controller
@View(id = TopPane.ID, name = "TopPane",
        initialTargetLayoutId = TopPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class TopPane extends BaseComponent {

    public static final String ID = "idcTop";
    public static final String CONTAINER = "PTop";

    @Resource
    private Context context;

    @Override
    protected void initComponent() {
        componentContext = context;
    }

    @Override
    protected void initLayout() {
        setPrefSize(1260, 100);
        setMinHeight(100);
        setMaxHeight(100);
    }

    @Override
    protected void loadFragments() {
        ManagedFragmentHandler<TopMenuFragment> topMenuFragment =
                context.getManagedFragmentHandler(TopMenuFragment.class);
        getChildren().add(topMenuFragment.getFragmentNode());
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
        return this;
    }
}
