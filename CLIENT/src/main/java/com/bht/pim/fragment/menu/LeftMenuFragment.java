package com.bht.pim.fragment.menu;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.component.LeftPane;
import com.bht.pim.component.MainPane;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.parent.project.ProjectDashboardFragment;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.util.LanguageUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j;

/**
 * @author bht
 */
@Log4j
@Fragment(id = LeftMenuFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/menu/LeftMenuFragment.fxml")
public final class LeftMenuFragment extends BaseComponentFragment {

    static final String ID = "idfMenuLeft";

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
    protected void onCreated() {
        log.info("[INIT] FXMenuFragment: " + getClass().getSimpleName());
        LanguageUtil.initLabel(lDashboard.textProperty(), LABEL_LEFT_PROJECT_DASHBOARD);
        LanguageUtil.initLabel(lProjectList.textProperty(), LABEL_LEFT_PROJECT_LIST);

        lDashboard.getStyleClass().add("clickable");
        lProjectList.getStyleClass().add("clickable");
        lDashboard.getStyleClass().add("active");

        lDashboard.setOnMouseClicked(this::onMouseClickedDashboard);
        lProjectList.setOnMouseClicked(this::onMouseClickedProjectList);
    }

    @Override
    protected void configLayout() {
        //
    }

    @Override
    protected void bindChildren() {
        //
    }

    private void onMouseClickedProjectList(MouseEvent mouseEvent) {
        log.info("[MENU] Clicked Project List");

        if (lProjectList.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                LeftPane.class,
                ProjectListFragment.class);

        context.send(MainPane.ID, switching);

        lDashboard.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lProjectList.getStyleClass().add("active");
    }

    private void onMouseClickedDashboard(MouseEvent mouseEvent) {
        log.info("[MENU] Clicked Group List");

        if (lDashboard.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                LeftPane.class,
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
