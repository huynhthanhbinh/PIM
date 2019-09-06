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
import com.bht.pim.fragment.children.project.ProjectEditFormFragment;

/**
 * @author bht
 */
@Controller
@Fragment(id = ProjectCreateFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class ProjectCreateFragment extends BaseComponentFragment {

    static final String ID = "idfPCreate";
    private static final String LABEL = "label.project.create";

    private ManagedFragmentHandler<MainLabelFragment> mainLabelFragment;
    private ManagedFragmentHandler<ProjectEditFormFragment> projectEditFormFragment;
    private ManagedFragmentHandler<ConfirmFragment> confirmFragment;

    @Resource
    private Context context;

    @Override
    protected void registerChildren() {
        mainLabelFragment = registerNewFragment(MainLabelFragment.class);
        projectEditFormFragment = registerNewFragment(ProjectEditFormFragment.class);
        confirmFragment = registerNewFragment(ConfirmFragment.class);
    }

    @Override
    protected void onCreated() {
        LOGGER.info("[INIT] FXParentFragment : " + ProjectCreateFragment.ID);
        projectEditFormFragment.getController().setIsUpdateState(false);
        mainLabelFragment.getController().setLabelText(LABEL);
        confirmFragment.getController().setLabelConfirm(ConfirmFragment.LABEL_CONFIRM_CREATE);
        confirmFragment.getController().setLabelCancel(ConfirmFragment.LABEL_CONFIRM_CANCEL);
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
        confirmFragment.getController().setOnSubmit(projectEditFormFragment.getController()::onSubmit);
        confirmFragment.getController().setOnCancel(projectEditFormFragment.getController()::onCancel);
    }
}