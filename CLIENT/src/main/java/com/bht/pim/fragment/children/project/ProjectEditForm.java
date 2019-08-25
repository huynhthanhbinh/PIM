package com.bht.pim.fragment.children.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.dto.GroupDto;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.fragment.children.ParentOwning;
import com.bht.pim.fragment.children.confirm.Confirmable;
import com.bht.pim.fragment.parent.project.ProjectList;
import com.bht.pim.mapper.DateTimeMapper;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.notification.NotificationStyle;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.service.EmployeeService;
import com.bht.pim.service.GroupService;
import com.bht.pim.service.ProjectService;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.NotificationUtil;
import com.bht.pim.util.PimUtil;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_EDIT_FORM,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectEditForm.fxml")
public class ProjectEditForm implements Initializable, Confirmable, ParentOwning {

    private ProjectDto projectDto;

    private LanguageProperty languageProperty = AppConfiguration.LANGUAGE_PROPERTY;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PimUtil projectUtil;
    @Autowired
    private DateTimeMapper dateTimeMapper;
    @Autowired
    private StatusMapper statusMapper;

    @FXML
    private Label lBasicInfo;
    @FXML
    private Label lFillAll;
    @FXML
    private Label lProjectMember;
    @FXML
    private Label lSize;
    @FXML
    private Label lNumber;
    @FXML
    private Label lProjectNumber;
    @FXML
    private Label lNumberExist;
    @FXML
    private Label lStatus;
    @FXML
    private Label lProjectName;
    @FXML
    private Label lNameEmpty;
    @FXML
    private Label lCustomer;
    @FXML
    private Label lCustomerEmpty;
    @FXML
    private Label lGroupOption;
    @FXML
    private Label lGroupOptionEmpty;
    @FXML
    private Label lLeader;
    @FXML
    private Label lLeaderChoice;
    @FXML
    private Label lStartDate;
    @FXML
    private Label lStartEmpty;
    @FXML
    private Label lEndDate;
    @FXML
    private Label lEndInvalid;

    @Resource
    private Context context;
    @FXML
    private VBox mainPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField textField;
    @FXML
    private TableView<EmployeeDto> table;
    @FXML
    private TableColumn<EmployeeDto, EmployeeDto> cName;
    @FXML
    private TableColumn<EmployeeDto, EmployeeDto> cRemove;
    @FXML
    private ComboBox<String> comboBoxStatus;
    @FXML
    private ComboBox<String> comboBoxOption;
    @FXML
    private ComboBox<EmployeeDto> comboBoxLeader;
    @FXML
    private TextField customer;
    @FXML
    private TextField name;
    @FXML
    private TextField number;
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;

    private boolean chose;
    private boolean current;
    private boolean isUpdate;
    private EmployeeDto leader;
    private List<Long> projectNumbers;
    private List<EmployeeDto> members; // member-of-this-projects
    private List<EmployeeDto> employees; // all-current-employees
    private List<EmployeeDto> leaders; // all-current-group-leaders
    private List<EmployeeDto> leaderOptions; // for-creating-new-group
    private AutoCompletionBinding<EmployeeDto> employeeAutoCompletion;


    @Override
    public void onSwitchParentFragment() {
        loadProjectEditForm();
    }

    public void setIsUpdateState(boolean isUpdateState) {
        isUpdate = isUpdateState;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Project Edit Form] Initialization");

        members = new ArrayList<>();
        employees = new ArrayList<>();
        leaders = new ArrayList<>();
        leaderOptions = new ArrayList<>();

        // init an instance for storing a project
        projectDto = ProjectDto.newBuilder().build();

        // for i18n / multilingual
        initAllLabels();

        // Init all inputs
        initAllInput();

        // Add all event-listener
        addAllEventListener();
    }

    // Hide all check-label
    private void hideAllCheckLabel() {
        lNumberExist.setVisible(false);
        lNameEmpty.setVisible(false);
        lCustomerEmpty.setVisible(false);
        lLeaderChoice.setVisible(false);
        lStartEmpty.setVisible(false);
        lEndInvalid.setVisible(false);
        lGroupOptionEmpty.setVisible(false);
        lFillAll.setVisible(false);
    }


    // Get all necessary data
    private void getNecessaryData() {
        // Get all exist project numbers
        projectNumbers = projectService.getProjectNumbers();

        // Get all employees
        employees = employeeService.getAllEmployees();

        // Get all current-group leaders
        leaders = groupService.getAllGroups().stream()
                .map(GroupDto::getLeader)
                .collect(Collectors.toList());

        // Init leader leaderOptions for option new groups
        employees.removeAll(leaders);

        chose = false;
        current = false;
        leader = null;
        members = new ArrayList<>();
        leaderOptions = new ArrayList<>(employees);
    }


    // Add all event-listener
    private void addAllEventListener() {

        // force the field to be numeric only
        number.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // input not number format
                number.setText(newValue
                        .replaceAll("[^\\d]", ""));

            } else if (!newValue.isEmpty()) { // input in correct format
                number.getStyleClass().remove("empty");
                lNumberExist.setVisible(projectNumbers
                        .contains(Long.valueOf(newValue)));
            }
        });

        // check-if the name field is empty or not
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            name.getStyleClass().remove("empty");
            lNameEmpty.setVisible(newValue.isEmpty());
        });

        // check-if the customer field is empty or not
        customer.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.getStyleClass().remove("empty");
            lCustomerEmpty.setVisible(newValue.isEmpty());
        });

        comboBoxOption.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    chose = true;
                    lGroupOptionEmpty.setVisible(false);

                    if (comboBoxLeader.getItems() != null) {
                        comboBoxLeader.getItems().clear();
                    }

                    if (!LanguageUtil
                            .getCurrentLabelOfKey("label.project.form.newgroup")
                            .equals(newValue)) { // current group

                        Objects.requireNonNull(comboBoxLeader.getItems()).addAll(leaders);
                        current = true;

                    } else { // new group (new a non-exist leader)
                        Objects.requireNonNull(comboBoxLeader.getItems()).addAll(leaderOptions);
                        current = false;
                    }

                    comboBoxLeader.getSelectionModel().selectFirst();
                    comboBoxLeader.setDisable(false);
                    textField.setDisable(false);
                }
        );
        comboBoxLeader.getSelectionModel().selectedItemProperty().addListener(
                this::leaderChoice);

        dateChangeListener(start);
        dateChangeListener(end);

        // Fixed table header, prevent from changing order of table column
        table.skinProperty().addListener((observable, oldSkin, newSkin) -> {
            TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            header.reorderingProperty().addListener((observable0, oldValue, newValue) ->
                    header.setReordering(false));
        });
    }

    private void initAllInput() {
        initComboBoxStatus();
        initComboBoxGroupOption();

        comboBoxLeader.setDisable(true);
        textField.setDisable(true);

        table.getItems().addAll(Collections.emptyList());
        configureTableMember(table);

        start.setPromptText("dd/MM/yyyy");
        end.setPromptText("dd/MM/yyyy");

        start.setConverter(projectUtil.dateStringConverter);
        end.setConverter(projectUtil.dateStringConverter);

        end.setDisable(true);
    }


    // Search employee auto-completion
    private void configureAutoCompletion() {
        employeeAutoCompletion = TextFields
                .bindAutoCompletion(textField, employees);

        employeeAutoCompletion.setMinWidth(300);
        employeeAutoCompletion.setHideOnEscape(true);
        employeeAutoCompletion.setOnAutoCompleted(event -> {

            EmployeeDto employeeDTO = event.getCompletion();

            textField.clear();
            table.getItems().add(employeeDTO);

            members.add(employeeDTO);
            log.info(members);

            // Update autocompletion list
            // remove the selected one
            employees.remove(employeeDTO);
            employeeAutoCompletion.dispose();
            configureAutoCompletion();
        });
    }


    // Config table member, CellValueFactory / CellFactory
    private void configureTableMember(TableView<EmployeeDto> tableView) {
        cName.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cName.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.85));
        cName.setResizable(false);

        cRemove.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.15));
        cRemove.setResizable(false);

        cRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cRemove.setCellFactory(this::remove);
        cRemove.setStyle("-fx-alignment: CENTER-RIGHT; -fx-border-insets: 5px;");

        tableView.getItems().addListener((ListChangeListener<EmployeeDto>) change ->
                lSize.setText(String.valueOf(tableView.getItems().size())));
    }


    // Button remove on each table row
    private TableCell<EmployeeDto, EmployeeDto> remove(TableColumn<EmployeeDto, EmployeeDto> param) {
        return new TableCell<EmployeeDto, EmployeeDto>() {
            private final Button bRemove = new Button("X");

            @Override
            protected void updateItem(EmployeeDto employeeDTO, boolean empty) {
                if (employeeDTO == null || employeeDTO == leader) {

                    // Not show the button
                    // to prevent user delete leader from group
                    setGraphic(null);
                    return;
                }

                // Show the button
                setGraphic(bRemove);

                bRemove.setOnAction(event -> {
                    // clear current row selection
                    table.getSelectionModel().clearSelection();

                    // remove this row from table
                    table.getItems().remove(employeeDTO);

                    // add this employeeDTO back to employeeDTO list
                    // for autocompletion next times
                    employees.add(employeeDTO);

                    // load auto-completion again
                    employeeAutoCompletion.dispose();
                    configureAutoCompletion();

                    // remove this id from the employeeDTO id list
                    members.remove(employeeDTO);

                    // log current list employeeDTO id
                    log.info(members);
                });
            }
        };
    }


    @Override
    public void onSubmit(MouseEvent event) {
        log.info("[bCreate] onClick");

        hideAllValidation();

        boolean emptyNumber = number.getText().isEmpty();
        boolean emptyName = name.getText().isEmpty();
        boolean emptyCustomer = name.getText().isEmpty();
        boolean emptyStart = start.getEditor().getText().isEmpty();
        boolean emptyEnd = end.getEditor().getText().isEmpty();
        boolean valid = chose && !(emptyNumber || emptyName || emptyCustomer || emptyStart);

        if (valid) {
            if (!emptyEnd && end.getValue().isBefore(start.getValue())) {

                end.getEditor().getStyleClass().add("empty");
                lEndInvalid.setVisible(true);
                return;
            }

            log.info("<<< PIM - On saving new project >>>");

            EmployeeDto groupLeader = EmployeeDto.newBuilder()
                    .id(leader.getId())
                    .build();

            GroupDto groupDto = GroupDto.newBuilder()
                    .leader(groupLeader)
                    .build();

            try {
                if (comboBoxOption.getValue()
                        .equals(LanguageUtil.getCurrentLabelOfKey("label.project.form.newgroup"))) {

                    if (saveNewGroup(groupDto)) {
                        NotificationUtil.showNotification(NotificationStyle.SUCCESS, Pos.CENTER,
                                "[PIM] Successfully create new group !");
                    } else {
                        NotificationUtil.showNotification(NotificationStyle.WARNING, Pos.CENTER,
                                "[PIM] Failed to create new group !");
                        return;
                    }
                }

                ProjectDto.Builder projectDtoBuilder = projectDto.toBuilder()
                        .number(Long.parseLong(number.getText()))
                        .name(name.getText())
                        .customer(customer.getText())
                        .group(groupDto)
                        .members(members)
                        .status(new SimpleStringProperty(comboBoxStatus.getValue()))
                        .start(start.getValue());

                if (end.getValue() != null) {
                    projectDtoBuilder.end(end.getValue());
                }

                NotificationUtil.showNotification(NotificationStyle.INFO, Pos.CENTER,
                        "[PIM] On saving project !");

                saveOrUpdateProject(projectDtoBuilder);

            } catch (Exception exception) {
                log.info(exception);
            }
        } else {
            warnOnInvalid(emptyNumber, emptyName, emptyCustomer, emptyStart);
        }
    }


    private void hideAllValidation() {
        lFillAll.setVisible(false);
        number.getStyleClass().remove("empty");
        name.getStyleClass().remove("empty");
        customer.getStyleClass().remove("empty");
        start.getEditor().getStyleClass().remove("empty");
        end.getEditor().getStyleClass().remove("empty");
        lGroupOptionEmpty.setVisible(false);
    }


    private void warnOnInvalid(boolean emptyNumber,
                               boolean emptyName,
                               boolean emptyCustomer,
                               boolean emptyStart) {
        lFillAll.setVisible(true);
        if (emptyNumber) {
            number.getStyleClass().add("empty");
        }
        if (emptyName) {
            name.getStyleClass().add("empty");
            lNameEmpty.setVisible(true);
        }
        if (emptyCustomer) {
            customer.getStyleClass().add("empty");
            lCustomerEmpty.setVisible(true);
        }
        if (emptyStart) {
            start.getEditor().getStyleClass().add("empty");
            lStartEmpty.setVisible(true);
        }
        if (!chose) {
            lGroupOptionEmpty.setVisible(true);
        }
    }


    private boolean saveNewGroup(GroupDto groupDto) {
        if (comboBoxOption.getSelectionModel().getSelectedItem()
                .equals(LanguageUtil.getCurrentLabelOfKey("label.project.form.newgroup"))) {

            // send group info to server to save
            log.info("<<< PIM - On creating new group >>>");
            return groupService.addNewGroup(groupDto);
        }
        return false;
    }


    @Override
    public void onCancel(MouseEvent event) {
        log.info("[bCancel] onClick");

        FragmentSwitching switching = new FragmentSwitching(
                ProjectEditForm.class,
                ProjectList.class);

        context.send(AppConfiguration.COMPONENT_MAIN, switching);
    }


    // when change leader choice
    private void leaderChoice(ObservableValue<? extends EmployeeDto> observable,
                              EmployeeDto oldValue, EmployeeDto newValue) {
        if (oldValue == null) { // change group option

            if (!current) { // new-group
                employees.remove(newValue);
            }

            members.add(newValue);

            // Add new-leader value to the table member
            leader = newValue;
            table.getItems().add(newValue);

        } else {
            // change leader option
            if (newValue != null && !newValue.equals(oldValue)) {

                if (!current) { // new-group
                    employees.add(oldValue);
                    employees.remove(newValue);
                }

                members.remove(oldValue);
                members.remove(newValue); // if exist before
                members.add(newValue); // add and mark as leader

                // if exist before but non-leader
                table.getItems().remove(newValue);

                // the same group option but differ in leader option
                leader = newValue;
                table.getItems().remove(oldValue);
                table.getItems().add(newValue);

            } else { // Change group-option

                if (!current) { // option is of preLeaders to Leader
                    employees.add(oldValue);
                }

                members.remove(oldValue);

                // Remove old-leader value out of table member
                table.getItems().remove(oldValue);
            }
        }

        log.info(members);

        // load auto-completion again
        employeeAutoCompletion.dispose();
        configureAutoCompletion();
    }


    private void dateChangeListener(DatePicker datePicker) {
        datePicker.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                datePicker.setValue(datePicker
                        .getConverter().fromString(datePicker.getEditor().getText()));

                datePicker.getEditor().getStyleClass().remove("empty");

                if (datePicker == start) {
                    lStartEmpty.setVisible(false);
                }
                if (end.getValue() != null && start.getValue() != null) {
                    if (end.getValue().isBefore(start.getValue())) {
                        end.getEditor().getStyleClass().add("empty");
                        lEndInvalid.setVisible(true);

                    } else {
                        end.getEditor().getStyleClass().remove("empty");
                        lEndInvalid.setVisible(false);
                    }
                } else {
                    end.getEditor().getStyleClass().remove("empty");
                    lEndInvalid.setVisible(false);
                }
            }
        });
    }

    public boolean getProjectById(long projectId) {
        projectDto = projectService.getProjectById(projectId);

        log.info(projectDto);

        return projectDto.getId() != 0;
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(lBasicInfo.textProperty(), "label.project.form.basicinfo");
        LanguageUtil.initLabel(lFillAll.textProperty(), "label.project.form.fillallfields");
        LanguageUtil.initLabel(lProjectMember.textProperty(), "label.project.form.projectmembers");
        LanguageUtil.initLabel(textField.promptTextProperty(), "label.project.form.searchmember");
        LanguageUtil.initLabel(lNumber.textProperty(), "label.project.form.numberofmember");
        LanguageUtil.initLabel(lProjectNumber.textProperty(), "label.project.form.projectnumber");
        LanguageUtil.initLabel(number.promptTextProperty(), "label.project.form.numberonly");
        LanguageUtil.initLabel(lNumberExist.textProperty(), "label.project.form.numberexist");
        LanguageUtil.initLabel(lStatus.textProperty(), "label.project.form.status");
        LanguageUtil.initLabel(lProjectName.textProperty(), "label.project.form.projectname");
        LanguageUtil.initLabel(name.promptTextProperty(), "label.project.form.inputname");
        LanguageUtil.initLabel(lNameEmpty.textProperty(), "label.project.form.nameempty");
        LanguageUtil.initLabel(lCustomer.textProperty(), "label.project.form.customer");
        LanguageUtil.initLabel(customer.promptTextProperty(), "label.project.form.inputcustomer");
        LanguageUtil.initLabel(lCustomerEmpty.textProperty(), "label.project.form.customerempty");
        LanguageUtil.initLabel(lGroupOption.textProperty(), "label.project.form.groupoption");
        LanguageUtil.initLabel(comboBoxOption.promptTextProperty(), "label.project.form.chooseoption");
        LanguageUtil.initLabel(lGroupOptionEmpty.textProperty(), "label.project.form.pleasechooseoption");
        LanguageUtil.initLabel(lLeader.textProperty(), "label.project.form.leader");
        LanguageUtil.initLabel(comboBoxLeader.promptTextProperty(), "label.project.form.chooseleader");
        LanguageUtil.initLabel(lLeaderChoice.textProperty(), "label.project.form.pleasechooseleader");
        LanguageUtil.initLabel(lStartDate.textProperty(), "label.project.form.startdate");
        LanguageUtil.initLabel(lStartEmpty.textProperty(), "label.project.form.specifystart");
        LanguageUtil.initLabel(lEndDate.textProperty(), "label.project.form.enddate");
        LanguageUtil.initLabel(lEndInvalid.textProperty(), "label.project.form.endinvalid");

        cName.setText(languageProperty.getResourceBundleProperty()
                .get().getString("label.project.form.membername"));

        languageProperty.getResourceBundleProperty()
                .addListener((observable, oldValue, newValue) ->
                        cName.setText(newValue.getString("label.project.form.membername")));
    }

    private void initComboBoxStatus() {
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("NEW").get());
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("PLA").get());
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("INP").get());
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("FIN").get());

        languageProperty.getResourceBundleProperty()
                .addListener((observable, oldValue, newValue) -> {
                    int index = comboBoxStatus.getSelectionModel().getSelectedIndex();
                    comboBoxStatus.getItems().clear();

                    comboBoxStatus.getItems().add(statusMapper.toGuiStatus("NEW").get());
                    comboBoxStatus.getItems().add(statusMapper.toGuiStatus("PLA").get());
                    comboBoxStatus.getItems().add(statusMapper.toGuiStatus("INP").get());
                    comboBoxStatus.getItems().add(statusMapper.toGuiStatus("FIN").get());

                    comboBoxStatus.getSelectionModel().select(index);
                });

        comboBoxStatus.getSelectionModel().selectFirst();
        comboBoxStatus.valueProperty().addListener((observable, oldValue, newValue) ->
                end.setDisable(!statusMapper
                        .toGuiStatus("FIN").get()
                        .equals(newValue)));
    }

    private void initComboBoxGroupOption() {
        ResourceBundle resourceBundle = languageProperty.getResourceBundleProperty().get();
        comboBoxOption.getItems().add(resourceBundle.getString("label.project.form.newgroup"));
        comboBoxOption.getItems().add(resourceBundle.getString("label.project.form.currentgroup"));

        languageProperty.getResourceBundleProperty()
                .addListener((observable, oldValue, newValue) -> {
                    int index = comboBoxOption.getSelectionModel().getSelectedIndex();
                    comboBoxOption.getItems().clear();

                    comboBoxOption.getItems().add(newValue.getString("label.project.form.newgroup"));
                    comboBoxOption.getItems().add(newValue.getString("label.project.form.currentgroup"));

                    comboBoxOption.getSelectionModel().select(index);
                });
    }

    private void emptyAllFields() {
        number.clear();
        name.clear();
        lNameEmpty.setVisible(false);
        customer.clear();
        lCustomerEmpty.setVisible(false);
        comboBoxStatus.getSelectionModel().selectFirst();
        comboBoxOption.getSelectionModel().clearSelection();
        comboBoxLeader.getSelectionModel().clearSelection();
        comboBoxLeader.setDisable(true);
        start.setValue(null);
        end.setValue(null);
        table.getItems().clear();
        members.clear();
        leaders.clear();
        leaderOptions.clear();
    }

    private void fillAllFieldsIfUpdate() {
        if (isUpdate) {
            number.setDisable(true);
            number.setText(String.valueOf(projectDto.getNumber()));
            lNumberExist.setVisible(false);
            name.setText(projectDto.getName());
            customer.setText(projectDto.getCustomer());
            comboBoxStatus.getSelectionModel().select(projectDto.getStatus().get());
            members = projectDto.getMembers();
            table.getItems().addAll(members);
            comboBoxOption.getSelectionModel().select(1);
            start.setValue(projectDto.getStart());
            end.setValue(projectDto.getEnd());
            if (!leader.equals(groupService.getGroupById(projectDto.getGroup().getId()).getLeader())) {
                table.getItems().remove(leader);
                leader = groupService.getGroupById(projectDto.getGroup().getId()).getLeader();
                comboBoxLeader.getSelectionModel().select(leader);
            }
        }
    }

    private void loadProjectEditForm() {
        // reset all back
        // to receive new record
        emptyAllFields();
        hideAllValidation();
        hideAllCheckLabel();

        // Get all necessary data from server
        getNecessaryData();
        configureAutoCompletion();
        fillAllFieldsIfUpdate();
    }

    private void saveOrUpdateProject(ProjectDto.Builder projectDtoBuilder) {
        projectDto = projectDtoBuilder.build();

        log.info(isUpdate);

        if (isUpdate) {
            updateProject(projectDto);
        } else {
            saveProject(projectDto);
        }
    }

    private void saveProject(ProjectDto projectDto) {
        if (projectService.addNewProject(projectDto)) {
            NotificationUtil.showNotification(NotificationStyle.SUCCESS, Pos.CENTER,
                    "[PIM] Successfully create project !");

            FragmentSwitching switching = new FragmentSwitching(
                    ProjectEditForm.class,
                    ProjectList.class);

            context.send(AppConfiguration.COMPONENT_MAIN, switching);

        } else {
            NotificationUtil.showNotification(NotificationStyle.WARNING, Pos.CENTER,
                    "[PIM] Failed to create new project !");

            loadProjectEditForm();
        }
    }

    private void updateProject(ProjectDto projectDto) {
        if (projectService.updateProject(projectDto)) {
            NotificationUtil.showNotification(NotificationStyle.SUCCESS, Pos.CENTER,
                    "[PIM] Successfully update project !");

            FragmentSwitching switching = new FragmentSwitching(
                    ProjectEditForm.class,
                    ProjectList.class);

            context.send(AppConfiguration.COMPONENT_MAIN, switching);

        } else {
            NotificationUtil.showNotification(NotificationStyle.WARNING, Pos.CENTER,
                    "[PIM] Failed to update project !");

            loadProjectEditForm();
        }
    }
}
