package com.bht.pim.component;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.menu.TopMenuFragment;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

/**
 * @author bht
 */
@Controller
@DeclarativeView(id = TopPane.ID, name = "TopPane",
        initialTargetLayoutId = TopPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/component/TopPane.fxml")
public class TopPane extends BaseComponent {

    public static final String ID = "idcTop";
    public static final String CONTAINER = "PTop";

    @Resource
    private Context context;
    @FXML
    private VBox topPane;

    @Override
    protected void initComponent(FXComponentLayout layout) {
        componentContext = context;

        topPane.prefWidthProperty().bind(
                layout.getGlassPane().widthProperty().subtract(20));
    }

    @Override
    protected void loadFragments() {
        ManagedFragmentHandler<TopMenuFragment> topMenuFragment =
                context.getManagedFragmentHandler(TopMenuFragment.class);
        topPane.getChildren().add(topMenuFragment.getFragmentNode());
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
        return null;
    }
}
