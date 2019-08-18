package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.Confirm;
import com.bht.pim.fragment.children.confirm.ConfirmBoxContaining;
import com.bht.pim.fragment.children.confirm.Confirmable;
import com.bht.pim.fragment.children.employee.EmployeeDetail;
import com.bht.pim.fragment.children.employee.EmployeeListTable;
import com.bht.pim.fragment.children.group.GroupDetail;
import com.bht.pim.fragment.children.group.GroupListTable;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.label.MainLabelContaining;
import com.bht.pim.fragment.children.pagination.PimPagination;
import com.bht.pim.fragment.children.project.ProjectDetail;
import com.bht.pim.fragment.children.project.ProjectEditableForm;
import com.bht.pim.fragment.children.project.ProjectListTable;
import com.bht.pim.fragment.children.project.ProjectListUtil;
import com.bht.pim.fragment.parent.ChildrenContainer;
import com.bht.pim.fragment.parent.employee.EmployeeInfo;
import com.bht.pim.fragment.parent.employee.EmployeeList;
import com.bht.pim.fragment.parent.group.GroupInfo;
import com.bht.pim.fragment.parent.group.GroupList;
import com.bht.pim.fragment.parent.project.ProjectCreate;
import com.bht.pim.fragment.parent.project.ProjectInfo;
import com.bht.pim.fragment.parent.project.ProjectList;
import com.bht.pim.fragment.parent.project.ProjectUpdate;
import com.bht.pim.message.PimMessage;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
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

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Log4j
@Getter
@Setter
@DeclarativeView(id = AppConfiguration.COMPONENT_MAIN, name = "MainPane",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_MAIN,
        viewLocation = "/com/bht/pim/component/MainPane.fxml")
public class MainPane implements FXComponent {

    private final Map<String, String> labels = labels();
    private final Map<String, ManagedFragmentHandler> fragments = fragments();

    @FXML
    private VBox mainPane;
    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;

    private ChildrenContainer childrenContainer;
    private ManagedFragmentHandler mainFragment;

    private ManagedFragmentHandler<EmployeeInfo> employeeInfoFragment;
    private ManagedFragmentHandler<EmployeeList> employeeListFragment;
    private ManagedFragmentHandler<GroupInfo> groupInfoFragment;
    private ManagedFragmentHandler<GroupList> groupListFragment;
    private ManagedFragmentHandler<ProjectCreate> projectCreateFragment;
    private ManagedFragmentHandler<ProjectUpdate> projectUpdateFragment;
    private ManagedFragmentHandler<ProjectInfo> projectInfoFragment;
    private ManagedFragmentHandler<ProjectList> projectListFragment;

    private ManagedFragmentHandler<Confirm> confirmFragment;
    private ManagedFragmentHandler<MainLabel> mainLabelFragment;
    private ManagedFragmentHandler<PimPagination> paginationFragment;


    private ManagedFragmentHandler<EmployeeDetail> employeeDetailFragment;
    private ManagedFragmentHandler<EmployeeListTable> employeeListTableFragment;


    private ManagedFragmentHandler<GroupDetail> groupDetailFragment;
    private ManagedFragmentHandler<GroupListTable> groupListTableFragment;


    private ManagedFragmentHandler<ProjectDetail> projectDetailFragment;
    private ManagedFragmentHandler<ProjectListTable> projectListTableFragment;
    private ManagedFragmentHandler<ProjectEditableForm> projectEditableFormFragment;
    private ManagedFragmentHandler<ProjectListUtil> projectListUtilFragment;


    @Override
    public Node handle(Message<Event, Object> message) {
        return null;
    }

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) {
        Node xNode = null;

        if (message.isMessageBodyTypeOf(PimMessage.class)) {
            PimMessage messageBody = (PimMessage) message.getMessageBody();

            log.info("[PIM Message] " + messageBody.getClass().getSimpleName() +
                    " >>> sent from: " + messageBody.getIdFragmentSent());

            xNode = messageBody.postHandle(node, this);
        }

        return xNode;
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {

        configureFragments();
        createChildrenContainer();
        initChildrenForFragments();

        mainFragment = projectListFragment;
        mainPane.getChildren().add(mainFragment.getFragmentNode());
        ((MainLabelContaining) mainFragment.getController())
                .setMainLabelText(AppConfiguration.LABEL_PROJECT_LIST);

        mainPane.prefWidthProperty().bind(layout.getGlassPane().widthProperty().subtract(227));
        mainPane.prefHeightProperty().bind(layout.getGlassPane().heightProperty().subtract(120));
    }

    private Map<String, String> labels() {
        Map<String, String> labelMap = new HashMap<>();
        labelMap.put(AppConfiguration.FRAGMENT_EMPLOYEE_INFO, AppConfiguration.LABEL_EMPLOYEE_INFO);
        labelMap.put(AppConfiguration.FRAGMENT_EMPLOYEE_LIST, AppConfiguration.LABEL_EMPLOYEE_LIST);
        labelMap.put(AppConfiguration.FRAGMENT_GROUP_INFO, AppConfiguration.LABEL_GROUP_INFO);
        labelMap.put(AppConfiguration.FRAGMENT_GROUP_LIST, AppConfiguration.LABEL_GROUP_LIST);
        labelMap.put(AppConfiguration.FRAGMENT_PROJECT_INFO, AppConfiguration.LABEL_PROJECT_INFO);
        labelMap.put(AppConfiguration.FRAGMENT_PROJECT_LIST, AppConfiguration.LABEL_PROJECT_LIST);
        labelMap.put(AppConfiguration.FRAGMENT_PROJECT_CREATE, AppConfiguration.LABEL_PROJECT_CREATE);
        labelMap.put(AppConfiguration.FRAGMENT_PROJECT_UPDATE, AppConfiguration.LABEL_PROJECT_UPDATE);
        return labelMap;
    }

    private Map<String, ManagedFragmentHandler> fragments() {
        Map<String, ManagedFragmentHandler> fragmentHandlerMap = new HashMap<>();
        fragmentHandlerMap.put(AppConfiguration.FRAGMENT_EMPLOYEE_INFO, employeeInfoFragment);
        fragmentHandlerMap.put(AppConfiguration.FRAGMENT_EMPLOYEE_LIST, employeeListFragment);
        fragmentHandlerMap.put(AppConfiguration.FRAGMENT_GROUP_INFO, groupInfoFragment);
        fragmentHandlerMap.put(AppConfiguration.FRAGMENT_GROUP_LIST, groupListFragment);
        fragmentHandlerMap.put(AppConfiguration.FRAGMENT_PROJECT_INFO, projectInfoFragment);
        fragmentHandlerMap.put(AppConfiguration.FRAGMENT_PROJECT_LIST, projectListFragment);
        fragmentHandlerMap.put(AppConfiguration.FRAGMENT_PROJECT_CREATE, projectCreateFragment);
        fragmentHandlerMap.put(AppConfiguration.FRAGMENT_PROJECT_UPDATE, projectUpdateFragment);
        return fragmentHandlerMap;
    }

    private void configureFragments() {
        employeeInfoFragment = context.getManagedFragmentHandler(EmployeeInfo.class);
        employeeListFragment = context.getManagedFragmentHandler(EmployeeList.class);
        groupInfoFragment = context.getManagedFragmentHandler(GroupInfo.class);
        groupListFragment = context.getManagedFragmentHandler(GroupList.class);
        projectListFragment = context.getManagedFragmentHandler(ProjectList.class);
        projectCreateFragment = context.getManagedFragmentHandler(ProjectCreate.class);
        projectUpdateFragment = context.getManagedFragmentHandler(ProjectUpdate.class);
        projectInfoFragment = context.getManagedFragmentHandler(ProjectInfo.class);
    }

    private void createChildrenContainer() {
        childrenContainer = ChildrenContainer.newBuilder()
                .confirmFragment(context.getManagedFragmentHandler(Confirm.class))
                .mainLabelFragment(context.getManagedFragmentHandler(MainLabel.class))
                .paginationFragment(context.getManagedFragmentHandler(PimPagination.class))
                .employeeDetailFragment(context.getManagedFragmentHandler(EmployeeDetail.class))
                .employeeListTableFragment(context.getManagedFragmentHandler(EmployeeListTable.class))
                .groupDetailFragment(context.getManagedFragmentHandler(GroupDetail.class))
                .groupListTableFragment(context.getManagedFragmentHandler(GroupListTable.class))
                .projectDetailFragment(context.getManagedFragmentHandler(ProjectDetail.class))
                .projectListTableFragment(context.getManagedFragmentHandler(ProjectListTable.class))
                .projectListUtilFragment(context.getManagedFragmentHandler(ProjectListUtil.class))
                .projectEditableFormFragment(context.getManagedFragmentHandler(ProjectEditableForm.class))
                .build();
    }

    private void initChildrenForFragments() {
        projectListFragment.getController().configureChildrenFragments(childrenContainer);
    }

    public void switchFragment(MainPane mainPane, String idFragmentTarget) {

        ObservableList<Node> nodes = mainPane.getMainPane().getChildren();
        nodes.remove(nodes.get(0));

        mainPane.setMainFragment(fragments.get(idFragmentTarget));
        nodes.add(mainPane.getMainFragment().getFragmentNode());

        ((MainLabelContaining) mainFragment.getController())
                .setMainLabelText(labels.get(idFragmentTarget));

        if (mainFragment.getController() instanceof ConfirmBoxContaining) {
            ConfirmBoxContaining parent = (ConfirmBoxContaining) mainFragment.getController();

            // set new label for parent fragment
            parent.setConfirmLabel(parent.getConfirmLabel());

            // handle for submit button
            ((Confirm) parent.getConfirmBox().getController()).setOnSubmit(
                    ((Confirmable) parent.getConfirmForm().getController())::onSubmit);

            // handle for cancel button
            ((Confirm) parent.getConfirmBox().getController()).setOnCancel(
                    ((Confirmable) parent.getConfirmForm().getController())::onCancel);
        }
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
}
