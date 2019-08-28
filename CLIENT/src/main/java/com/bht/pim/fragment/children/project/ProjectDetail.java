package com.bht.pim.fragment.children.project;

import java.net.URL;
import java.util.ResourceBundle;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.fragment.children.ParentOwning;
import com.bht.pim.fragment.parent.project.ProjectInfo;
import com.bht.pim.fragment.parent.project.ProjectList;
import com.bht.pim.fragment.parent.project.ProjectUpdate;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.message.impl.IdentifierSending;
import com.bht.pim.notification.NotificationStyle;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.service.GroupService;
import com.bht.pim.service.ProjectService;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.NotificationUtil;
import com.bht.pim.util.PimUtil;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_DETAIL,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectDetail.fxml")
public class ProjectDetail implements Initializable, ParentOwning {

    private ProjectDto projectDto;

    private LanguageProperty languageProperty = AppConfiguration.LANGUAGE_PROPERTY;

    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private PimUtil pimUtil;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private GroupService groupService;
    @Resource
    private Context context;

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
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Project Detail] Initialization");

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
    public void onSwitchParentFragment() {
        number.setText(String.valueOf(projectDto.getNumber()));
        status.textProperty().bind(projectDto.getStatus());
        name.setText(projectDto.getName());
        customer.setText(projectDto.getCustomer());
        group.setText(String.valueOf(projectDto.getGroup().getId()));
        leader.setText(groupService.getGroupById(projectDto.getGroup().getId()).getLeader().toString());
        start.setValue(projectDto.getStart());
        end.setValue(projectDto.getEnd());
        table.getItems().clear();
        table.getItems().addAll(projectDto.getMembers());
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
        start.setConverter(pimUtil.dateStringConverter);
        end.setConverter(pimUtil.dateStringConverter);
    }


    public void onModify(MouseEvent mouseEvent) {
        if (projectDto.getStatus().equals(statusMapper.toGuiStatus("FIN"))) {
            NotificationUtil.showNotification(
                    NotificationStyle.ERROR,
                    Pos.CENTER,
                    "Cannot edit project which has already been\"Finished\"!");
            return;
        }

        log.info("[PIM] on modify project");

        IdentifierSending sending = new IdentifierSending(
                ProjectList.class,
                ProjectUpdate.class,
                projectDto.getId());

        context.send(AppConfiguration.COMPONENT_MAIN, sending);

        FragmentSwitching switching = new FragmentSwitching(
                ProjectInfo.class,
                ProjectUpdate.class);

        context.send(AppConfiguration.COMPONENT_MAIN, switching);
    }

    public void onReturn(MouseEvent mouseEvent) {
        log.info("[PIM] on return back to project list");

        FragmentSwitching fragmentSwitching = new FragmentSwitching(
                ProjectInfo.class,
                ProjectList.class);

        context.send(AppConfiguration.COMPONENT_MAIN, fragmentSwitching);
    }
}