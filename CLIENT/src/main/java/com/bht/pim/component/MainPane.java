package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.employee.EmployeeInfo;
import com.bht.pim.fragment.employee.EmployeeList;
import com.bht.pim.fragment.group.GroupInfo;
import com.bht.pim.fragment.group.GroupList;
import com.bht.pim.fragment.project.ProjectCreate;
import com.bht.pim.fragment.project.ProjectInfo;
import com.bht.pim.fragment.project.ProjectList;
import com.bht.pim.fragment.project.ProjectUpdate;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
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
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import java.util.ResourceBundle;

@Log4j
@DeclarativeView(id = AppConfiguration.COMPONENT_MAIN,
        name = "MainPane",
        resourceBundleLocation = "bundles.languageBundle",
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_MAIN,
        viewLocation = "/com/bht/pim/component/MainPane.fxml")
public class MainPane implements FXComponent {

    @FXML
    private AnchorPane mainPane;

    @Resource
    private Context context;

    @Resource
    private ResourceBundle bundle;

    @Override
    public Node handle(Message<Event, Object> message) throws Exception {
        return null;
    }

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) throws Exception {

        log.info(message);

        switch (message.getMessageBody().toString()) {

            case AppConfiguration.FRAGMENT_PROJECT_LIST:
                alterFragment(ProjectList.class);
                break;

            case AppConfiguration.FRAGMENT_PROJECT_INFO:
                alterFragment(ProjectInfo.class);
                break;

            case AppConfiguration.FRAGMENT_PROJECT_CREATE:
                alterFragment(ProjectCreate.class);
                break;

            case AppConfiguration.FRAGMENT_PROJECT_UPDATE:
                alterFragment(ProjectUpdate.class);
                break;

            case AppConfiguration.FRAGMENT_GROUP_LIST:
                alterFragment(GroupList.class);
                break;

            case AppConfiguration.FRAGMENT_GROUP_INFO:
                alterFragment(GroupInfo.class);
                break;

            case AppConfiguration.FRAGMENT_EMPLOYEE_LIST:
                alterFragment(EmployeeList.class);
                break;

            case AppConfiguration.FRAGMENT_EMPLOYEE_INFO:
                alterFragment(EmployeeInfo.class);
                break;

            default:
                break;
        }

        return null;
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {

        ManagedFragmentHandler<ProjectList> fragment = context
                .getManagedFragmentHandler(ProjectList.class);

        mainPane.getChildren().add(fragment.getFragmentNode());

        mainPane.prefWidthProperty().bind(layout.getGlassPane().widthProperty().subtract(227));
        mainPane.prefHeightProperty().bind(layout.getGlassPane().heightProperty().subtract(100));
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout arg0) {
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        log.info("[SHOW] FXComponentLayout: " + componentLayout + " in: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        log.info("[HIDE] FXComponentLayout: " + componentLayout + " in: " + context.getId());
    }

    @SuppressWarnings("unchecked")
    private void alterFragment(Class fragment) {
        mainPane.getChildren().remove(mainPane.getChildren().get(0));
        mainPane.getChildren().add(context
                .getManagedFragmentHandler(fragment).getFragmentNode());
    }
}
