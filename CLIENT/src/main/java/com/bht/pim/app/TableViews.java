package com.bht.pim.app;

import com.bht.pim.proto.project.Project;
import com.bht.pim.proto.project.ProjectList;
import com.bht.pim.proto.project.ProjectListServiceGrpc;
import com.bht.pim.util.CellMapping;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TableViews extends Application {

    private Logger logger = Logger.getLogger(Selection.class);

    private static final int PORT = 9999;
    private static final String HOST = "localhost";
    private ManagedChannel channel;

    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ClassLoader classLoader = getClass().getClassLoader();

        window = primaryStage;

        window.setTitle("Project Information Management");
        window.getIcons().add(
                new Image(Objects.requireNonNull(
                        classLoader.getResourceAsStream("pictures/icon.png"))));

        TableView<Project> table = new TableView<>();

        TableColumn<Project, Long> cSelect = new TableColumn<>("");
        cSelect.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.05));

        TableColumn<Project, Long> cNumber = new TableColumn<>("Number");
        cNumber.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cNumber.setCellValueFactory(new PropertyValueFactory<>("number"));


        TableColumn<Project, String> cName = new TableColumn<>("Name");
        cName.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.25));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Project, String> cCustomer = new TableColumn<>("Customer");
        cCustomer.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.25));
        cCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));


        TableColumn<Project, String> cStatus = new TableColumn<>("Status");
        cStatus.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        cStatus.setCellFactory(CellMapping::STATUS);


        TableColumn<Project, Long> cStart = new TableColumn<>("Start");
        cStart.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        cStart.setCellFactory(CellMapping::DATE);


        TableColumn<Project, Long> cEnd = new TableColumn<>("End");
        cEnd.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        cEnd.setCellFactory(CellMapping::DATE);

        TableColumn<Project, Long> cDelete = new TableColumn<>("Delete");
        cDelete.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.05));
        cDelete.setResizable(false);

        table.setItems(getAllProducts());
        table.getColumns().addAll(cSelect, cNumber, cName, cCustomer, cStatus, cStart, cEnd, cDelete);

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(table);

        Scene scene = new Scene(layout, 1024, 576);

        scene.getStylesheets().add(Objects.requireNonNull(
                classLoader.getResource("form.css")).toExternalForm());

        window.setScene(scene);
        showWindow(window);
    }

    private void showWindow(Stage window) {
        window.setResizable(true);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }

    // Get all of products
    public ObservableList<Project> getAllProducts() {

        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        channel = ManagedChannelBuilder
                .forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        ProjectListServiceGrpc.ProjectListServiceBlockingStub stub5 =
                ProjectListServiceGrpc.newBlockingStub(channel);

        com.bht.pim.proto.project.NoParam noParam2 =
                com.bht.pim.proto.project.NoParam.newBuilder().build();

        ProjectList projectList = stub5.getProjectList(noParam2);

        // Real project : get from database
        // Using service, repository with Spring, Hibernate
        // This is just some sample data

        channel.shutdown();

        return FXCollections.observableArrayList(projectList.getProjectListList());
    }
}