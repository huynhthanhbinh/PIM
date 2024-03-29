package com.bht.pim.fragment.children.project;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseFragment;
import com.bht.pim.component.MainPane;
import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.fragment.parent.project.ProjectInfoFragment;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.fragment.parent.project.ProjectUpdateFragment;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.message.impl.IdentifierSending;
import com.bht.pim.notification.JFXNotificationType;
import com.bht.pim.property.FormatProperty;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.service.GroupService;
import com.bht.pim.service.ProjectService;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.NotificationUtil;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * @author bht
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@Fragment(id = ProjectDetailFragment.ID,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectDetailFragment.fxml")
public final class ProjectDetailFragment extends BaseFragment {

    static final String ID = "projectDetailFragment";
    private ProjectDto projectDto;
    @Autowired
    private LanguageProperty languageProperty;
    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private GroupService groupService;
    @Resource
    private Context context;

    @FXML
    private VBox mainPane;
    @FXML
    private Label lProjectMember;
    @FXML
    private Label lSize;
    @FXML
    private Label lNumber;
    @FXML
    private Label lBasicInfo;
    @FXML
    private Label lProjectNumber;
    @FXML
    private Label lStatus;
    @FXML
    private Label lProjectName;
    @FXML
    private Label lCustomer;
    @FXML
    private Label lGroup;
    @FXML
    private Label lLeader;
    @FXML
    private Label lStartDate;
    @FXML
    private Label lEndDate;
    @FXML
    private TextField number;
    @FXML
    private TextField status;
    @FXML
    private TextField name;
    @FXML
    private TextField customer;
    @FXML
    private TextField group;
    @FXML
    private TextField leader;
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<EmployeeDto> table;
    @FXML
    private TableColumn<EmployeeDto, EmployeeDto> cName;

    @Override
    public void onCreated() {
        // for i18n / multilingual
        initAllLabels();

        // disable all fields
        disableAllFields();

        // init table
        configureTableMember();

        // set format for date
        setDateStringFormat();
    }

    @Override
    protected void configLayout() {
        layout = mainPane;
    }

    @Override
    protected void onSwitch() {
        number.setText(String.valueOf(projectDto.getNumber()));
        status.textProperty().bind(projectDto.getStatus());
        name.setText(projectDto.getName());
        customer.setText(projectDto.getCustomer());
        group.setText(String.valueOf(projectDto.getGroup().getId()));
        leader.setText(groupService.getGroupById(projectDto.getGroup().getId()).getLeader().getVisa());
        start.setValue(projectDto.getStart());
        end.setValue(projectDto.getEnd());
        table.getItems().addAll(projectDto.getMembers());
    }

    @Override
    protected void preLeft() {
        number.clear();
        status.clear();
        name.clear();
        customer.clear();
        group.clear();
        leader.clear();
        start.setValue(null);
        end.setValue(null);
        table.getItems().clear();
        projectDto = null;
    }

    @Override
    protected void bindChildren() {
        //
    }

    public boolean getProjectById(long projectId) {
        projectDto = projectService.getProjectById(projectId);
        return projectDto.getId() > 0;
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(lBasicInfo.textProperty(), "label.project.form.basicinfo");
        LanguageUtil.initLabel(lProjectMember.textProperty(), "label.project.form.projectmembers");
        LanguageUtil.initLabel(lNumber.textProperty(), "label.project.form.numberofmember");
        LanguageUtil.initLabel(lProjectNumber.textProperty(), "label.project.form.projectnumber");
        LanguageUtil.initLabel(lStatus.textProperty(), "label.project.form.status");
        LanguageUtil.initLabel(lProjectName.textProperty(), "label.project.form.projectname");
        LanguageUtil.initLabel(lCustomer.textProperty(), "label.project.form.customer");
        LanguageUtil.initLabel(lGroup.textProperty(), "label.project.form.group");
        LanguageUtil.initLabel(lLeader.textProperty(), "label.project.form.leader");
        LanguageUtil.initLabel(lStartDate.textProperty(), "label.project.form.startdate");
        LanguageUtil.initLabel(lEndDate.textProperty(), "label.project.form.enddate");

        cName.setText(languageProperty.getResourceBundleProperty()
                .get().getString("label.project.form.membername"));

        languageProperty.getResourceBundleProperty()
                .addListener((observable, oldValue, newValue) ->
                        cName.setText(newValue.getString("label.project.form.membername")));
    }

    private void disableAllFields() {
        number.setDisable(true);
        status.setDisable(true);
        name.setDisable(true);
        customer.setDisable(true);
        group.setDisable(true);
        leader.setDisable(true);
        start.setDisable(true);
        end.setDisable(true);
    }

    // Config table member, CellValueFactory / CellFactory
    private void configureTableMember() {
        cName.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cName.prefWidthProperty().bind(table.widthProperty().subtract(20));
        cName.setResizable(false);

        table.getItems().addListener((ListChangeListener<EmployeeDto>) change ->
                lSize.setText(String.valueOf(table.getItems().size())));
    }

    // set converter for date-format
    private void setDateStringFormat() {
        start.converterProperty().bind(FormatProperty.DATE_STRING_CONVERTER);
        end.converterProperty().bind(FormatProperty.DATE_STRING_CONVERTER);
    }


    public void onModify(MouseEvent mouseEvent) {
        if (projectDto.getStatus().equals(statusMapper.toGuiStatus("FIN"))) {
            NotificationUtil.showNotification(
                    JFXNotificationType.ERROR,
                    Pos.CENTER,
                    "Cannot edit project which has already been\"Finished\"!");
            return;
        }

        LOGGER.info("[INFO] on modify project");

        IdentifierSending sending = new IdentifierSending(
                ProjectListFragment.class,
                ProjectUpdateFragment.class,
                projectDto.getId());

        context.send(MainPane.ID, sending);

        FragmentSwitching switching = new FragmentSwitching(
                ProjectInfoFragment.class,
                ProjectUpdateFragment.class);

        context.send(MainPane.ID, switching);
    }

    public void onReturn(MouseEvent mouseEvent) {
        LOGGER.info("[INFO] on return back to project list");

        FragmentSwitching fragmentSwitching = new FragmentSwitching(
                ProjectInfoFragment.class,
                ProjectListFragment.class);

        context.send(MainPane.ID, fragmentSwitching);
    }
}