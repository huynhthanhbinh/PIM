package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.confirm.Confirm;
import com.bht.pim.fragment.confirm.Confirmable;
import com.bht.pim.fragment.label.MainLabel;
import com.bht.pim.fragment.project.ProjectList;
import com.bht.pim.message.PimMessage;
import javafx.collections.ObservableList;
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
import org.springframework.stereotype.Controller;

import java.util.ResourceBundle;

@Log4j
@Getter
@Setter
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
@Controller
@SuppressWarnings("unchecked")
=======
>>>>>>> parent of 142f52d... JACP
=======
>>>>>>> parent of 142f52d... JACP
=======
>>>>>>> parent of 142f52d... JACP
=======
>>>>>>> parent of 142f52d... JACP
=======
>>>>>>> parent of 142f52d... JACP
@DeclarativeView(id = AppConfiguration.COMPONENT_MAIN, name = "MainPane",
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

    private ManagedFragmentHandler<Confirm> confirmFragment;
    private ManagedFragmentHandler<MainLabel> labelFragment;
    private ManagedFragmentHandler mainFragment;

    @Override
    public Node handle(Message<Event, Object> message) {
        return null;
    }

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) {
        Node xNode = null;

        if (message.isMessageBodyTypeOf(PimMessage.class)) {
            PimMessage messageBody = (PimMessage) message.getMessageBody();

            log.info("[PIM Message] " + messageBody.getClass().getSimpleName() +
                    " >>> sent from: " + messageBody.getIdFragmentSent());

            xNode = messageBody.postHandle(node, this);
        }

        return xNode;
    }

    @FXML
    @SuppressWarnings("unchecked")
    public static void switchFragment(MainPane mainPane, Class fragmentClazz) {
        ObservableList<Node> nodes = mainPane.getMainPane().getChildren();

        // remove all pane except label pane
        for (int i = nodes.size() - 1; i > 0; i--) {
            nodes.remove(nodes.get(i));
        }

        mainPane.setMainFragment(mainPane.getContext()
                .getManagedFragmentHandler(fragmentClazz));
        nodes.add(mainPane.getMainFragment().getFragmentNode());
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {

        labelFragment = context.getManagedFragmentHandler(MainLabel.class);
        mainFragment = context.getManagedFragmentHandler(ProjectList.class);

        mainPane.getChildren().add(labelFragment.getFragmentNode());
        mainPane.getChildren().add(mainFragment.getFragmentNode());
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 142f52d... JACP
=======
>>>>>>> parent of 142f52d... JACP

>>>>>>> parent of 142f52d... JACP
=======

>>>>>>> parent of 142f52d... JACP
=======

>>>>>>> parent of 142f52d... JACP
        mainPane.prefWidthProperty().bind(layout.getGlassPane().widthProperty().subtract(227));
        mainPane.prefHeightProperty().bind(layout.getGlassPane().heightProperty().subtract(120));
    }

    @FXML
    public void addConfirmBox(String newLabel) {
        // add fragment Confirm Box (OK-CANCEL)
        confirmFragment = context.getManagedFragmentHandler(Confirm.class);
        mainPane.getChildren().add(confirmFragment.getFragmentNode());

        // set label for submit button
        confirmFragment.getController().setLabelText(newLabel);

        // handle for submit button
        confirmFragment.getController().setOnSubmit(
                ((Confirmable) mainFragment.getController())::onSubmit);
        // handle for cancel button
        confirmFragment.getController().setOnCancel(
                ((Confirmable) mainFragment.getController())::onCancel);
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
