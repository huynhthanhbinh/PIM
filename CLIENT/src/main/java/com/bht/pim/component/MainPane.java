package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmBox;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.pagination.PimPagination;
import com.bht.pim.fragment.children.project.ProjectDetail;
import com.bht.pim.fragment.children.project.ProjectEditForm;
import com.bht.pim.fragment.children.project.ProjectTable;
import com.bht.pim.fragment.children.project.ProjectUtil;
import com.bht.pim.fragment.parent.ChildrenContaining;
import com.bht.pim.fragment.parent.IdentifierNeeding;
import com.bht.pim.fragment.parent.SuccessNeeding;
import com.bht.pim.fragment.parent.project.ProjectCreate;
import com.bht.pim.fragment.parent.project.ProjectInfo;
import com.bht.pim.fragment.parent.project.ProjectList;
import com.bht.pim.fragment.parent.project.ProjectUpdate;
import com.bht.pim.message.PimMessage;
import com.bht.pim.util.PimUtil;
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

import java.util.ResourceBundle;

@Log4j
@Getter
@Setter
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

    private ManagedFragmentHandler currentFragment;
    private ManagedFragmentHandler<ProjectList> projectListFragment;
    private ManagedFragmentHandler<ProjectCreate> projectCreateFragment;
    private ManagedFragmentHandler<ProjectUpdate> projectUpdateFragment;
    private ManagedFragmentHandler<ProjectInfo> projectInfoFragment;


    private void loadFragments() {
        projectListFragment = context.getManagedFragmentHandler(ProjectList.class);
        projectCreateFragment = context.getManagedFragmentHandler(ProjectCreate.class);
        projectUpdateFragment = context.getManagedFragmentHandler(ProjectUpdate.class);
        projectInfoFragment = context.getManagedFragmentHandler(ProjectInfo.class);
    }

    private void assignChildren() {
        projectListFragment.getController().addAllChildren(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectUtil.class),
                registerNewFragment(ProjectTable.class),
                registerNewFragment(PimPagination.class)});

        projectCreateFragment.getController().addAllChildren(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectEditForm.class),
                registerNewFragment(ConfirmBox.class)});

        projectUpdateFragment.getController().addAllChildren(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectEditForm.class),
                registerNewFragment(ConfirmBox.class)});

        projectInfoFragment.getController().addAllChildren(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectDetail.class)});
    }

    @Override
    public Node handle(Message<Event, Object> message) {
        return null;
    }

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) {
        return PimMessage.messageHandler(node, message, this);
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {

        loadFragments();
        assignChildren();

        switchFragment(this, ProjectList.class);
        PimUtil.alignPane(mainPane, context);
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

    public static void switchFragment(MainPane mainPane, Class fragmentClazz) {
        ObservableList<Node> nodes = mainPane.getMainPane().getChildren();
        nodes.clear();

        ManagedFragmentHandler target = mainPane.getContext()
                .getManagedFragmentHandler(fragmentClazz);

        ((ChildrenContaining) target.getController()).onSwitchParentFragment();

        mainPane.setCurrentFragment(target);
        nodes.add(mainPane.getCurrentFragment().getFragmentNode());
    }

    public static void sendIdentifier(long id, MainPane mainPane,
                                      Class sender, Class receiver) {

        boolean success = ((IdentifierNeeding) mainPane
                .getContext()
                .getManagedFragmentHandler(receiver)
                .getController())
                .getObjectWithIdentifier(id);

        log.info("\n\n" + success + "\n\n");

        ((SuccessNeeding) mainPane
                .getContext()
                .getManagedFragmentHandler(sender)
                .getController())
                .getSuccessProperty()
                .set(success);
    }

    public static void onShowPerspective(MainPane mainPane) {
        if (mainPane.getCurrentFragment() != null) {
            ((ChildrenContaining) mainPane.getCurrentFragment().getController())
                    .onSwitchParentFragment();
        }
    }
}
