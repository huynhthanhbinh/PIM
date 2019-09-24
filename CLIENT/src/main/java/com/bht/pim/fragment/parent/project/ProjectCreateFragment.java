package com.bht.pim.fragment.parent.project;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.configuration.SpringConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmFragment;
import com.bht.pim.fragment.children.label.MainLabelFragment;
import com.bht.pim.fragment.children.project.ProjectEditFormFragment;

/**
 *
 * @author bht
 */
@Fragment(id = ProjectCreateFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = SpringConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/parent/common/MainFragment.fxml")
public final class ProjectCreateFragment extends BaseComponentFragment {

    static final String ID = "projectCreateFragment";
    private static final String LABEL = "label.project.create";

    private MainLabelFragment mainLabelFragment;
    private ProjectEditFormFragment projectEditFormFragment;
    private ConfirmFragment confirmFragment;

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
        projectEditFormFragment.setIsUpdateState(false);
        mainLabelFragment.setLabelText(LABEL);
        confirmFragment.setLabelConfirm(ConfirmFragment.LABEL_CONFIRM_CREATE);
        confirmFragment.setLabelCancel(ConfirmFragment.LABEL_CONFIRM_CANCEL);
    }

    @Override
    protected void configLayout() {
        // layout.doSomething
    }

    @Override
    protected void bindChildren() {
        confirmFragment.setOnSubmit(projectEditFormFragment::onSubmit);
        confirmFragment.setOnCancel(projectEditFormFragment::onCancel);
    }
}