package com.bht.pim.fragment.parent.project;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.base.ParentFragment;
import com.bht.pim.configuration.AppConfiguration;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author bht
 */
@Controller
@Fragment(id = ProjectDashboardFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class ProjectDashboardFragment extends ParentFragment {

    static final String ID = "idfPDashboard";
    static final String LABEL = "label.project.dashboard";

    @Resource
    private Context context;

    @Override
    protected void onCreated() {
        LOGGER.info("[INIT] FXParentFragment : " + ProjectUpdateFragment.ID);
    }

    @Override
    protected void getChildrenFragments(List<ChildFragment> children) {
        // ...
    }

    @Override
    protected void configureEachChildFragment() {
        // ...
    }

    @Override
    protected void bindChildrenFragments() {
        // ...
    }
}
