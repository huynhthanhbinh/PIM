package com.bht.pim.fragment.children.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.base.BaseFragment;
import com.bht.pim.component.MainPane;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.fragment.parent.project.ProjectInfoFragment;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.fragment.parent.project.ProjectUpdateFragment;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.message.impl.IdentifierSending;
import com.bht.pim.notification.NotificationStyle;
import com.bht.pim.service.ProjectService;
import com.bht.pim.util.FormatUtil;
import com.bht.pim.util.ImageUtil;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.NotificationUtil;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

/**
 * @author bht
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@Fragment(id = ProjectTableFragment.ID,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectTableFragment.fxml")
public final class ProjectTableFragment extends BaseFragment {

    static final String ID = "projectTableFragment";
    private static final int MAX_TABLE_ROW = 8;

    private final Image delete = ImageUtil.getImage("delete");
    private final Image edit = ImageUtil.getImage("edit");
    private final Image deleteInverse = ImageUtil.getImage("delete_inverse");
    private final Image editInverse = ImageUtil.getImage("edit_inverse");


    private int countSuccess; // for delete all selected projects
    private int countFail; // for delete all selected projects

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
    private FormatUtil<ProjectDto> formatUtil;
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
    public void onCreated() {
        // init property for binding purposes
        initAllProperties();

        // for multilingual
        initAllLabels();

        // Init all inputs
        initAllFields();

        // Add all event-listener
        addAllEventListener();
    }

    @Override
    protected void configLayout() {
        layout = mainPane;
    }

    @Override
    protected void onSwitch() {
        getListProject(pageIndexProperty.get());
        searchBox.setOnKeyPressed(this::onKeyPressedSearchBox);
    }

    @Override
    protected void preLeft() {
        // searchBox.clear()
        // not clear any more
        // as view or edit project and return back
    }

    @Override
    protected void bindChildren() {
        //
    }

    // Get all necessary data
    private void getListProject(int pageIndex) {

        ObservableList<ProjectDto> projectDtoList = projectService
                .getProjectList(
                        MAX_TABLE_ROW,
                        pageIndex,
                        searchBox.textProperty(),
                        statusProperty);

        table.getItems().clear();
        table.setItems(projectDtoList);
        double temp;

        if (statusProperty.get() != null && !statusProperty.get().isEmpty()) {
            LOGGER.info("[REQUEST] Get all projects with status = " + statusProperty.get());
            temp = projectService.getNumberOfProjectsByStatus(statusProperty);
        } else if (!searchBox.textProperty().get().isEmpty()) {
            LOGGER.info("[REQUEST] Get all projects with keyword = " + searchBox.getText());
            temp = projectService.getNumberOfProjectsByKeyword(searchBox.textProperty());
        } else {
            LOGGER.info("[REQUEST] Get all projects");
            temp = projectService.getNumberOfProjects();
        }

        LOGGER.info("[RESPONSE] Number of projects: " + (long) temp);
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
        cStart.cellFactoryProperty().bind(formatUtil.dateCellFormatProperty);
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
                    LOGGER.info("[INFO] Delete project id = " + projectDto.getId());

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
                    if (projectDto.getStatus().equals(statusMapper.toGuiStatus("FIN"))) {
                        NotificationUtil.showNotification(
                                NotificationStyle.ERROR,
                                Pos.CENTER,
                                "Cannot edit project which has already been\"Finished\"!");
                        return;
                    }

                    LOGGER.info("[INFO] Edit project id = " + projectDto.getId());

                    LOGGER.info(successProperty.get());

                    IdentifierSending sending = new IdentifierSending(
                            ProjectListFragment.class,
                            ProjectUpdateFragment.class,
                            projectDto.getId());

                    context.send(MainPane.ID, sending);

                    LOGGER.info(successProperty.get());

                    FragmentSwitching switching = new FragmentSwitching(
                            ProjectListFragment.class,
                            ProjectUpdateFragment.class);

                    context.send(MainPane.ID, switching);
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
                    LOGGER.info("[INFO] view info of project id = " + projectDto.getId());

                    //LOGGER.info(successProperty.get());

                    IdentifierSending sending = new IdentifierSending(
                            ProjectListFragment.class,
                            ProjectInfoFragment.class,
                            projectDto.getId());

                    context.send(MainPane.ID, sending);

                    //LOGGER.info(successProperty.get());

                    FragmentSwitching switching = new FragmentSwitching(
                            ProjectListFragment.class,
                            ProjectInfoFragment.class);

                    context.send(MainPane.ID, switching);
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
                            if (newValue) {
                                aboutToDeleteProjects.add(project);
                                selectedProperty.set(selectedProperty.get() + 1);
                            } else {
                                selectedProperty.set(selectedProperty.get() - 1);
                                aboutToDeleteProjects.remove(project);
                            }
                        });
            }
        };
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(cNumber.textProperty(), "label.number");
        LanguageUtil.initLabel(cName.textProperty(), "label.name");
        LanguageUtil.initLabel(cCustomer.textProperty(), "label.customer");
        LanguageUtil.initLabel(cStatus.textProperty(), "label.status");
        LanguageUtil.initLabel(cStart.textProperty(), "label.start");
        LanguageUtil.initLabel(cManagement.textProperty(), "label.management");
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
            LOGGER.info(exception);

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