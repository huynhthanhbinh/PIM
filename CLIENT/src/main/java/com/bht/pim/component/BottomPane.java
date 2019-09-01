package com.bht.pim.component;


import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.supplementary.ErrorHandling;
import com.bht.pim.fragment.supplementary.Login;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import java.util.ResourceBundle;

@Getter
@Setter
@DeclarativeView(id = BottomPane.ID, name = "BottomPane",
        initialTargetLayoutId = BottomPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/component/BottomPane.fxml")
public class BottomPane extends BaseComponent implements FXComponent {

    public static final String ID = "idcBottom";
    public static final String CONTAINER = "PBottom";

    private ManagedFragmentHandler<Login> loginFragment;
    private ManagedFragmentHandler<ErrorHandling> errorHandlingFragment;

    @Resource
    private Context context;
    @FXML
    private VBox mainPane;
    @FXML
    private VBox loginPane;
    @FXML
    private VBox errorPane;

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) throws Exception {

        if (message.getMessageBody() instanceof Throwable) {

            LOGGER.info("[PIM] show error page");
            LOGGER.info(message.getMessageBody());
            mainPane.getChildren().clear();
            mainPane.getChildren().add(errorPane);
            errorHandlingFragment.getController()
                    .setDetail((Throwable) message.getMessageBody());

        } else {

            LOGGER.info("[PIM] show login page");
            mainPane.getChildren().clear();
            mainPane.getChildren().add(loginPane);
        }

        return null;
    }

    @Override
    public Node handle(Message<Event, Object> message) throws Exception {
        return null;
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout arg0,
                                 final ResourceBundle resourceBundle) {

        loginFragment = context.getManagedFragmentHandler(Login.class);
        errorHandlingFragment = context.getManagedFragmentHandler(ErrorHandling.class);

        loginPane = (VBox) loginFragment.getFragmentNode();
        errorPane = (VBox) errorHandlingFragment.getFragmentNode();

        mainPane.getChildren().add(errorPane);
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout componentLayout) {
        LOGGER.info("[DESTROY] FXComponentLayout: " + context.getId());
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        LOGGER.info("[SHOW] FXComponentLayout: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        LOGGER.info("[HIDE] FXComponentLayout: " + context.getId());
    }
}
