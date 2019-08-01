package com.bht.pim.app;

import com.bht.pim.proto.employee.Employee;
import com.bht.pim.proto.employee.EmployeeList;
import com.bht.pim.proto.employee.EmployeeListServiceGrpc;
import com.bht.pim.proto.employee.NoParam;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AutoCompleteTextField extends Application {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";
    private Logger logger = Logger.getLogger(AutoCompleteTextField.class);

    private TextField textField;
    private Label lNumber = new Label("Number of members : ");
    private Label lSize = new Label("0");
    private TableView<Member> table = new TableView();
    private AutoCompletionBinding<String> employeeAutoCompletion;
    private List<String> employees = employeeList();
    private List<Long> members = new ArrayList<>();
    private long leaderId = 13;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        ClassLoader classLoader = getClass().getClassLoader();

        primaryStage.setTitle("Project Information Management");
        primaryStage.getIcons().add(
                new Image(Objects.requireNonNull(classLoader
                        .getResourceAsStream("pictures/icon.png"))));

        textField = new TextField();
        textField.setPromptText("Search for employee");
        textField.setFocusTraversable(false);

        configureAutoCompletion();
        employeeAutoCompletion.setMinWidth(400);
        employeeAutoCompletion.setHideOnEscape(true);

        VBox vBox = new VBox();
        vBox.getChildren().add(textField);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(lNumber, lSize);
        hBox.getStyleClass().add("size");

        lSize.setId("color-label");

        configureTableMember(table);
        table.getItems().addAll(Collections.emptyList());

        VBox layout = new VBox();
        layout.getChildren().addAll(vBox, hBox, table);

        layout.setSpacing(20);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 450, 400);

        scene.getStylesheets().add(Objects.requireNonNull(classLoader
                .getResource("css/project_members_table.css"))
                .toExternalForm());

        primaryStage.setScene(scene);
        showWindow(primaryStage);
    }


    private void showWindow(Stage window) {
        window.setResizable(true);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }


    private List<String> employeeList() {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        // Get employee list =======================================

        EmployeeListServiceGrpc.EmployeeListServiceBlockingStub stub3 =
                EmployeeListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        EmployeeList employeeList = stub3.getEmployeeList(noParam);

        channel.shutdown();

        return employeeList.getEmployeeListList().stream()
                .map(this::toEmployeeInfo)
                .collect(Collectors.toList());
    }


    private void configureAutoCompletion() {
        employeeAutoCompletion = TextFields
                .bindAutoCompletion(textField, employees);
        employeeAutoCompletion.setOnAutoCompleted(event -> {
            String input = textField.getText();
            textField.clear();

            int start = input.indexOf('=') + 1;
            int end = input.indexOf('|') - 1;
            long id = Long.parseLong(input.substring(start, end));
            String name = input.substring(end + 3);

            table.getItems().add(new Member(id, name));

            members.add(id);
            logger.info(members);

            // Update autocompletion list
            // remove the selected one
            employees.remove(input);
            employeeAutoCompletion.dispose();
            configureAutoCompletion();
        });
    }


    private void configureTableMember(TableView tableView) {
        TableColumn<Member, Long> cName = new TableColumn<>("NAME");
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cName.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.85));
        cName.setResizable(false);

        TableColumn<Member, Member> cRemove = new TableColumn<>("");
        cRemove.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.15));
        cRemove.setResizable(false);

        cRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cRemove.setCellFactory(this::REMOVE);
        cRemove.setStyle("-fx-alignment: CENTER-RIGHT; -fx-border-insets: 5px;");

        tableView.getColumns().addAll(cName, cRemove);
        tableView.getItems().addListener((ListChangeListener) change ->
                lSize.setText(String.valueOf(tableView.getItems().size())));
    }


    // mapping employee to employee info
    private String toEmployeeInfo(Employee employee) {
        return "id=" + employee.getId() + " | " + employee.getVisa() + " - "
                + employee.getLastName() + " " + employee.getFirstName();
    }


    // button remove on each table row of table project members
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


    // for table initialize
    public class Member {
        private long id;
        private String name;

        public Member(long id, String name) {
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

        public String toEmployeeInfo() {
            return "id=" + id + " | " + name;
        }
    }
}
