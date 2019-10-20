package com.bht.pim.component;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponent;
import com.bht.pim.base.BaseFragment;
import com.bht.pim.fragment.parent.IdentifierNeeding;
import com.bht.pim.fragment.parent.SuccessNeeding;
import com.bht.pim.fragment.parent.project.ProjectCreateFragment;
import com.bht.pim.fragment.parent.project.ProjectDashboardFragment;
import com.bht.pim.fragment.parent.project.ProjectInfoFragment;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.fragment.parent.project.ProjectUpdateFragment;
import com.bht.pim.message.PimMessage;
import com.bht.pim.perspective.DefaultPerspective;
import com.bht.pim.util.LayoutUtil;

import javafx.event.Event;
import javafx.scene.Node;
import lombok.Getter;

/**
 *
 * @author bht
 */
@View(id = MainPane.ID, name = MainPane.ID,
        initialTargetLayoutId = MainPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class MainPane extends BaseComponent {

    public static final String ID = "mainComponent";
    public static final String CONTAINER = "PMain";

    @Getter
    @Resource
    private Context context;

    @Override
    protected void initComponent() {
        componentContext = context;
    }

    @Override
    protected void initLayout() {
        setMinSize(1024, 600);
        setPrefSize(1024, 600);
        LayoutUtil.alignPane(this, context);
    }

    @Override
    protected void loadFragments() {
        registerComponentFragment(ProjectDashboardFragment.class);
        registerComponentFragment(ProjectListFragment.class);
        registerComponentFragment(ProjectCreateFragment.class);
        registerComponentFragment(ProjectUpdateFragment.class);
        registerComponentFragment(ProjectInfoFragment.class);
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {
        return PimMessage.handleMessage(message, this);
    }

    public static void sendIdentifier(long id, MainPane mainPane,
                                      Class<? extends BaseFragment> sender, Class<? extends BaseFragment> receiver) {
        boolean success = ((IdentifierNeeding) mainPane
                .getContext()
                .getManagedFragmentHandler(receiver)
                .getController())
                .getObjectWithIdentifier(id);

        ((SuccessNeeding) mainPane
                .getContext()
                .getManagedFragmentHandler(sender)
                .getController())
                .getSuccessProperty()
                .set(success);
    }

    public static void onShowPerspective(MainPane mainPane) {
        switchComponentFragment(mainPane, ProjectDashboardFragment.class);
        if (!AppConfiguration.LOGGED_IN_PROPERTY.get()) { // not logged-in or recently logout
            mainPane.getContext().send(DefaultPerspective.ID, "show");
        }
    }
}