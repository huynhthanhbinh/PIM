package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.parent.employee.EmployeeList;
import com.bht.pim.fragment.parent.group.GroupList;
import com.bht.pim.fragment.parent.project.ProjectList;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.util.LanguageUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.util.ResourceBundle;

@Log4j
@Controller
@DeclarativeView(id = AppConfiguration.COMPONENT_LEFT, name = "LeftPane",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_LEFT,
        viewLocation = "/com/bht/pim/component/LeftPane.fxml")
public class LeftPane implements FXComponent {

    @FXML
    private Label lProjectList;
    @FXML
    private Label lGroupList;
    @FXML
    private Label lEmployeeList;
    @Resource
    private Context context;

    @Override
    public Node handle(Message<Event, Object> message) {
        return null;
    }

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) {
        return null;
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout arg0,
                                 final ResourceBundle resourceBundle) {

        LanguageUtil.initLabel(lProjectList.textProperty(), AppConfiguration.LABEL_LEFT_LIST_PROJECT);
        LanguageUtil.initLabel(lGroupList.textProperty(), AppConfiguration.LABEL_LEFT_LIST_GROUP);
        LanguageUtil.initLabel(lEmployeeList.textProperty(), AppConfiguration.LABEL_LEFT_LIST_EMPLOYEE);

        lProjectList.getStyleClass().add("clickable");
        lGroupList.getStyleClass().add("clickable");
        lEmployeeList.getStyleClass().add("clickable");
        lProjectList.getStyleClass().add("active");

        lProjectList.setOnMouseClicked(this::onMouseClickedProjectList);
        lGroupList.setOnMouseClicked(this::onMouseClickedGroupList);
        lEmployeeList.setOnMouseClicked(this::onMouseClickedEmployeeList);
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout componentLayout) {
        log.info("[DESTROY] FXComponentLayout: " + context.getId());
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        log.info("[SHOW] FXComponentLayout: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        log.info("[HIDE] FXComponentLayout: " + context.getId());
    }

    private void onMouseClickedProjectList(MouseEvent mouseEvent) {
        log.info("[LeftPane] Clicked Project List");

        if (lProjectList.getStyleClass().contains("active")) {
            mouseEvent.consume();
            return;
        }

        FragmentSwitching switching = new FragmentSwitching(
                LeftPane.class,
                ProjectList.class);

        context.send(AppConfiguration.COMPONENT_MAIN, switching);

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
                GroupList.class);

        context.send(AppConfiguration.COMPONENT_MAIN, switching);

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
                EmployeeList.class);

        context.send(AppConfiguration.COMPONENT_MAIN, switching);

        lEmployeeList.getStyleClass().remove("active");
        lProjectList.getStyleClass().remove("active");
        lGroupList.getStyleClass().remove("active");
        lEmployeeList.getStyleClass().add("active");
    }
}
