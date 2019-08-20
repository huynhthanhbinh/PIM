package com.bht.pim.fragment.parent.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.ConfirmBox;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.project.ProjectEditForm;
import com.bht.pim.fragment.parent.ChildrenContaining;
import com.bht.pim.fragment.parent.IdentifierNeeding;
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
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_UPDATE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/parent/project/ProjectUpdate.fxml")
public class ProjectUpdate implements Initializable, IdentifierNeeding, ChildrenContaining {

    private MainLabel mainLabel;
    private ProjectEditForm projectEditForm;
    private ConfirmBox confirmBox;

    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;
    @FXML
    private VBox mainPane;

    @Override
    public boolean getObjectWithIdentifier(long id) {
        return projectEditForm.getProjectById(id);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Project Update] On init scene ");
    }

    @Override
    public <T> void addAllChildren(Pair<T, Node>[] children) {
        for (Pair<T, Node> child : children) {
            mainPane.getChildren().add(child.getValue());
        }

        mainLabel = (MainLabel) children[0].getKey();
        projectEditForm = (ProjectEditForm) children[1].getKey();
        confirmBox = (ConfirmBox) children[2].getKey();

        mainLabel.setLabelText(AppConfiguration.LABEL_PROJECT_UPDATE);
        confirmBox.setLabelText("UPDATE");
        confirmBox.setOnSubmit(projectEditForm::onSubmit);
        confirmBox.setOnCancel(projectEditForm::onCancel);
    }

    @Override
    public void onSwitchParentFragment() {
        log.info("Switching fragment, new fragment: " + getClass().getSimpleName());

        // Create Project : false
        // Update Project : true
        projectEditForm.setIsUpdateState(true);

        mainLabel.onSwitchParentFragment();
        projectEditForm.onSwitchParentFragment();
        confirmBox.onSwitchParentFragment();
    }
}
