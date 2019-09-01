package com.bht.pim.fragment.parent.project;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.base.ParentFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmBox;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.project.ProjectEditForm;
import com.bht.pim.fragment.parent.IdentifierNeeding;
import com.bht.pim.util.PimUtil;
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
@Fragment(id = ProjectUpdate.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public class ProjectUpdate extends ParentFragment implements IdentifierNeeding {

    static final String ID = "idfPUpdate";
    static final String LABEL = "label.project.update";

    private MainLabel mainLabel;
    private ProjectEditForm projectEditForm;
    private ConfirmBox confirmBox;

    @Resource
    private Context context;

    @Override
    public boolean getObjectWithIdentifier(long id) {
        return projectEditForm.getProjectById(id);
    }

    @Override
    protected void onCreated() {
        LOGGER.info("[Project Update] On init scene\n");
        PimUtil.alignPane(this, context);
    }

    @Override
    protected void getChildrenFragments(List<ChildFragment> children) {
        mainLabel = (MainLabel) children.get(0);
        projectEditForm = (ProjectEditForm) children.get(1);
        confirmBox = (ConfirmBox) children.get(2);
    }

    @Override
    protected void configureEachChildFragment() {
        // Create Project : false
        // Update Project : true
        projectEditForm.setIsUpdateState(true);
        mainLabel.setLabelText(LABEL);
        confirmBox.setLabelConfirm(ConfirmBox.LABEL_CONFIRM_UPDATE);
        confirmBox.setLabelCancel(ConfirmBox.LABEL_CONFIRM_CANCEL);
    }

    @Override
    protected void bindChildrenFragments() {
        confirmBox.setOnSubmit(projectEditForm::onSubmit);
        confirmBox.setOnCancel(projectEditForm::onCancel);
    }
}