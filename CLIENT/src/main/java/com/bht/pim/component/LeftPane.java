package com.bht.pim.component;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.menu.LeftMenuFragment;
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
@DeclarativeView(id = LeftPane.ID, name = "LeftPane",
        initialTargetLayoutId = LeftPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/component/LeftPane.fxml")
public class LeftPane extends BaseComponent {

    public static final String ID = "idcLeft";
    public static final String CONTAINER = "PLeft";

    @Resource
    private Context context;
    @FXML
    private VBox leftPane;

    @Override
    protected void initComponent(FXComponentLayout layout) {
        componentContext = context;
    }

    @Override
    protected void loadFragments() {
        ManagedFragmentHandler<LeftMenuFragment> leftMenuFragment =
                context.getManagedFragmentHandler(LeftMenuFragment.class);
        leftPane.getChildren().add(leftMenuFragment.getFragmentNode());
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
