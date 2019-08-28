package com.bht.pim.component;


import java.util.ResourceBundle;

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

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.supplementary.ErrorHandling;
import com.bht.pim.fragment.supplementary.Login;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@DeclarativeView(id = AppConfiguration.COMPONENT_BOTTOM, name = "BottomPane",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_BOTTOM,
        viewLocation = "/com/bht/pim/component/BottomPane.fxml")
public class BottomPane implements FXComponent {

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

            log.info("[PIM] show error page");
            log.info(message.getMessageBody());
            mainPane.getChildren().clear();
            mainPane.getChildren().add(errorPane);
            errorHandlingFragment.getController()
                    .setDetail((Throwable) message.getMessageBody());

        } else {

            log.info("[PIM] show login page");
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
        log.info("[DESTROY] FXComponentLayout: " + context.getId());
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        log.info("[SHOW] FXComponentLayout: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        log.info("[HIDE] FXComponentLayout: " + context.getId());
    }
}
