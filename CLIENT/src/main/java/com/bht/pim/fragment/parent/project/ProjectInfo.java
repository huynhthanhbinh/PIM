package com.bht.pim.fragment.parent.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmBox;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.project.ProjectDetail;
import com.bht.pim.fragment.parent.ChildrenContaining;
import com.bht.pim.fragment.parent.IdentifierNeeding;
import com.bht.pim.util.PimUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_INFO,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/parent/project/ProjectInfo.fxml")
public class ProjectInfo implements Initializable, ChildrenContaining, IdentifierNeeding {

    private MainLabel mainLabel;
    private ProjectDetail projectDetail;
    private ConfirmBox confirmBox;

    @Resource
    private Context context;
    @FXML
    private VBox mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Project Info] On init scene\n");
        PimUtil.alignPane(mainPane, context);
    }

    @Override
    public <T> void addAllChildren(Pair<T, Node>[] children) {
        for (Pair<T, Node> child : children) {
            mainPane.getChildren().add(child.getValue());
        }

        mainLabel = (MainLabel) children[0].getKey();
        projectDetail = (ProjectDetail) children[1].getKey();
        confirmBox = (ConfirmBox) children[2].getKey();

        mainLabel.setLabelText(AppConfiguration.LABEL_PROJECT_INFO);
        confirmBox.setLabelConfirm(AppConfiguration.LABEL_CONFIRM_MODIFY);
        confirmBox.setLabelCancel(AppConfiguration.LABEL_CONFIRM_RETURN);
        confirmBox.setOnSubmit(projectDetail::onModify);
        confirmBox.setOnCancel(projectDetail::onReturn);
    }

    @Override
    public void onSwitchParentFragment() {
        log.info("Switching fragment, new fragment: " + getClass().getSimpleName());
        mainLabel.onSwitchParentFragment();
        projectDetail.onSwitchParentFragment();
        confirmBox.onSwitchParentFragment();
    }

    @Override
    public boolean getObjectWithIdentifier(long id) {
        return projectDetail.getProjectById(id);
    }
}
