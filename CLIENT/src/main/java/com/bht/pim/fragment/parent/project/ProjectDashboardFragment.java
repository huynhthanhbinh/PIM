package com.bht.pim.fragment.parent.project;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.label.MainLabelFragment;
import com.bht.pim.fragment.children.project.ProjectPanelFragment;

/**
 * @author bht
 */
@Controller
@Fragment(id = ProjectDashboardFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class ProjectDashboardFragment extends BaseComponentFragment {

    static final String ID = "idfPDashboard";
    static final String LABEL = "label.project.dashboard";

    private ManagedFragmentHandler<MainLabelFragment> mainLabelFragment;
    private ManagedFragmentHandler<ProjectPanelFragment> projectPanelFragment;

    @Resource
    private Context context;

    @Override
    protected void onCreated() {
        LOGGER.info("[INIT] FXParentFragment : " + ProjectDashboardFragment.ID);

        mainLabelFragment = registerNewFragment(MainLabelFragment.class);
        projectPanelFragment = registerNewFragment(ProjectPanelFragment.class);

        mainLabelFragment.getController().setLabelText(LABEL);
    }

    @Override
    protected void configLayout() {

    }

    @Override
    protected void onSwitch() {

    }

    @Override
    protected void preLeft() {

    }

    @Override
    protected void bindChildren() {

    }
}
