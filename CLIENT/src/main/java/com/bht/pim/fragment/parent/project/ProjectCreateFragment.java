package com.bht.pim.fragment.parent.project;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
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
    static final String LABEL = "label.project.create";

    private MainLabelFragment mainLabelFragment;
    private ProjectEditFormFragment projectEditFormFragment;
    private ConfirmFragment confirmFragment;

    @Resource
    private Context context;

    @Override
    protected void onCreated() {
        LOGGER.info("[INIT] FXParentFragment : " + ProjectCreateFragment.ID);
    }

//    @Override
//    protected void getChildrenFragments(List<ChildFragment> children) {
//        mainLabelFragment = (MainLabelFragment) children.get(0);
//        projectEditFormFragment = (ProjectEditFormFragment) children.get(1);
//        confirmFragment = (ConfirmFragment) children.get(2);
//    }
//
//    @Override
//    protected void configureEachChildFragment() {
//        // Create Project : false
//        // Update Project : true
//        projectEditFormFragment.setIsUpdateState(false);
//        mainLabelFragment.setLabelText(LABEL);
//        confirmFragment.setLabelConfirm(ConfirmFragment.LABEL_CONFIRM_CREATE);
//        confirmFragment.setLabelCancel(ConfirmFragment.LABEL_CONFIRM_CANCEL);
//    }
//
//    @Override
//    protected void bindChildrenFragments() {
//        confirmFragment.setOnSubmit(projectEditFormFragment::onSubmit);
//        confirmFragment.setOnCancel(projectEditFormFragment::onCancel);
//    }


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