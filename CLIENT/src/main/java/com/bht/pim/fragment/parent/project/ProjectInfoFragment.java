package com.bht.pim.fragment.parent.project;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmFragment;
import com.bht.pim.fragment.children.label.MainLabelFragment;
import com.bht.pim.fragment.children.project.ProjectDetailFragment;
import com.bht.pim.fragment.parent.IdentifierNeeding;

/**
 * @author bht
 */
@Controller
@Fragment(id = ProjectInfoFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class ProjectInfoFragment extends BaseComponentFragment implements IdentifierNeeding {

    static final String ID = "idfPInfo";
    private static final String LABEL = "label.project.info";

    private ManagedFragmentHandler<MainLabelFragment> mainLabelFragment;
    private ManagedFragmentHandler<ProjectDetailFragment> projectDetailFragment;
    private ManagedFragmentHandler<ConfirmFragment> confirmFragment;

    @Resource
    private Context context;

    @Override
    protected void registerChildren() {
        mainLabelFragment = registerNewFragment(MainLabelFragment.class);
        projectDetailFragment = registerNewFragment(ProjectDetailFragment.class);
        confirmFragment = registerNewFragment(ConfirmFragment.class);
    }

    @Override
    protected void onCreated() {
        LOGGER.info("[INIT] FXParentFragment : " + ProjectInfoFragment.ID);
        mainLabelFragment.getController().setLabelText(LABEL);
        confirmFragment.getController().setLabelConfirm(ConfirmFragment.LABEL_CONFIRM_MODIFY);
        confirmFragment.getController().setLabelCancel(ConfirmFragment.LABEL_CONFIRM_RETURN);
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
        confirmFragment.getController().setOnSubmit(projectDetailFragment.getController()::onModify);
        confirmFragment.getController().setOnCancel(projectDetailFragment.getController()::onReturn);
    }

    @Override
    public boolean getObjectWithIdentifier(long id) {
        return projectDetailFragment.getController().getProjectById(id);
    }
}