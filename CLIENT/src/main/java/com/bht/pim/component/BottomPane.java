package com.bht.pim.component;


import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.supplementary.ErrorHandlingFragment;
import com.bht.pim.fragment.supplementary.LoginFragment;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

/**
 * @author bht
 */
@Getter
@Setter
@View(id = BottomPane.ID, name = "BottomPane",
        initialTargetLayoutId = BottomPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class BottomPane extends BaseComponent {

    public static final String ID = "idcBottom";
    public static final String CONTAINER = "PBottom";

    private ManagedFragmentHandler<LoginFragment> loginFragment;
    private ManagedFragmentHandler<ErrorHandlingFragment> errorHandlingFragment;

    @Resource
    private Context context;
    @FXML
    private VBox loginPane;
    @FXML
    private VBox errorPane;

    @Override
    protected void initComponent() {
        componentContext = context;
    }

    @Override
    protected void initLayout() {
        setAlignment(Pos.CENTER);
        setMinSize(1024, 600);
        setPrefSize(1280, 600);
    }

    @Override
    protected void loadFragments() {
        loginFragment = context.getManagedFragmentHandler(LoginFragment.class);
        errorHandlingFragment = context.getManagedFragmentHandler(ErrorHandlingFragment.class);

        loginPane = (VBox) loginFragment.getFragmentNode();
        errorPane = (VBox) errorHandlingFragment.getFragmentNode();

        getChildren().add(errorPane);
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
        if (message.getMessageBody() instanceof Throwable) {

            LOGGER.info("[SHOW] FXSupplementaryFragment: " + ErrorHandlingFragment.ID);
            getChildren().clear();
            getChildren().add(errorPane);
            errorHandlingFragment.getController()
                    .setDetail((Throwable) message.getMessageBody());

        } else {

            LOGGER.info("[SHOW] FXSupplementaryFragment: " + LoginFragment.ID);
            getChildren().clear();
            getChildren().add(loginPane);
        }
        return this;
    }
}
