package com.bht.pim.component;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.base.ParentFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmBox;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.pagination.PimPagination;
import com.bht.pim.fragment.children.project.ProjectDetail;
import com.bht.pim.fragment.children.project.ProjectEditForm;
import com.bht.pim.fragment.children.project.ProjectTable;
import com.bht.pim.fragment.children.project.ProjectUtil;
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

import java.util.Arrays;
import java.util.ResourceBundle;

@Getter
@Setter
@SuppressWarnings("unchecked")
@DeclarativeView(id = MainPane.ID, name = "MainPane",
        initialTargetLayoutId = MainPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/component/MainPane.fxml")
public class MainPane extends BaseComponent implements FXComponent {

    public static final String ID = "idcMain";
    public static final String CONTAINER = "PMain";

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
        projectListFragment.getController().addAllChildren(Arrays.asList(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectUtil.class),
                registerNewFragment(ProjectTable.class),
                registerNewFragment(PimPagination.class)}));

        projectCreateFragment.getController().addAllChildren(Arrays.asList(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectEditForm.class),
                registerNewFragment(ConfirmBox.class)}));

        projectUpdateFragment.getController().addAllChildren(Arrays.asList(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectEditForm.class),
                registerNewFragment(ConfirmBox.class)}));

        projectInfoFragment.getController().addAllChildren(Arrays.asList(new Pair[]{
                registerNewFragment(MainLabel.class),
                registerNewFragment(ProjectDetail.class),
                registerNewFragment(ConfirmBox.class)}));
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

        PimUtil.alignPane(mainPane, context);
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

    private <T> Pair<T, Node> registerNewFragment(Class<T> fragmentClass) {
        ManagedFragmentHandler<T> fragment = context.getManagedFragmentHandler(fragmentClass);
        return new Pair<>(fragment.getController(), fragment.getFragmentNode());
    }

    public static void switchFragment(MainPane mainPane, Class fragmentClazz) {
        ObservableList<Node> nodes = mainPane.getMainPane().getChildren();
        nodes.clear();

        ManagedFragmentHandler target = mainPane.getContext()
                .getManagedFragmentHandler(fragmentClazz);

        ((ParentFragment) target.getController()).onSwitchParentFragment();

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

        LOGGER.info(success);

        ((SuccessNeeding) mainPane
                .getContext()
                .getManagedFragmentHandler(sender)
                .getController())
                .getSuccessProperty()
                .set(success);
    }

    public static void onShowPerspective(MainPane mainPane) {
        switchFragment(mainPane, ProjectList.class);

        if (!AppConfiguration.LOGGED_IN_PROPERTY.get()) { // not logged-in or recently logout
            mainPane.getContext().send(AppConfiguration.PERSPECTIVE_DEFAULT, "show");
        }
    }
}
