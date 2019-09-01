package com.bht.pim.component;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.parent.project.ProjectList;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.util.LanguageUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

@Controller
@DeclarativeView(id = LeftPane.ID, name = "LeftPane",
        initialTargetLayoutId = LeftPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/component/LeftPane.fxml")
public class LeftPane extends BaseComponent {

    public static final String ID = "idcLeft";
    public static final String CONTAINER = "PLeft";

    @Resource
    private Context context;
    @FXML
    private Label lProjectList;
    @FXML
    private Label lGroupList;
    @FXML
    private Label lEmployeeList;

    @Override
    protected void initComponent(FXComponentLayout layout) {
        componentContext = context;

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

    @Override
    protected void loadFragments() {
        // ...
    }

    @Override
    protected void createFragmentList() {
        // ...
    }

    @Override
    protected void assignChildren() {
        // ...
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {
        return null;
    }

    private void onMouseClickedProjectList(MouseEvent mouseEvent) {
        LOGGER.info("[LeftPane] Clicked Project List");

        if (lProjectList.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                LeftPane.class,
                ProjectList.class);

        componentContext.send(MainPane.ID, switching);

        lEmployeeList.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lGroupList.getStyleClass().remove("active");
        lProjectList.getStyleClass().add("active");
    }

    private void onMouseClickedGroupList(MouseEvent mouseEvent) {
        LOGGER.info("[LeftPane] Clicked Group List");

        if (lGroupList.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                LeftPane.class,
                ProjectList.class);

        componentContext.send(MainPane.ID, switching);

        lEmployeeList.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lGroupList.getStyleClass().remove("active");
        lGroupList.getStyleClass().add("active");
    }

    private void onMouseClickedEmployeeList(MouseEvent mouseEvent) {
        LOGGER.info("[LeftPane] Clicked Employee List");

        if (lEmployeeList.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                LeftPane.class,
                ProjectList.class);

        componentContext.send(MainPane.ID, switching);

        lEmployeeList.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lGroupList.getStyleClass().remove("active");
        lEmployeeList.getStyleClass().add("active");
    }
}
