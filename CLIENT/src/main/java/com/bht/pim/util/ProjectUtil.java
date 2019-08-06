package com.bht.pim.util;

import com.bht.pim.proto.projects.*;
import io.grpc.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import org.apache.log4j.Logger;

import java.util.List;

public class ProjectUtil {

    private static Logger logger = Logger.getLogger(ProjectUtil.class);

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
