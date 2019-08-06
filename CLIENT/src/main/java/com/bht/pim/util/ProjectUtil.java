package com.bht.pim.util;

import com.bht.pim.proto.projects.*;
import com.google.protobuf.Timestamp;
import io.grpc.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProjectUtil {

    private static Logger logger = Logger.getLogger(ProjectUtil.class);
    private static final Timestamp NON_SETUP = Timestamp.newBuilder().build();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private ProjectUtil() {
    }

    // Get all project numbers ====================================
    public static List<Long> getProjectNumbers(Channel channel) {
        ProjectListServiceGrpc.ProjectListServiceBlockingStub stub =
                ProjectListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        return stub.getProjectNumbers(noParam)
                .getProjectNumbersList();
    }

    // Get all of projects
    public static ObservableList<Project> getAllProjects(Channel channel) {

        ProjectListServiceGrpc.ProjectListServiceBlockingStub stub =
                ProjectListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        ProjectList projectList = stub.getProjectList(noParam);

        return FXCollections.observableArrayList(projectList.getProjectsList());
    }

    // Add new project
    public static boolean addNewProject(Channel channel, ProjectInfo projectInfo) {
        ProjectServiceGrpc.ProjectServiceBlockingStub stub =
                ProjectServiceGrpc.newBlockingStub(channel);

        return stub.addNewProject(projectInfo).getIsSuccess();
    }

    // Get a specific project
    public static ProjectInfo getProjectById(Channel channel, long id) {
        ProjectServiceGrpc.ProjectServiceBlockingStub stub =
                ProjectServiceGrpc.newBlockingStub(channel);

        ProjectId projectId = ProjectId.newBuilder().setId(id).build();

        return stub.getProjectById(projectId);
    }

    // Format Date : convert from long to Date
    public static TableCell<Project, Timestamp> dateFormat(TableColumn<Project, Timestamp> column) {
        return new TableCell<Project, Timestamp>() {
            @Override
            protected void updateItem(Timestamp item, boolean empty) {
                if (item == null || empty) {
                    setText(null);
                    setStyle("");

                } else {
                    // Format date
                    if (item.equals(NON_SETUP)) { // Not set date yet
                        setText("         /");

                    } else {
                        LocalDate localDate = DateUtil.toLocalDate(item);
                        setText(DATE_FORMAT.format(localDate));
                    }
                    setStyle("");
                }
            }
        };
    }

    // Format Status : convert from server data to status
    public static TableCell<Project, String> statusFormat(TableColumn<Project, String> column) {
        return new TableCell<Project, String>() {

            @Override
            protected void updateItem(String item, boolean empty) {

                if (item == null || empty) {
                    setText(null);

                } else {
                    switch (item) {
                        case "NEW":
                            setText("New");
                            return;

                        case "PLA":
                            setText("Planned");
                            return;

                        case "INP":
                            setText("In progress");
                            return;

                        case "FIN":
                            setText("Finished");
                            return;

                        default:
                            setText("Not set");
                    }
                }
            }
        };
    }
}
