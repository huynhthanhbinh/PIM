package com.bht.pim.fragment.project;

import com.bht.pim.intermediate.Member;
import com.bht.pim.notification.NotificationStyle;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.projects.Project;
import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.util.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class ProjectCreate implements Initializable {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";

    @FXML
    public TextField textField;
    @FXML
    public Label lNumber;
    @FXML
    public Label lSize;
    @FXML
    public TableView table;
    @FXML
    public TableColumn<Member, Long> cName;
    @FXML
    public TableColumn<Member, Member> cRemove;
    @FXML
    public ComboBox<String> comboBoxStatus;
    @FXML
    public ComboBox<String> comboBoxOption;
    @FXML
    public ComboBox<Member> comboBoxLeader;
    @FXML
    public TextField customer;
    @FXML
    public TextField name;
    @FXML
    public TextField number;
    @FXML
    public Label lNumberExist;
    @FXML
    public Label lNameEmpty;
    @FXML
    public Label lCustomerEmpty;
    @FXML
    public Label lLeaderChoice;
    @FXML
    public Label lStartEmpty;
    @FXML
    public Label lEndInvalid;
    @FXML
    public Label lGroupOption;
    @FXML
    public Label lFillAll;
    @FXML
    public Button bCreate;
    @FXML
    public Button bCancel;
    @FXML
    public DatePicker start;
    @FXML
    public DatePicker end;

    private ManagedChannel channel;
    private boolean chose;
    private boolean current;
    private Member leader;
    private List<Long> projectNumbers;
    private List<Long> members;
    private List<Member> employees;
    private List<Member> leaders;
    private List<Member> leaderOptions;
    private AutoCompletionBinding<Member> employeeAutoCompletion;
    private Logger logger = Logger.getLogger(ProjectCreate.class);

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Init this scene code go here
        logger.info("[PIM Client - ProjectCreate] On init scene ");

        // Get all necessary data from server
        getNecessaryData();

        // Init all inputs
        initAllInput();

        // Add all event-listener
        addAllEventListener();

        // Hide all check-label
        hideAllCheckLabel();
    }


    // Hide all check-label
    private void hideAllCheckLabel() {
        lNumberExist.setVisible(false);
        lNameEmpty.setVisible(false);
        lCustomerEmpty.setVisible(false);
        lLeaderChoice.setVisible(false);
        lStartEmpty.setVisible(false);
        lEndInvalid.setVisible(false);
        lGroupOption.setVisible(false);
        lFillAll.setVisible(false);
    }


    // Get all necessary data
    private void getNecessaryData() {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        // Get all exist project numbers
        projectNumbers = ProjectUtil.getProjectNumbers(channel);

        // Get all employees
        employees = EmployeeUtil.getAllEmployees(channel).stream()
                .map(Member::toMember)
                .collect(Collectors.toList());

        // Get all current-group leaders
        leaders = GroupUtil.getAllGroups(channel).stream()
                .map(Member::toMember)
                .collect(Collectors.toList());

        // Init leader leaderOptions for option new groups
        employees.removeAll(leaders);
        leaderOptions = new ArrayList<>(employees);

        // Turn off connection
        channel.shutdown();

        chose = false;
        current = false;
        leader = null;
        members = new ArrayList<>();
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
                    lGroupOption.setVisible(false);

                    if (comboBoxLeader.getItems() != null) {
                        comboBoxLeader.getItems().clear();
                    }

                    if (newValue.equals("Current group")) { // current group
                        comboBoxLeader.getItems().addAll(leaders);
                        current = true;

                    } else { // new group (new a non-exist leader)
                        comboBoxLeader.getItems().addAll(leaderOptions);
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

        // if user click create
        bCreate.setOnMouseClicked(this::onSubmit);

        // if user click cancel
        bCancel.setOnMouseClicked(this::onCancel);
    }

    private void initAllInput() {
        comboBoxStatus.getItems().add("New");
        comboBoxStatus.getSelectionModel().selectFirst();

        String[] options = {"New group", "Current group"};
        comboBoxOption.getItems().addAll(options);
        comboBoxLeader.setDisable(true);
        textField.setDisable(true);

        configureAutoCompletion();
        employeeAutoCompletion.setMinWidth(300);
        employeeAutoCompletion.setHideOnEscape(true);

        configureTableMember(table);
        table.getItems().addAll(Collections.emptyList());

        start.setPromptText("dd/MM/yyyy");
        end.setPromptText("dd/MM/yyyy");

        start.setConverter(DateUtil.DATE_STRING_CONVERTER);
        end.setConverter(DateUtil.DATE_STRING_CONVERTER);
    }


    // Search employee auto-completion
    private void configureAutoCompletion() {
        employeeAutoCompletion = TextFields
                .bindAutoCompletion(textField, employees);
        employeeAutoCompletion.setOnAutoCompleted(event -> {

            Member member = event.getCompletion();

            textField.clear();
            table.getItems().add(member);

            members.add(member.getId());
            logger.info(members);

            // Update autocompletion list
            // remove the selected one
            employees.remove(member);
            employeeAutoCompletion.dispose();
            configureAutoCompletion();
        });
    }


    // Config table member, CellValueFactory / CellFactory
    private void configureTableMember(TableView tableView) {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cName.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.85));
        cName.setResizable(false);

        cRemove.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.15));
        cRemove.setResizable(false);

        cRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cRemove.setCellFactory(this::remove);
        cRemove.setStyle("-fx-alignment: CENTER-RIGHT; -fx-border-insets: 5px;");

        tableView.getItems().addListener((ListChangeListener) change ->
                lSize.setText(String.valueOf(tableView.getItems().size())));
    }


    // Button remove on each table row
    private TableCell<Member, Member> remove(TableColumn<Member, Member> param) {
        return new TableCell<Member, Member>() {
            private final Button bRemove = new Button("X");

            @Override
            protected void updateItem(Member member, boolean empty) {
                if (member == null || member == leader) {

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
                    table.getItems().remove(member);

                    // add this employee back to employee list
                    // for autocompletion next times
                    employees.add(member);

                    // load auto-completion again
                    employeeAutoCompletion.dispose();
                    configureAutoCompletion();

                    // remove this id from the member id list
                    members.remove(member.getId());

                    // log current list member id
                    logger.info(members);
                });
            }
        };
    }


    // when user click button create Project
    private void onSubmit(MouseEvent event) {
        logger.info("[bCreate] onClick");
        boolean emptyNumber = number.getText().isEmpty();
        boolean emptyName = name.getText().isEmpty();
        boolean emptyCustomer = name.getText().isEmpty();
        boolean emptyStart = start.getEditor().getText().isEmpty();
        boolean emptyEnd = end.getEditor().getText().isEmpty();

        lFillAll.setVisible(false);
        number.getStyleClass().remove("empty");
        name.getStyleClass().remove("empty");
        customer.getStyleClass().remove("empty");
        start.getEditor().getStyleClass().remove("empty");
        lGroupOption.setVisible(false);

        if (chose && !(emptyNumber || emptyName || emptyCustomer || emptyStart)) {
            if (!emptyEnd && end.getValue().isBefore(start.getValue())) {
                logger.info("<<< INVALID END-DATE ! >>>");
                end.getEditor().getStyleClass().add("empty");
                lEndInvalid.setVisible(true);
                return;
            }

            logger.info("<<< PIM - On saving new project >>>");

            Employee groupLeader = Employee.newBuilder()
                    .setId(leader.getId())
                    .build();

            Group group = Group.newBuilder()
                    .setLeader(groupLeader)
                    .build();

            try {
                if (comboBoxOption.getSelectionModel().getSelectedItem().equals("New group")) {
                    // send group info to server to save
                    logger.info("<<< PIM - On creating new group >>>");
                    //GroupUtil.addNewGroup(channel, group);
                }

                logger.info(emptyStart);
                logger.info(start.getEditor().getText());

                Project.Builder projectBuilder = Project.newBuilder()
                        .setNumber(Long.parseLong(number.getText()))
                        .setName(name.getText())
                        .setCustomer(customer.getText())
                        .setGroup(group)
                        .setStart(DateUtil.toTimestamp(start.getValue()));

                if (end.getValue() != null) {
                    projectBuilder.setEnd(DateUtil.toTimestamp(end.getValue()));
                }

                Project project = projectBuilder.build();

                List<Employee> employeeList = members.stream()
                        .map(Member::toEmployee)
                        .collect(Collectors.toList());

                ProjectInfo projectInfo = ProjectInfo.newBuilder()
                        .setProject(project)
                        .addAllEmployees(employeeList)
                        .build();

                logger.info(projectInfo);

                String successMsg = "[PIM] Successfully create project !";
                NotificationUtil.show(
                        NotificationStyle.SUCCESS, Pos.CENTER, successMsg);

                //ProjectUtil.addNewProject(projectInfo);

            } catch (Exception exception) {
                logger.info(exception);
                exception.printStackTrace();
            }
        } else {
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
                lGroupOption.setVisible(true);
            }
        }
    }


    // when user click button cancel
    private void onCancel(MouseEvent event) {
        logger.info("[bCancel] onClick");
    }


    // when change leader choice
    private void leaderChoice(ObservableValue<? extends Member> observable,
                              Member oldValue, Member newValue) {
        if (oldValue == null) { // change group option

            if (!current) { // new-group
                employees.remove(newValue);
            }

            members.add(newValue.getId());

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

                members.remove(oldValue.getId());
                members.remove(newValue.getId()); // if exist before
                members.add(newValue.getId()); // add and mark as leader

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

                members.remove(oldValue.getId());

                // Remove old-leader value out of table member
                table.getItems().remove(oldValue);
            }
        }

        logger.info(members);

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
}