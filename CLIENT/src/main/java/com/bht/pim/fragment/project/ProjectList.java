package com.bht.pim.fragment.project;

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
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ProjectList implements Initializable {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";

    @FXML
    public Button bDelete;
    @FXML
    public Button bNew;
    @FXML
    public TableView<Project> table;
    @FXML
    public TableColumn<Project, Project> cSelect;
    @FXML
    public TableColumn<Project, Long> cNumber;
    @FXML
    public TableColumn<Project, String> cName;
    @FXML
    public TableColumn<Project, String> cCustomer;
    @FXML
    public TableColumn<Project, String> cStatus;
    @FXML
    public TableColumn<Project, Timestamp> cStart;
    @FXML
    public TableColumn<Project, Timestamp> cEnd;
    @FXML
    public TableColumn<Project, Project> cManagement;

    private Logger logger = Logger.getLogger(ProjectList.class);
    private ManagedChannel channel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Init this scene code go here
        logger.info("[PIM Client - ProjectCreate] On init scene ");

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
        cSelect.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.05));
        cSelect.setResizable(false);

        cNumber.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        cNumber.setResizable(false);

        cName.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.25));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cName.setResizable(false);

        cCustomer.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.2));
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

        cEnd.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
        cEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        cEnd.setCellFactory(DateUtil::dateFormat);
        cEnd.setResizable(false);

        cManagement.prefWidthProperty().bind(table.widthProperty().subtract(18).multiply(0.1));
    }

    // Add all event-listener
    private void addAllEventListener() {
        table.widthProperty().addListener((source, oldWidth, newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) table.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((observable, oldValue, newValue) ->
                    header.setReordering(false));
        });
    }
}
