package com.bht.pim.fragment.menu;

import com.bht.pim.component.LeftPane;
import com.bht.pim.component.MainPane;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.parent.project.ProjectDashboardFragment;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.util.LanguageUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author bht
 */
@Log4j
@Controller
@Fragment(id = LeftMenuFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/menu/LeftMenuFragment.fxml")
public class LeftMenuFragment implements Initializable {

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
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[INIT] FXMenuFragment: " + ID);
        LanguageUtil.initLabel(lDashboard.textProperty(), LABEL_LEFT_PROJECT_DASHBOARD);
        LanguageUtil.initLabel(lProjectList.textProperty(), LABEL_LEFT_PROJECT_LIST);

        lDashboard.getStyleClass().add("clickable");
        lProjectList.getStyleClass().add("clickable");
        lProjectList.getStyleClass().add("active");

        lDashboard.setOnMouseClicked(this::onMouseClickedDashboard);
        lProjectList.setOnMouseClicked(this::onMouseClickedProjectList);
    }


    private void onMouseClickedProjectList(MouseEvent mouseEvent) {
        log.info("[LeftPane] Clicked Project List");

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
        log.info("[LeftPane] Clicked Group List");

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
}
