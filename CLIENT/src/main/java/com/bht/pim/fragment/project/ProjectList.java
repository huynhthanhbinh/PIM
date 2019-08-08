package com.bht.pim.fragment.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.proto.projects.Project;
import com.bht.pim.util.DateUtil;
import com.bht.pim.util.ProjectUtil;
import com.google.protobuf.Timestamp;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        resourceBundleLocation = "bundles.languageBundle",
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
    private TableColumn<Project, String> cName;
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
        log.info("[PIM Client - ProjectList] On init scene ");

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
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
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
    }

    // Add all event-listener
    private void addAllEventListener() {
        table.widthProperty().addListener((source, oldWidth, newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) table.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((observable, oldValue, newValue) ->
                    header.setReordering(false));
        });

        bNew.setOnMouseClicked(event -> {
            log.info("[NEW] on mouse clicked");
            context.send(AppConfiguration.COMPONENT_MAIN, AppConfiguration.FRAGMENT_PROJECT_CREATE);
        });
    }
}
