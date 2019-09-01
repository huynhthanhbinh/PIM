package com.bht.pim.fragment.parent.project;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.base.ParentFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmBox;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.project.ProjectDetail;
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
@Fragment(id = ProjectInfo.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public class ProjectInfo extends ParentFragment implements IdentifierNeeding {

    static final String ID = "idfPInfo";
    static final String LABEL = "label.project.info";

    private MainLabel mainLabel;
    private ProjectDetail projectDetail;
    private ConfirmBox confirmBox;

    @Resource
    private Context context;

    @Override
    protected void onCreated() {
        LOGGER.info("[Project Info] On init scene\n");
        PimUtil.alignPane(this, context);
    }

    @Override
    protected void getChildrenFragments(List<ChildFragment> children) {
        mainLabel = (MainLabel) children.get(0);
        projectDetail = (ProjectDetail) children.get(1);
        confirmBox = (ConfirmBox) children.get(2);
    }

    @Override
    protected void configureEachChildFragment() {
        mainLabel.setLabelText(LABEL);
        confirmBox.setLabelConfirm(ConfirmBox.LABEL_CONFIRM_MODIFY);
        confirmBox.setLabelCancel(ConfirmBox.LABEL_CONFIRM_RETURN);
    }

    @Override
    protected void bindChildrenFragments() {
        confirmBox.setOnSubmit(projectDetail::onModify);
        confirmBox.setOnCancel(projectDetail::onReturn);
    }

    @Override
    public boolean getObjectWithIdentifier(long id) {
        return projectDetail.getProjectById(id);
    }
}