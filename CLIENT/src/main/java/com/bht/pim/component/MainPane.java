package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.label.MainLabel;
import com.bht.pim.fragment.project.ProjectList;
import com.bht.pim.message.PimMessage;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
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

@Log4j
@Getter
@Setter
@DeclarativeView(id = AppConfiguration.COMPONENT_MAIN,
        name = "MainPane",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_MAIN,
        viewLocation = "/com/bht/pim/component/MainPane.fxml")
public class MainPane implements FXComponent {
    @FXML
    private VBox mainPane;

    @Resource
    private Context context;

    @Resource
    private ResourceBundle bundle;

    private ManagedFragmentHandler<MainLabel> labelFragment;
    private ManagedFragmentHandler mainFragment;

    @Override
    public Node handle(Message<Event, Object> message) throws Exception {
        return null;
    }

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) throws Exception {

        log.info("Node: " + node);
        log.info("Message: " + message);

        Node xNode = null;
        Object messageBody = message.getMessageBody();

        if (messageBody instanceof PimMessage) {
            log.info("[PIM Message] " + messageBody.getClass().getSimpleName());
            xNode = ((PimMessage) messageBody).postHandle(node, this);
        }

        return xNode;
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {

        labelFragment = context.getManagedFragmentHandler(MainLabel.class);
        mainFragment = context.getManagedFragmentHandler(ProjectList.class);

        mainPane.getChildren().add(labelFragment.getFragmentNode());
        mainPane.getChildren().add(mainFragment.getFragmentNode());

        mainPane.prefWidthProperty().bind(layout.getGlassPane().widthProperty().subtract(227));
        mainPane.prefHeightProperty().bind(layout.getGlassPane().heightProperty().subtract(100));
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout arg0) {
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        log.info("[SHOW] FXComponentLayout: " + componentLayout + " in: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        log.info("[HIDE] FXComponentLayout: " + componentLayout + " in: " + context.getId());
    }


}
