package com.bht.pim.fragment.children.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.base.BaseFragment;
import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.dto.GroupDto;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.fragment.children.confirm.Confirmable;
import com.bht.pim.mapper.DateTimeMapper;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.property.FormatProperty;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.service.EmployeeService;
import com.bht.pim.service.GroupService;
import com.bht.pim.service.ProjectService;
import com.bht.pim.util.LanguageUtil;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author bht
 */
@SuppressWarnings("all")
abstract class ProjectEditFormFragmentBase extends BaseFragment implements Confirmable {

    @Autowired
    private LanguageProperty languageProperty;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    protected GroupService groupService;
    @Autowired
    protected ProjectService projectService;
    @Autowired
    protected DateTimeMapper dateTimeMapper;
    @Autowired
    protected StatusMapper statusMapper;

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
    protected TextField customer;
    @FXML
    protected TextField name;
    @FXML
    protected TextField number;
    @FXML
    protected DatePicker start;
    @FXML
    protected DatePicker end;

    ProjectDto projectDto;
    EmployeeDto leader;
    List<EmployeeDto> members; // member-of-this-projects

    boolean chose;
    private boolean current;
    boolean isUpdate;
    private List<EmployeeDto> employees; // all-current-employees
    private List<EmployeeDto> leaders; // all-current-group-leaders
    private List<EmployeeDto> leaderOptions; // for-creating-new-group
    private AutoCompletionBinding<EmployeeDto> employeeAutoCompletion;

    @Override
    public final void onCreated() {
        initAllLabels();
        initAllAttributes();
        addAllEventListener();
    }

    @Override
    protected final void configLayout() {
        layout = mainPane;
    }

    @Override
    protected final void onSwitch() {
        loadProjectEditForm();
    }

    @Override
    protected final void preLeft() {
        emptyAllFields();
    }

    @Override
    protected final void bindChildren() {
        //
    }

    public final void setIsUpdateState(boolean isUpdateState) {
        isUpdate = isUpdateState;
        initComboBoxStatus();
    }

    public final boolean getProjectById(long projectId) {
        projectDto = projectService.getProjectById(projectId);
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
        LanguageUtil.initLabel(start.promptTextProperty(), FormatProperty.DATE_PATTERN_PROPERTY);
        LanguageUtil.initLabel(lStartEmpty.textProperty(), "label.project.form.specifystart");
        LanguageUtil.initLabel(lEndDate.textProperty(), "label.project.form.enddate");
        LanguageUtil.initLabel(end.promptTextProperty(), FormatProperty.DATE_PATTERN_PROPERTY);
        LanguageUtil.initLabel(lEndInvalid.textProperty(), "label.project.form.endinvalid");
        LanguageUtil.initLabel(cName.textProperty(), "label.project.form.membername");
    }

    private void initAllAttributes() {
        members = new ArrayList<>();
        employees = new ArrayList<>();
        leaders = new ArrayList<>();
        leaderOptions = new ArrayList<>();
        projectDto = ProjectDto.newBuilder().build();
        comboBoxLeader.setDisable(true);
        textField.setDisable(true);
        table.getItems().addAll(Collections.emptyList());
        start.converterProperty().bind(FormatProperty.DATE_STRING_CONVERTER);
        end.converterProperty().bind(FormatProperty.DATE_STRING_CONVERTER);
        end.setDisable(true);
        initComboBoxGroupOption();
        configureTableMember();
    }

    final void loadProjectEditForm() {
        hideAllValidation();
        hideAllCheckLabel();
        getNecessaryData();
        configureAutoCompletion();
        fillAllFieldsIfUpdate();
    }

    private void addAllEventListener() {
        addProjectNumberListener();
        addProjectNameListener();
        addProjectCustomerListener();
        addComboBoxOptionListner();
        addLeaderChangeListener();
        addTableSkinListener();
        addLabelNumberOfMembersListener();
        addDateChangeListener(start);
        addDateChangeListener(end);
    }

    final void hideAllValidation() {
        lFillAll.setVisible(false);
        number.getStyleClass().remove("empty");
        name.getStyleClass().remove("empty");
        customer.getStyleClass().remove("empty");
        start.getEditor().getStyleClass().remove("empty");
        end.getEditor().getStyleClass().remove("empty");
        lGroupOptionEmpty.setVisible(false);
    }

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

    // Config table member, CellValueFactory / CellFactory
    private void configureTableMember() {
        cName.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cName.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.85));
        cName.setResizable(false);

        cRemove.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.15));
        cRemove.setResizable(false);

        cRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cRemove.setCellFactory(this::remove);
        cRemove.setStyle("-fx-alignment: CENTER-RIGHT; -fx-border-insets: 5px;");
    }

    private void initComboBoxStatus() {
        addAvailableStatus();

        languageProperty.getResourceBundleProperty()
                .addListener((observable, oldValue, newValue) -> {
                    int index = comboBoxStatus.getSelectionModel().getSelectedIndex();
                    addAvailableStatus();
                    comboBoxStatus.getSelectionModel().select(index);
                });

        comboBoxStatus.getSelectionModel().selectFirst();
        comboBoxStatus.valueProperty().addListener((observable, oldValue, newValue) ->
                end.setDisable(!statusMapper.toGuiStatus("FIN").get().equals(newValue)));
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

    private void addAvailableStatus() {
        comboBoxStatus.getItems().clear();
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("NEW").get());

        if (isUpdate) {
            comboBoxStatus.getItems().add(statusMapper.toGuiStatus("PLA").get());
            comboBoxStatus.getItems().add(statusMapper.toGuiStatus("INP").get());
            comboBoxStatus.getItems().add(statusMapper.toGuiStatus("FIN").get());
        }
    }

    // Get all necessary data
    private void getNecessaryData() {
        // Get all employees
        employees = employeeService.getEmployeeList(0, 0);

        // Get all current-group leaders
        leaders = groupService.getGroupList(0, 0)
                .stream()
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
            start.setValue(projectDto.getStart());
            end.setValue(projectDto.getEnd());

            EmployeeDto groupLeader = groupService.getGroupById(projectDto.getGroup().getId()).getLeader();
            table.getItems().remove(groupLeader);
            comboBoxOption.getSelectionModel().select(1);
            comboBoxLeader.getSelectionModel().select(groupLeader);
        }
    }

    final void warnOnInvalid(boolean emptyNumber,
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
                });
            }
        };
    }

    // Search employee auto-completion
    private void configureAutoCompletion() {
        employeeAutoCompletion = TextFields.bindAutoCompletion(textField, employees);
        employeeAutoCompletion.setMinWidth(300);
        employeeAutoCompletion.setHideOnEscape(true);
        employeeAutoCompletion.setOnAutoCompleted(event -> {

            textField.clear();
            EmployeeDto employeeDTO = event.getCompletion();
            table.getItems().add(employeeDTO);
            members.add(employeeDTO);

            // Update autocompletion list
            // remove the selected one
            employees.remove(employeeDTO);
            employeeAutoCompletion.dispose();
            configureAutoCompletion();
        });
    }

    // force the field to be numeric only
    private void addProjectNumberListener() {
        number.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // input not number format
                number.setText(newValue
                        .replaceAll("[^\\d]", ""));

            } else if (!newValue.isEmpty()) { // input in correct format
                number.getStyleClass().remove("empty");
            }
        });
    }


    // check-if the name field is empty or not
    private void addProjectNameListener() {
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            name.getStyleClass().remove("empty");
            lNameEmpty.setVisible(newValue.isEmpty());
        });
    }


    // check-if the customer field is empty or not
    private void addProjectCustomerListener() {
        customer.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.getStyleClass().remove("empty");
            lCustomerEmpty.setVisible(newValue.isEmpty());
        });
    }


    private void addComboBoxOptionListner() {
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
    }


    // Fixed table header, prevent from changing order of table column
    private void addTableSkinListener() {
        table.skinProperty().addListener((observable, oldSkin, newSkin) -> {
            TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            header.reorderingProperty().addListener((observable0, oldValue, newValue) ->
                    header.setReordering(false));
        });
    }

    // Number of members participated in this project
    private void addLabelNumberOfMembersListener() {
        table.getItems().addListener((ListChangeListener<EmployeeDto>) change ->
                lSize.setText(String.valueOf(table.getItems().size())));
    }

    // change on datepicker's value
    private void addDateChangeListener(DatePicker datePicker) {
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

    // change on leader
    private void addLeaderChangeListener() {
        comboBoxLeader.getSelectionModel().selectedItemProperty().addListener(this::onChangeLeaderChoice);
    }

    // when change leader choice
    private void onChangeLeaderChoice(ObservableValue<? extends EmployeeDto> observable,
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

        // load auto-completion again
        employeeAutoCompletion.dispose();
        configureAutoCompletion();
    }

    final boolean validProjectNumber() {
        if (!isUpdate && projectService.getProjectByNumber(Long.valueOf(number.getText())).getId() != null) {
            number.getStyleClass().add("empty");
            lNumberExist.setVisible(true);
            return false;
        }
        number.getStyleClass().remove("empty");
        lNumberExist.setVisible(false);
        return true;
    }

    final boolean validEndDate(boolean emptyEnd) {
        if (!end.isDisable() && (emptyEnd || (!emptyEnd && end.getValue().isBefore(start.getValue())))) {
            end.getEditor().getStyleClass().remove("empty");
            end.getEditor().getStyleClass().add("empty");
            lEndInvalid.setVisible(true);
            return false;
        }
        return true;
    }

    final String getGroupOption() {
        return comboBoxOption.getValue();
    }

    final String getProjectStatus() {
        return comboBoxStatus.getValue();
    }
}