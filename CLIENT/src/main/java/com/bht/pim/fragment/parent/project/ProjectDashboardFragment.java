package com.bht.pim.fragment.parent.project;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.fragment.children.label.MainLabelFragment;
import com.bht.pim.fragment.children.project.ProjectPanelFragment;

/**
 *
 * @author bht
 */
@Fragment(id = ProjectDashboardFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/parent/common/MainFragment.fxml")
public final class ProjectDashboardFragment extends BaseComponentFragment {

    static final String ID = "projectDashboardFragment";
    private static final String LABEL = "label.project.dashboard";

    private MainLabelFragment mainLabelFragment;
    private ProjectPanelFragment projectPanelFragment;

    @Resource
    private Context context;

    @Override
    protected void registerChildren() {
        mainLabelFragment = registerNewFragment(MainLabelFragment.class);
        projectPanelFragment = registerNewFragment(ProjectPanelFragment.class);
    }

    @Override
    protected void onCreated() {
        mainLabelFragment.setLabelText(LABEL);
    }

    @Override
    protected void configLayout() {
        // layout.doSomething
    }

    @Override
    protected void bindChildren() {
        //
    }
}
