package com.bht.pim.fragment.children.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.fragment.children.ParentOwning;
import com.bht.pim.fragment.parent.project.ProjectInfo;
import com.bht.pim.fragment.parent.project.ProjectList;
import com.bht.pim.fragment.parent.project.ProjectUpdate;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.message.impl.IdentifierSending;
import com.bht.pim.notification.NotificationStyle;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.service.ProjectService;
import com.bht.pim.util.NotificationUtil;
import com.bht.pim.util.PimUtil;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_TABLE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectTable.fxml")
public class ProjectTable implements Initializable, ParentOwning {

    private final Image delete = PimUtil.getImage("delete");
    private final Image edit = PimUtil.getImage("edit");
    private final Image deleteInverse = PimUtil.getImage("delete_inverse");
    private final Image editInverse = PimUtil.getImage("edit_inverse");

    private LanguageProperty languageProperty = AppConfiguration.LANGUAGE_PROPERTY;
    private static final int MAX_TABLE_ROW = 8;

    private int countSuccess = 0; // for delete all selected projects
    private int countFail = 0; // for delete all selected projects

    // binding
    @Getter
    private IntegerProperty pageCountProperty;
    @Getter
    private IntegerProperty pageIndexProperty;
    @Getter
    private StringProperty statusProperty;
    @Getter
    private ObjectProperty<SingleSelectionModel<String>> statusSelection;
    @Getter
    private IntegerProperty selectedProperty;
    @Setter
    private TextField searchBox;
    @Getter
    private BooleanProperty successProperty;


    @Autowired
    private ProjectService projectService;
    @Autowired
    private PimUtil projectUtil;
    @Autowired
    private StatusMapper statusMapper;


    @Resource
    private Context context;
    @FXML
    @Getter
    private VBox mainPane;
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
    private TableColumn<ProjectDto, ProjectDto> cStatus;
    @FXML
    private TableColumn<ProjectDto, LocalDate> cStart;
    @FXML
    private TableColumn<ProjectDto, ProjectDto> cManagement;

    private List<ProjectDto> aboutToDeleteProjects;


    @Override
    public void onSwitchParentFragment() {
        // Get all necessary data from server
        getListProject(pageIndexProperty.get());
        searchBox.setOnKeyPressed(this::onKeyPressedSearchBox);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init property for binding purposes
        initAllProperties();

        // for multilingual
        initAllLabels();

        // Init all inputs
        initAllFields();

        // Add all event-listener
        addAllEventListener();
    }


    // Get all necessary data
    private void getListProject(int pageIndex) {

        ObservableList<ProjectDto> projectDtoList = projectService
                .getProjectList(
                        MAX_TABLE_ROW,
                        pageIndex,
                        searchBox.textProperty(),
                        statusProperty);

        table.setItems(projectDtoList);
        double temp;

        if (statusProperty.get() != null && !statusProperty.get().isEmpty()) {
            temp = projectService.getNumberOfProjectsByStatus(statusProperty);
        } else if (!searchBox.textProperty().get().isEmpty()) {
            temp = projectService.getNumberOfProjectsByKeyword(searchBox.textProperty());
        } else {
            temp = projectService.getNumberOfProjects();
        }

        log.info("Number of projects: " + (long) temp);
        pageCountProperty.set((int) Math.ceil(temp / MAX_TABLE_ROW));
        selectedProperty.set(0); // reset number of selected projects
        aboutToDeleteProjects.clear(); // remove old list of last page
    }

    // Init all table fields
    private void initAllFields() {
        cSelect.prefWidthProperty().bind(table.widthProperty().multiply(0.05));
        cSelect.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cSelect.setCellFactory(this::select);
        cSelect.setResizable(false);

        cNumber.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        cNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        cNumber.setResizable(false);

        cName.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        cName.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cName.setCellFactory(this::name);
        cName.setResizable(false);

        cCustomer.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        cCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        cCustomer.setResizable(false);

        cStatus.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        cStatus.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cStatus.setCellFactory(this::status);
        cStatus.setResizable(false);

        cStart.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        cStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        cStart.setCellFactory(projectUtil::dateFormat);
        cStart.setResizable(false);

        cManagement.prefWidthProperty().bind(table.widthProperty().subtract(20).multiply(0.1));
        cManagement.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        cManagement.setCellFactory(this::management);
        cManagement.setResizable(false);
    }

    // Add all event-listener
    private void addAllEventListener() {
        table.skinProperty().addListener((observable, oldSkin, newSkin) -> {
            TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            header.reorderingProperty().addListener((observable0, oldValue, newValue) ->
                    header.setReordering(false));
        });
    }

    // Button delete on table row
    private TableCell<ProjectDto, ProjectDto> management(TableColumn<ProjectDto, ProjectDto> param) {
        return new TableCell<ProjectDto, ProjectDto>() {

            private Button bRemove = new Button();
            private Button bEdit = new Button();

            private ImageView iRemove = new ImageView();
            private ImageView iEdit = new ImageView();

            @Override
            protected void updateItem(ProjectDto projectDto, boolean empty) {
                if (projectDto == null || empty) {
                    setGraphic(null);
                    return;
                }

                bRemove.setGraphic(iRemove);
                bEdit.setGraphic(iEdit);

                iRemove.imageProperty().bind(
                        Bindings.when(getTableRow().selectedProperty())
                                .then(deleteInverse)
                                .otherwise(delete));

                iEdit.imageProperty().bind(
                        Bindings.when(getTableRow().selectedProperty())
                                .then(editInverse)
                                .otherwise(edit));

                HBox hBox = new HBox();
                hBox.getChildren().addAll(bEdit, bRemove);
                setGraphic(hBox);
                hBox.setAlignment(Pos.CENTER_RIGHT);

                bRemove.setOnAction(event -> {
                    if (!projectDto.getStatus().equals(statusMapper.toGuiStatus("NEW"))) {
                        NotificationUtil.showNotification(
                                NotificationStyle.ERROR,
                                Pos.CENTER,
                                "Cannot delete project which status is not \"New\"!");
                        return;
                    }
                    log.info("Delete project id = " + projectDto.getId());

                    if (projectService.deleteProject(projectDto.getId())) {
                        NotificationUtil.showNotification(
                                NotificationStyle.SUCCESS,
                                Pos.CENTER,
                                "Successfully delete project !");


                        table.getItems().remove(projectDto);
                        getListProject(calculatePageIndexAfterDelete());

                    } else {
                        NotificationUtil.showNotification(
                                NotificationStyle.WARNING,
                                Pos.CENTER,
                                "Failed to delete project !");
                    }
                });

                bEdit.setOnAction(event -> {
                    log.info("Edit project id = " + projectDto.getId());

                    log.info(successProperty.get());

                    IdentifierSending sending = new IdentifierSending(
                            ProjectList.class,
                            ProjectUpdate.class,
                            projectDto.getId());

                    context.send(AppConfiguration.COMPONENT_MAIN, sending);

                    log.info(successProperty.get());

                    FragmentSwitching switching = new FragmentSwitching(
                            ProjectList.class,
                            ProjectUpdate.class);

                    context.send(AppConfiguration.COMPONENT_MAIN, switching);
                });
            }
        };
    }

    // Name label - clickable
    private TableCell<ProjectDto, ProjectDto> name(TableColumn<ProjectDto, ProjectDto> param) {
        return new TableCell<ProjectDto, ProjectDto>() {

            private Label lName = new Label();

            @Override
            protected void updateItem(ProjectDto projectDto, boolean empty) {
                if (projectDto == null || empty) {
                    setGraphic(null);
                    return;
                }

                lName.setText(projectDto.getName());
                lName.getStyleClass().add("clickable");
                lName.setPickOnBounds(false);
                lName.setOnMouseClicked(event -> {
                    log.info("view info of project id = " + projectDto.getId());

                    log.info(successProperty.get());

                    IdentifierSending sending = new IdentifierSending(
                            ProjectList.class,
                            ProjectInfo.class,
                            projectDto.getId());

                    context.send(AppConfiguration.COMPONENT_MAIN, sending);

                    log.info(successProperty.get());

                    FragmentSwitching switching = new FragmentSwitching(
                            ProjectList.class,
                            ProjectInfo.class);

                    context.send(AppConfiguration.COMPONENT_MAIN, switching);
                });

                setGraphic(lName);
            }
        };
    }

    //Status label
    private TableCell<ProjectDto, ProjectDto> status(TableColumn<ProjectDto, ProjectDto> param) {
        return new TableCell<ProjectDto, ProjectDto>() {

            private Label lStatus = new Label();

            @Override
            protected void updateItem(ProjectDto projectDto, boolean empty) {
                if (projectDto == null || empty) {
                    setGraphic(null);
                    return;
                }

                lStatus.textProperty().bind(projectDto.getStatus());
                setGraphic(lStatus);
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
                            log.info(project.getId() + " : " + newValue);
                            if (newValue) {
                                aboutToDeleteProjects.add(project);
                                selectedProperty.set(selectedProperty.get() + 1);
                            } else {
                                selectedProperty.set(selectedProperty.get() - 1);
                                aboutToDeleteProjects.remove(project);
                            }
                            log.info("List project to be deleted !");
                            aboutToDeleteProjects.forEach(log::info);
                        });
            }
        };
    }

    private void initAllLabels() {
        ResourceBundle bundle = languageProperty.getResourceBundleProperty().get();
        cNumber.setText(bundle.getString("label.number"));
        cName.setText(bundle.getString("label.name"));
        cCustomer.setText(bundle.getString("label.customer"));
        cStatus.setText(bundle.getString("label.status"));
        cStart.setText(bundle.getString("label.start"));
        cManagement.setText(bundle.getString("label.management"));

        languageProperty.getResourceBundleProperty()
                .addListener((observable, oldValue, newValue) -> {
                    cNumber.setText(newValue.getString("label.number"));
                    cName.setText(newValue.getString("label.name"));
                    cCustomer.setText(newValue.getString("label.customer"));
                    cStatus.setText(newValue.getString("label.status"));
                    cStart.setText(newValue.getString("label.start"));
                    cManagement.setText(newValue.getString("label.management"));
                });
    }

    // if page n has only 1 project
    // after delete, load page 1
    private int calculatePageIndexAfterDelete() {
        return (table.getItems().size() > 0)
                ? pageIndexProperty.get()
                : 0;
    }

    private void onSearchForProject(Event event) {
        if (!searchBox.getText().isEmpty()) {
            statusSelection.get().clearSelection();
            log.info("Searching for project with keyword = " + searchBox.getText());
            getListProject(0);
        }
    }

    public void onReset(MouseEvent mouseEvent) {
        statusSelection.get().clearSelection();
        searchBox.clear();
        pageIndexProperty.set(0);
        getListProject(0);
    }

    private void onKeyPressedSearchBox(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            onSearchForProject(keyEvent);
        }
    }

    private void initAllProperties() {
        aboutToDeleteProjects = new ArrayList<>();
        pageIndexProperty = new SimpleIntegerProperty();
        pageCountProperty = new SimpleIntegerProperty();
        statusProperty = new SimpleStringProperty();
        statusSelection = new SimpleObjectProperty<>();
        successProperty = new SimpleBooleanProperty();
        selectedProperty = new SimpleIntegerProperty();

        pageIndexProperty.addListener((observable, oldValue, newValue) ->
                getListProject(newValue.intValue()));

        statusProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchBox.setText("");
            }
            getListProject(0);
        });
    }

    public void onDeleteAllSelected(MouseEvent mouseEvent) {
        countSuccess = 0;
        countFail = 0;

        try {
            aboutToDeleteProjects.forEach(projectDto -> {

                if (projectDto.getStatus().equals(statusMapper.toGuiStatus("NEW"))) {
                    projectService.deleteProject(projectDto.getId());
                    table.getItems().remove(projectDto);
                    countSuccess++;

                } else {
                    countFail++;
                }
            });

        } catch (Exception exception) {
            log.info(exception);

        } finally {
            getListProject(calculatePageIndexAfterDelete());

            if (countFail > 0) {
                NotificationUtil.showNotification(
                        NotificationStyle.WARNING,
                        Pos.CENTER,
                        "Failed to delete " + countFail + " non-new project(s) !");
            }
            if (countSuccess > 0) {
                NotificationUtil.showNotification(
                        NotificationStyle.SUCCESS,
                        Pos.CENTER,
                        "Successfully delete " + countSuccess + " project(s) !");
            }
        }
    }
}