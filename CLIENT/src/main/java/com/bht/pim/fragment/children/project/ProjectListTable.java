package com.bht.pim.fragment.children.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.service.ProjectService;
import com.bht.pim.util.ProjectUtil;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Log4j
@Controller
<<<<<<< HEAD:CLIENT/src/main/java/com/bht/pim/fragment/children/project/ProjectListTable.java
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_LIST_TABLE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectListTable.fxml")
public class ProjectListTable implements Initializable {
=======
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_LIST,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/project/ProjectList.fxml")
public class ProjectList implements Initializable {
>>>>>>> parent of f33f974... [ProjectCreate] Saving Project:CLIENT/src/main/java/com/bht/pim/fragment/project/ProjectList.java

    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectUtil projectUtil;


    @FXML
    private TableView<ProjectDto> table;
    @FXML
    private TableColumn<ProjectDto, ProjectDto> cSelect;
    @FXML
    private TableColumn<ProjectDto, Long> cNumber;
    @FXML
    private TableColumn<ProjectDto, ProjectDto> cName;
    @FXML
    private TableColumn<ProjectDto, String> cCustomer;
    @FXML
    private TableColumn<ProjectDto, String> cStatus;
    @FXML
    private TableColumn<ProjectDto, LocalDate> cStart;
    @FXML
    private TableColumn<ProjectDto, ProjectDto> cManagement;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Get all necessary data from server
        getNecessaryData();

        // Init all inputs
        initAllFields();

        // Add all event-listener
        addAllEventListener();
    }


    // Get all necessary data
    private void getNecessaryData() {
        table.setItems(projectService.getAllProjects());
    }

    // Init all table fields
    private void initAllFields() {
        cSelect.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.05));
        cSelect.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cSelect.setCellFactory(this::select);
        cSelect.setResizable(false);

        cNumber.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        cNumber.setResizable(false);

        cName.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.3));
        cName.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cName.setCellFactory(this::name);
        cName.setResizable(false);

        cCustomer.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.25));
        cCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        cCustomer.setResizable(false);

        cStatus.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        cStatus.setResizable(false);

        cStart.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        cStart.setCellFactory(projectUtil::dateFormat);
        cStart.setResizable(false);

        cManagement.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cManagement.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cManagement.setCellFactory(this::management);
        cManagement.setResizable(false);
    }

    // Button delete on table row
    private TableCell<ProjectDto, ProjectDto> management(TableColumn<ProjectDto, ProjectDto> param) {
        return new TableCell<ProjectDto, ProjectDto>() {

            private final Button bRemove = new Button(" X ");

            @Override
            protected void updateItem(ProjectDto project, boolean empty) {
                if (project == null || empty || !project.getStatus().equals("New")) {
                    setGraphic(null);
                    return;
                }

                setGraphic(bRemove);
                bRemove.setOnAction(event -> {
                    log.info("Delete project id = " + project.getId());
                });
            }
        };
    }

    // Add all event-listener
    private void addAllEventListener() {
        table.skinProperty().addListener((observable, oldSkin, newSkin) -> {
            TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            header.reorderingProperty().addListener((observable0, oldValue, newValue) ->
                    header.setReordering(false));
        });
    }

    // Name label - clickable
    private TableCell<ProjectDto, ProjectDto> name(TableColumn<ProjectDto, ProjectDto> param) {
        return new TableCell<ProjectDto, ProjectDto>() {

            private Label lName = new Label();

            @Override
            protected void updateItem(ProjectDto project, boolean empty) {
                if (project == null || empty) {
                    setGraphic(null);
                    return;
                }

                lName.setText(project.getName());
                lName.getStyleClass().add("clickable");
                lName.setPickOnBounds(false);
                lName.setOnMouseClicked(event -> {
                    log.info("view info of project id = " + project.getId());
                });

                setGraphic(lName);
            }
        };
    }

    // Checkbox Select
    private TableCell<ProjectDto, ProjectDto> select(TableColumn<ProjectDto, ProjectDto> param) {
        return new TableCell<ProjectDto, ProjectDto>() {

            @Override
            protected void updateItem(ProjectDto project, boolean empty) {
                if (project == null || empty) {
                    setGraphic(null);
                    return;
                }

                CheckBox checkBox = new CheckBox();
                setGraphic(checkBox);

                checkBox.selectedProperty().addListener(
                        (observable, oldValue, newValue) -> {

                        });
            }
        };
    }
}
