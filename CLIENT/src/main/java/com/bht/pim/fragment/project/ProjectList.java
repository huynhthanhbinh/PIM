package com.bht.pim.fragment.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.message.impl.MainLabelUpdating;
import com.bht.pim.proto.projects.Project;
import com.bht.pim.util.DateUtil;
import com.bht.pim.util.ProjectUtil;
import com.google.protobuf.Timestamp;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_LIST,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/project/ProjectList.fxml")
public class ProjectList implements Initializable {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";

    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;
    @FXML
    private VBox mainPane;
    @FXML
    private Button bDelete;
    @FXML
    private Button bNew;
    @FXML
    private TableView<Project> table;
    @FXML
    private TableColumn<Project, Project> cSelect;
    @FXML
    private TableColumn<Project, Long> cNumber;
    @FXML
    private TableColumn<Project, Project> cName;
    @FXML
    private TableColumn<Project, String> cCustomer;
    @FXML
    private TableColumn<Project, String> cStatus;
    @FXML
    private TableColumn<Project, Timestamp> cStart;
    @FXML
    private TableColumn<Project, Project> cManagement;

    private ManagedChannel channel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Init this scene code go here
        log.info("[Project List] On init scene ");

        MainLabelUpdating mainLabelUpdating = new MainLabelUpdating(
                AppConfiguration.FRAGMENT_PROJECT_LIST,
                AppConfiguration.LABEL_PROJECT_LIST);

        context.send(AppConfiguration.COMPONENT_MAIN, mainLabelUpdating);

        Pane main = context.getComponentLayout().getGlassPane();
        mainPane.prefWidthProperty().bind(main.widthProperty().subtract(227));
        mainPane.prefHeightProperty().bind(main.heightProperty().subtract(100));


        // Get all necessary data from server
        getNecessaryData();

        // Init all inputs
        initAllFields();

        // Add all event-listener
        addAllEventListener();
    }


    // Get all necessary data
    private void getNecessaryData() {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        table.setItems(ProjectUtil.getAllProjects(channel));

        // Turn off connection
        channel.shutdown();
    }

    // Init all table fields
    private void initAllFields() {

        //bNew.setOnAction(event -> ((Stage) bNew.getScene().getWindow()).setScene());

        cSelect.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.05));
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
        cStatus.setCellFactory(ProjectUtil::statusFormat);
        cStatus.setResizable(false);

        cStart.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        cStart.setCellFactory(DateUtil::dateFormat);
        cStart.setResizable(false);

        cManagement.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
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

        bNew.setOnMouseClicked(event -> {
            log.info("[NEW] on mouse clicked");

            FragmentSwitching switching = new FragmentSwitching(
                    AppConfiguration.FRAGMENT_PROJECT_LIST,
                    AppConfiguration.FRAGMENT_PROJECT_CREATE);

            context.send(AppConfiguration.COMPONENT_MAIN, switching);
        });
    }

    // Button delete on table row
    private TableCell<Project, Project> management(TableColumn<Project, Project> param) {
        return new TableCell<Project, Project>() {

            private final Button bRemove = new Button(" X ");

            @Override
            protected void updateItem(Project project, boolean empty) {
                if (project == null || empty || !project.getStatus().equals("NEW")) {
                    setGraphic(null);
                    return;
                }

                setGraphic(bRemove);
                bRemove.setOnAction(event -> {
                    log.info("Delete " + project);
                });
            }
        };
    }

    // Name label - clickable
    private TableCell<Project, Project> name(TableColumn<Project, Project> param) {
        return new TableCell<Project, Project>() {

            private Label lName = new Label();

            @Override
            protected void updateItem(Project project, boolean empty) {
                if (project == null || empty) {
                    setGraphic(null);
                    return;
                }

                lName.setText(project.getName());
                lName.getStyleClass().add("clickable");
                lName.setPickOnBounds(false);
                lName.setOnMouseClicked(event -> {
                    log.info("INFO of project: " + project.getId());
                });

                setGraphic(lName);
            }
        };
    }
}
