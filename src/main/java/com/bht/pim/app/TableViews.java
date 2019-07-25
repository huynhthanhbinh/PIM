package com.bht.pim.app;

import com.bht.pim.models.ProjectDTO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Objects;

public class TableViews extends Application {

    private Logger logger = Logger.getLogger(Selection.class);

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

        /*
        TableColumn<ProjectDTO, Long> cSelect = new TableColumn<>("");
        cSelect.setMinWidth(40);
        cSelect.setCellValueFactory(new PropertyValueFactory<>());
        */


        TableColumn<ProjectDTO, Long> cNumber = new TableColumn<>("Number");
        cNumber.setMinWidth(40);
        cNumber.setCellValueFactory(new PropertyValueFactory<>("number"));


        TableColumn<ProjectDTO, Long> cName = new TableColumn<>("Name");
        cName.setMinWidth(40);
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<ProjectDTO, Long> cStatus = new TableColumn<>("Status");
        cStatus.setMinWidth(40);
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        TableColumn<ProjectDTO, Long> cCustomer = new TableColumn<>("Customer");
        cCustomer.setMinWidth(40);
        cCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));


        TableColumn<ProjectDTO, Long> cStart = new TableColumn<>("Start Date");
        cStart.setMinWidth(40);
        cStart.setCellValueFactory(new PropertyValueFactory<>("start"));


        /*
        TableColumn<ProjectDTO, Long> cDelete = new TableColumn<>("Delete");
        cDelete.setMinWidth(40);
        cDelete.setCellValueFactory(new PropertyValueFactory<>());
        */

        TableView<ProjectDTO> table = new TableView<>();
        table.setItems(getAllProducts());
        table.getColumns().addAll(cNumber, cName, cStatus, cCustomer, cStart);

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(table);

        Scene scene = new Scene(layout, 500, 400);

        scene.getStylesheets().add(Objects.requireNonNull(
                classLoader.getResource("form.css")).toExternalForm());

        window.setScene(scene);
        showWindow(window);
    }

    private void showWindow(Stage window) {
        window.setResizable(false);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }

    // Get all of products
    public ObservableList<ProjectDTO> getAllProducts() {

        ObservableList<ProjectDTO> projects =
                FXCollections.observableArrayList();

        // Real project : get from database
        // Using service, repository with Spring, Hibernate
        // This is just some sample data
        for (int i = 0; i < 8; i++) {
            ProjectDTO project = new ProjectDTO();

            int id = i + 1;

            project.setId(id);
            project.setNumber(id);
            project.setName("Project " + id);
            project.setCustomer("Customer " + id);
            project.setGroup("Group " + id);

            projects.add(project);
        }
        return projects;
    }
}