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
import com.bht.pim.fragment.children.project.ProjectDetailFragment;
import com.bht.pim.fragment.parent.IdentifierNeeding;

/**
 *
 * @author bht
 */
@Controller
@Fragment(id = ProjectInfoFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/parent/common/MainFragment.fxml")
public final class ProjectInfoFragment extends BaseComponentFragment implements IdentifierNeeding {

    static final String ID = "idfPInfo";
    private static final String LABEL = "label.project.info";

    private MainLabelFragment mainLabelFragment;
    private ProjectDetailFragment projectDetailFragment;
    private ConfirmFragment confirmFragment;

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
        LOGGER.info("[INIT] FXComponentFragment : " + ProjectInfoFragment.ID);
        mainLabelFragment.setLabelText(LABEL);
        confirmFragment.setLabelConfirm(ConfirmFragment.LABEL_CONFIRM_MODIFY);
        confirmFragment.setLabelCancel(ConfirmFragment.LABEL_CONFIRM_RETURN);
    }

    @Override
    protected void configLayout() {
        // layout.doSomething
    }

    @Override
    protected void bindChildren() {
        confirmFragment.setOnSubmit(projectDetailFragment::onModify);
        confirmFragment.setOnCancel(projectDetailFragment::onReturn);
    }

    @Override
    public boolean getObjectWithIdentifier(long id) {
        return projectDetailFragment.getProjectById(id);
    }
}