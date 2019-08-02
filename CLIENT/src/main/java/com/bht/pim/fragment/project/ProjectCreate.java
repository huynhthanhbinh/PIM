package com.bht.pim.fragment.project;

import com.bht.pim.proto.employee.Employee;
import com.bht.pim.proto.employee.EmployeeList;
import com.bht.pim.proto.employee.EmployeeListServiceGrpc;
import com.bht.pim.proto.employee.NoParam;
import com.bht.pim.proto.project.ProjectListServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public ComboBox<String> comboBoxOption;
    @FXML
    public ComboBox<String> comboBoxStatus;
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


    private ManagedChannel channel;
    private long leaderId;
    private List<Long> projectNumbers;
    private List<String> employees;
    private List<Long> members;
    private AutoCompletionBinding<String> employeeAutoCompletion;
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
    }


    // Get all necessary data
    private void getNecessaryData() {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        // Get all exist project numbers
        projectNumbers = getProjectNumbers(channel);

        // Get all employees
        employees = getEmployeeList(channel);

        // Turn off connection
        channel.shutdown();

        leaderId = 13;
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
                lNumberExist.setVisible(projectNumbers
                        .contains(Long.valueOf(newValue)));
            }
        });

        // check-if the name field is empty or not
        name.textProperty().addListener((observable, oldValue, newValue) ->
                lNameEmpty.setVisible(newValue.isEmpty()));

        // check-if the customer field is empty or not
        customer.textProperty().addListener((observable, oldValue, newValue) ->
                lCustomerEmpty.setVisible(newValue.isEmpty()));
    }

    private void initAllInput() {
        comboBoxStatus.getItems().add("New");
        comboBoxStatus.getSelectionModel().selectFirst();

        String[] options = {"New group", "Current group"};
        comboBoxOption.getItems().addAll(options);
        comboBoxOption.getSelectionModel().selectFirst();

        configureAutoCompletion();
        employeeAutoCompletion.setMinWidth(300);
        employeeAutoCompletion.setHideOnEscape(true);

        configureTableMember(table);
        table.getItems().addAll(Collections.emptyList());
    }


    // Search employee auto-completion
    private void configureAutoCompletion() {
        employeeAutoCompletion = TextFields
                .bindAutoCompletion(textField, employees);
        employeeAutoCompletion.setOnAutoCompleted(event -> {
            String input = textField.getText();
            textField.clear();

            int start = input.indexOf('=') + 1;
            int end = input.indexOf('|') - 1;
            long id = Long.parseLong(input.substring(start, end));
            String memberName = input.substring(end + 3);

            table.getItems().add(new Member(id, memberName));

            members.add(id);
            logger.info(members);

            // Update autocompletion list
            // remove the selected one
            employees.remove(input);
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
        cRemove.setCellFactory(this::REMOVE);
        cRemove.setStyle("-fx-alignment: CENTER-RIGHT; -fx-border-insets: 5px;");

        tableView.getItems().addListener((ListChangeListener) change ->
                lSize.setText(String.valueOf(tableView.getItems().size())));
    }


    // Button remove on each table row
    private TableCell<Member, Member> REMOVE(TableColumn<Member, Member> param) {
        return new TableCell<Member, Member>() {
            private final Button bRemove = new Button("X");

            @Override
            protected void updateItem(Member member, boolean empty) {
                if (member == null || member.id == leaderId) {

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
                    employees.add(member.toEmployeeInfo());

                    // load auto-completion again
                    employeeAutoCompletion.dispose();
                    configureAutoCompletion();

                    // remove this id from the member id list
                    members.remove(member.id);

                    // log current list member id
                    logger.info(members);
                });
            }
        };
    }


    // Get all project numbers ====================================
    private List<Long> getProjectNumbers(Channel channel) {
        ProjectListServiceGrpc.ProjectListServiceBlockingStub stub5 =
                ProjectListServiceGrpc.newBlockingStub(channel);

        com.bht.pim.proto.project.NoParam noParam2 =
                com.bht.pim.proto.project.NoParam.newBuilder().build();

        return stub5.getProjectNumbers(noParam2)
                .getProjectNumbersList();
    }


    // Employee List get response from server
    private List<String> getEmployeeList(Channel channel) {
        // Get employee list =======================================

        EmployeeListServiceGrpc.EmployeeListServiceBlockingStub stub3 =
                EmployeeListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        EmployeeList employeeList = stub3.getEmployeeList(noParam);

        return employeeList.getEmployeeListList().stream()
                .map(this::toEmployeeInfo)
                .collect(Collectors.toList());
    }


    // mapping employee to employee info
    private String toEmployeeInfo(Employee employee) {
        return "id=" + employee.getId() + " | " + employee.getVisa() + " - "
                + employee.getLastName() + " " + employee.getFirstName();
    }


    // for table initialize
    public class Member {
        private long id;
        private String name;

        private Member(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String toEmployeeInfo() {
            return "id=" + id + " | " + name;
        }
    }

    // Add a new group ============================================
//
//    Group newGroup = Group.newBuilder()
//            .setGroupLeaderId(2)
//            .build();
//
//    Success success = stub1.addNewGroup(newGroup);
//        logger.info(success.getIsSuccess());
}
