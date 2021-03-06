package com.bht.pim.fragment.menu;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.component.MainPane;
import com.bht.pim.fragment.parent.project.ProjectDashboardFragment;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.util.LanguageUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author bht
 */
@Fragment(id = LeftMenuFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/menu/LeftMenuFragment.fxml")
public final class LeftMenuFragment extends BaseComponentFragment {

    static final String ID = "leftMenuFragment";

    private static final String LABEL_LEFT_PROJECT_DASHBOARD = "label.pim.left.project.dashboard";
    private static final String LABEL_LEFT_PROJECT_LIST = "label.pim.left.project.list";

    @Resource
    private Context context;
    @FXML
    private Label lDashboard;
    @FXML
    private Label lProjectList;


    @Override
    protected void registerChildren() {
        //
    }

    @Override
    protected void configLayout() {
        //
    }

    @Override
    protected void bindChildren() {
        //
    }


    @Override
    protected void onCreated() {
        initAllLabels();
        initAllStyles();
        addAllEventListeners();
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(lDashboard.textProperty(), LABEL_LEFT_PROJECT_DASHBOARD);
        LanguageUtil.initLabel(lProjectList.textProperty(), LABEL_LEFT_PROJECT_LIST);
    }

    private void initAllStyles() {
        lDashboard.getStyleClass().add("clickable");
        lProjectList.getStyleClass().add("clickable");
        lDashboard.getStyleClass().add("active");
    }

    private void addAllEventListeners() {
        lDashboard.setOnMouseClicked(this::onMouseClickedDashboard);
        lProjectList.setOnMouseClicked(this::onMouseClickedProjectList);
    }

    private void onMouseClickedProjectList(MouseEvent mouseEvent) {
        if (lProjectList.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                ProjectDashboardFragment.class,
                ProjectListFragment.class);

        context.send(MainPane.ID, switching);

        lDashboard.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lProjectList.getStyleClass().add("active");
    }

    private void onMouseClickedDashboard(MouseEvent mouseEvent) {
        if (lDashboard.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                ProjectListFragment.class,
                ProjectDashboardFragment.class);

        context.send(MainPane.ID, switching);

        lProjectList.getStyleClass().remove("active");
        lDashboard.getStyleClass().remove("active");
        lDashboard.getStyleClass().add("active");
    }

    public void onShowed() {
        lProjectList.getStyleClass().remove("active");
        lDashboard.getStyleClass().remove("active");
        lDashboard.getStyleClass().add("active");
    }
}