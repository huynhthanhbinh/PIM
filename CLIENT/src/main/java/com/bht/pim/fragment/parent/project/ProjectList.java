package com.bht.pim.fragment.parent.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.label.MainLabelContaining;
import com.bht.pim.fragment.children.pagination.PimPagination;
import com.bht.pim.fragment.children.project.ProjectListTable;
import com.bht.pim.fragment.children.project.ProjectListUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_LIST,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/parent/project/ProjectList.fxml")
public class ProjectList implements Initializable, MainLabelContaining {

    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;
    @FXML
    private VBox mainPane;

    private ManagedFragmentHandler<MainLabel> mainLabelFragment;
    private ManagedFragmentHandler<ProjectListUtil> projectListUtilFragment;
    private ManagedFragmentHandler<ProjectListTable> projectListTableFragment;
    private ManagedFragmentHandler<PimPagination> paginationFragment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainLabelFragment = context.getManagedFragmentHandler(MainLabel.class);
        projectListUtilFragment = context.getManagedFragmentHandler(ProjectListUtil.class);
        projectListTableFragment = context.getManagedFragmentHandler(ProjectListTable.class);
        paginationFragment = context.getManagedFragmentHandler(PimPagination.class);

        mainPane.setPrefWidth(1050);
        mainPane.setPrefHeight(600);
        mainPane.setSpacing(5);
        mainPane.setPadding(new Insets(5));

        mainPane.getChildren().addAll(
                mainLabelFragment.getFragmentNode(),
                projectListUtilFragment.getFragmentNode(),
                projectListTableFragment.getFragmentNode(),
                paginationFragment.getFragmentNode());

        mainPane.getChildren().forEach(node -> VBox.setVgrow(node, Priority.ALWAYS));
    }

    @Override
    public void setMainLabelText(String mainLabelText) {
        mainLabelFragment.getController().setLabelText(mainLabelText);
    }
}
