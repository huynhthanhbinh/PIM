package com.bht.pim.util;

import com.bht.pim.dto.projects.*;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.util.List;

public class ProjectUtil {

    private ProjectUtil() {
    }

    // Get all project numbers ====================================
    public static List<Long> getProjectNumbers(Channel channel) {
        ProjectListServiceGrpc.ProjectListServiceBlockingStub stub =
                ProjectListServiceGrpc.newBlockingStub(channel);

        return stub.getProjectNumbers(Empty.getDefaultInstance())
                .getProjectNumbersList();
    }

    // Get all of projects
    public static ObservableList<Project> getAllProjects(Channel channel) {

        ProjectListServiceGrpc.ProjectListServiceBlockingStub stub =
                ProjectListServiceGrpc.newBlockingStub(channel);

        ProjectList projectList = stub.getProjectList(Empty.getDefaultInstance());

        return FXCollections.observableArrayList(projectList.getProjectsList());
    }

    // Add new project
    public static boolean addNewProject(Channel channel, ProjectInfo projectInfo) {
        ProjectServiceGrpc.ProjectServiceBlockingStub stub =
                ProjectServiceGrpc.newBlockingStub(channel);

        return stub.addNewProject(projectInfo).getValue();
    }

    // Get a specific project
    public static ProjectInfo getProjectById(Channel channel, long id) {
        ProjectServiceGrpc.ProjectServiceBlockingStub stub =
                ProjectServiceGrpc.newBlockingStub(channel);

        Int64Value projectId = Int64Value.newBuilder().setValue(id).build();

        return stub.getProjectById(projectId);
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
