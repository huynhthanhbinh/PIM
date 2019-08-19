package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.confirm.ConfirmBox;
import com.bht.pim.fragment.label.MainLabel;
import com.bht.pim.fragment.project.*;
import com.bht.pim.message.PimMessage;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
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
@Controller
@SuppressWarnings("unchecked")
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

    private ManagedFragmentHandler mainFragment;
    private ManagedFragmentHandler<ProjectList> projectListFragment;
    private ManagedFragmentHandler<ProjectCreate> projectCreateFragment;


    private void loadFragments() {
        projectListFragment = context.getManagedFragmentHandler(ProjectList.class);
        projectCreateFragment = context.getManagedFragmentHandler(ProjectCreate.class);

        mainFragment = projectListFragment;
    }

    private void assignChildren() {
        projectListFragment.getController().addAllChildren(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectUtil.class),
                registerNewFragment(ProjectTable.class)});

        projectCreateFragment.getController().addAllChildren(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectEditForm.class),
                registerNewFragment(ConfirmBox.class)});
    }

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
    public static void switchFragment(MainPane mainPane, Class fragmentClazz) {
        ObservableList<Node> nodes = mainPane.getMainPane().getChildren();
        nodes.remove(nodes.get(0));

        mainPane.setMainFragment(mainPane.getContext()
                .getManagedFragmentHandler(fragmentClazz));
        nodes.add(mainPane.getMainFragment().getFragmentNode());
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {

        loadFragments();
        assignChildren();

        mainPane.getChildren().add(mainFragment.getFragmentNode());
        mainPane.prefWidthProperty().bind(layout.getGlassPane().widthProperty().subtract(227));
        mainPane.prefHeightProperty().bind(layout.getGlassPane().heightProperty().subtract(120));
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

    private <T> Pair<T, Node> registerNewFragment(Class<T> fragmentClass) {
        ManagedFragmentHandler<T> fragment = context.getManagedFragmentHandler(fragmentClass);
        return new Pair<>(fragment.getController(), fragment.getFragmentNode());
    }
}
