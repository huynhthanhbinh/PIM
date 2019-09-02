package com.bht.pim.component;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmFragment;
import com.bht.pim.fragment.children.label.MainLabelFragment;
import com.bht.pim.fragment.children.pagination.PaginationFragment;
import com.bht.pim.fragment.children.project.ProjectDetailFragment;
import com.bht.pim.fragment.children.project.ProjectEditFormFragment;
import com.bht.pim.fragment.children.project.ProjectTableFragment;
import com.bht.pim.fragment.children.project.ProjectUtilFragment;
import com.bht.pim.fragment.parent.IdentifierNeeding;
import com.bht.pim.fragment.parent.SuccessNeeding;
import com.bht.pim.fragment.parent.project.ProjectCreateFragment;
import com.bht.pim.fragment.parent.project.ProjectInfoFragment;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.fragment.parent.project.ProjectUpdateFragment;
import com.bht.pim.message.PimMessage;
import com.bht.pim.util.PimUtil;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import java.util.Arrays;

/**
 * @author bht
 */
@Getter
@Setter
@SuppressWarnings("unchecked")
@View(id = MainPane.ID, name = "MainPane",
        initialTargetLayoutId = MainPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class MainPane extends BaseComponent {

    public static final String ID = "idcMain";
    public static final String CONTAINER = "PMain";

    @Resource
    private Context context;

    private ManagedFragmentHandler<ProjectListFragment> projectListFragment;
    private ManagedFragmentHandler<ProjectCreateFragment> projectCreateFragment;
    private ManagedFragmentHandler<ProjectUpdateFragment> projectUpdateFragment;
    private ManagedFragmentHandler<ProjectInfoFragment> projectInfoFragment;

    @Override
    protected void initComponent() {
        componentContext = context;
    }

    @Override
    protected void initLayout() {
        setMinSize(1024, 600);
        setPrefSize(1024, 600);
        PimUtil.alignPane(this, context);
    }

    @Override
    protected void loadFragments() {
        projectListFragment = context.getManagedFragmentHandler(ProjectListFragment.class);
        projectCreateFragment = context.getManagedFragmentHandler(ProjectCreateFragment.class);
        projectUpdateFragment = context.getManagedFragmentHandler(ProjectUpdateFragment.class);
        projectInfoFragment = context.getManagedFragmentHandler(ProjectInfoFragment.class);
    }

    @Override
    protected void createFragmentList() {
        fragments.add(projectListFragment.getController());
        fragments.add(projectCreateFragment.getController());
        fragments.add(projectUpdateFragment.getController());
        fragments.add(projectInfoFragment.getController());
    }

    @Override
    protected void assignChildren() {
        projectListFragment.getController().addAllChildren(Arrays.asList(new Pair[]{
                registerChildFragment(MainLabelFragment.class),
                registerChildFragment(ProjectUtilFragment.class),
                registerChildFragment(ProjectTableFragment.class),
                registerChildFragment(PaginationFragment.class)}));

        projectCreateFragment.getController().addAllChildren(Arrays.asList(new Pair[]{
                registerChildFragment(MainLabelFragment.class),
                registerChildFragment(ProjectEditFormFragment.class),
                registerChildFragment(ConfirmFragment.class)}));

        projectUpdateFragment.getController().addAllChildren(Arrays.asList(new Pair[]{
                registerChildFragment(MainLabelFragment.class),
                registerChildFragment(ProjectEditFormFragment.class),
                registerChildFragment(ConfirmFragment.class)}));

        projectInfoFragment.getController().addAllChildren(Arrays.asList(new Pair[]{
                registerChildFragment(MainLabelFragment.class),
                registerChildFragment(ProjectDetailFragment.class),
                registerChildFragment(ConfirmFragment.class)}));
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {
        return PimMessage.messageHandler(message, this);
    }

    public static void sendIdentifier(long id, MainPane mainPane, Class sender, Class receiver) {

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
        switchFragment(mainPane, ProjectListFragment.class);

        if (!AppConfiguration.LOGGED_IN_PROPERTY.get()) { // not logged-in or recently logout
            mainPane.getContext().send(AppConfiguration.PERSPECTIVE_DEFAULT, "show");
        }
    }
}