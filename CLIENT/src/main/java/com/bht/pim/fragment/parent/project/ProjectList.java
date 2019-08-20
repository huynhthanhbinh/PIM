package com.bht.pim.fragment.parent.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.pagination.PimPagination;
import com.bht.pim.fragment.children.project.ProjectTable;
import com.bht.pim.fragment.children.project.ProjectUtil;
import com.bht.pim.fragment.parent.ChildrenContaining;
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
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_LIST,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/parent/project/ProjectList.fxml")
public class ProjectList implements Initializable, ChildrenContaining {

    private MainLabel mainLabel;
    private ProjectUtil projectUtil;
    private ProjectTable projectTable;
    private PimPagination pagination;

    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;
    @FXML
    private VBox mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Project List] On init scene ");
    }

    @Override
    public final <T> void addAllChildren(Pair<T, Node>[] children) {
        for (Pair<T, Node> child : children) {
            mainPane.getChildren().add(child.getValue());
        }

        mainLabel = (MainLabel) children[0].getKey();
        projectUtil = (ProjectUtil) children[1].getKey();
        projectTable = (ProjectTable) children[2].getKey();
        pagination = (PimPagination) children[3].getKey();

        mainLabel.setLabelText(AppConfiguration.LABEL_PROJECT_LIST);
    }

    @Override
    public void onSwitchParentFragment() {
        log.info("Switching fragment, new fragment: " + getClass().getSimpleName());

        mainLabel.onSwitchParentFragment();
        projectUtil.onSwitchParentFragment();
        projectTable.onSwitchParentFragment();
        pagination.onSwitchParentFragment();
    }
}
