package com.bht.pim.app;

import com.bht.pim.proto.employee.EmployeeList;
import com.bht.pim.proto.employee.EmployeeListServiceGrpc;
import com.bht.pim.proto.employee.NoParam;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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

public class AutoCompleteTextField extends Application {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";
    private Logger logger = Logger.getLogger(AutoCompleteTextField.class);

    private TextField textField;
    private TableView<Member> table = new TableView();
    private AutoCompletionBinding<String> employeeAutoCompletion;
    private List<String> employees = employeeList();
    private List<Long> members = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ClassLoader classLoader = getClass().getClassLoader();

        primaryStage.setTitle("Project Information Management");
        primaryStage.getIcons().add(
                new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream("pictures/icon.png"))));

        textField = new TextField();
        textField.setPromptText("Search for employee");
        textField.setFocusTraversable(false);

        configureAutoCompletion();
        employeeAutoCompletion.setMinWidth(400);
        employeeAutoCompletion.setHideOnEscape(true);

        VBox vBox = new VBox();
        vBox.getChildren().add(textField);

        configureTableMember(table);
        table.getItems().addAll(Collections.emptyList());

        VBox layout = new VBox();
        layout.getChildren().addAll(vBox, table);

        layout.setSpacing(20);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 450, 400);

        scene.getStylesheets().add(Objects.requireNonNull(
                classLoader.getResource("css/project_members_table.css")).toExternalForm());

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

        List<String> employeesList = new ArrayList<>();

        employeeList.getEmployeeListList().forEach(employee -> employeesList.add(
                "id=" + employee.getId() + " | " + employee.getVisa() +
                        " - " + employee.getLastName() + " " + employee.getFirstName()));

        return employeesList;
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
            logger.info("Selected ID: " + id);
            members.add(id);
            logger.info(members);

            employees.remove(input);
            employeeAutoCompletion.dispose();
            configureAutoCompletion();
        });
    }

    private void configureTableMember(TableView tableView) {
        TableColumn<Member, Long> cId = new TableColumn<>("ID");
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cId.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.2));
        cId.setResizable(false);

        TableColumn<Member, Long> cName = new TableColumn<>("NAME");
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cName.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.6));
        cName.setResizable(false);

        TableColumn<Member, Long> cDelete = new TableColumn<>("DELETE");
        cDelete.setCellValueFactory(new PropertyValueFactory<>("id"));
        cDelete.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.2));
        cDelete.setResizable(false);

        tableView.getColumns().addAll(cId, cName, cDelete);
    }

    private class Member {
        private String id;
        private String name;
    }
}
