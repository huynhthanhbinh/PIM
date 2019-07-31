package com.bht.pim.app;

import com.bht.pim.proto.employee.EmployeeList;
import com.bht.pim.proto.employee.EmployeeListServiceGrpc;
import com.bht.pim.proto.employee.NoParam;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AutoCompleteTextField extends Application {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";
    private Logger logger = Logger.getLogger(AutoCompleteTextField.class);
    private ManagedChannel channel;

    private TextField textField;
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

        Scene scene = new Scene(vBox, 1024, 576);

        scene.getStylesheets().add(Objects.requireNonNull(
                classLoader.getResource("css/sample.css")).toExternalForm());

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
        channel = ManagedChannelBuilder
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
}
