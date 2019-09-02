package com.bht.pim.fragment.menu;

import com.bht.pim.component.LeftPane;
import com.bht.pim.component.MainPane;
import com.bht.pim.configuration.AppConfiguration;
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

    @Resource
    private Context context;
    @FXML
    private Label lProjectList;
    @FXML
    private Label lGroupList;
    @FXML
    private Label lEmployeeList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[INIT] FXMenuFragment: " + ID);
        LanguageUtil.initLabel(lProjectList.textProperty(), AppConfiguration.LABEL_LEFT_PROJECT_LIST);
        LanguageUtil.initLabel(lGroupList.textProperty(), AppConfiguration.LABEL_LEFT_PROJECT_LIST);
        LanguageUtil.initLabel(lEmployeeList.textProperty(), AppConfiguration.LABEL_LEFT_PROJECT_LIST);

        lProjectList.getStyleClass().add("clickable");
        lGroupList.getStyleClass().add("clickable");
        lEmployeeList.getStyleClass().add("clickable");
        lProjectList.getStyleClass().add("active");

        lProjectList.setOnMouseClicked(this::onMouseClickedProjectList);
        lGroupList.setOnMouseClicked(this::onMouseClickedGroupList);
        lEmployeeList.setOnMouseClicked(this::onMouseClickedEmployeeList);
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

        lEmployeeList.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lGroupList.getStyleClass().remove("active");
        lProjectList.getStyleClass().add("active");
    }

    private void onMouseClickedGroupList(MouseEvent mouseEvent) {
        log.info("[LeftPane] Clicked Group List");

        if (lGroupList.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                LeftPane.class,
                ProjectListFragment.class);

        context.send(MainPane.ID, switching);

        lEmployeeList.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lGroupList.getStyleClass().remove("active");
        lGroupList.getStyleClass().add("active");
    }

    private void onMouseClickedEmployeeList(MouseEvent mouseEvent) {
        log.info("[LeftPane] Clicked Employee List");

        if (lEmployeeList.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                LeftPane.class,
                ProjectListFragment.class);

        context.send(MainPane.ID, switching);

        lEmployeeList.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lGroupList.getStyleClass().remove("active");
        lEmployeeList.getStyleClass().add("active");
    }
}
