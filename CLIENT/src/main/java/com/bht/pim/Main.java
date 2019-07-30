package com.bht.pim;

import com.bht.pim.proto.employee.*;
import com.bht.pim.proto.employee.NoParam;
import com.bht.pim.proto.group.*;
import com.bht.pim.proto.group.Success;
import com.bht.pim.proto.project.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class Main extends Application {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";
    private ManagedChannel channel;

    private Logger logger = Logger.getLogger(Main.class);
    private Parent rootNode;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        channel = ManagedChannelBuilder
                .forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        logger.info(channel);

        logger.info("<<< PIM CLIENT - ON INIT  >>>");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getClassLoader()
                .getResource("templates/sample.fxml"));

        rootNode = fxmlLoader.load();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("<<< PIM CLIENT - ON START >>>");
        showWindow(primaryStage);

        // Get 1 employee info ====================================

        EmployeeServiceGrpc.EmployeeServiceBlockingStub stub =
                EmployeeServiceGrpc.newBlockingStub(channel);

        EmployeeId employeeId = EmployeeId.newBuilder()
                .setId(2)
                .build();

        EmployeeInfo employee = stub.getEmployeeById(employeeId);

        logger.info(employee);

        employee.getEnrolledProjectsList().forEach(project -> logger.info(project.getName()));

        // Get 1 group info =======================================

        GroupServiceGrpc.GroupServiceBlockingStub stub1 =
                GroupServiceGrpc.newBlockingStub(channel);

        GroupId groupId = GroupId.newBuilder()
                .setId(1)
                .build();

        GroupInfo group = stub1.getGroupById(groupId);

        logger.info(group);

        group.getEnrolledProjectsList().forEach(project -> logger.info(project.getName()));

        // Get 1 project info =====================================

        ProjectServiceGrpc.ProjectServiceBlockingStub stub2 =
                ProjectServiceGrpc.newBlockingStub(channel);

        ProjectId projectId = ProjectId.newBuilder()
                .setId(4)
                .build();

        ProjectInfo project = stub2.getProjectById(projectId);

        logger.info(project);

        project.getEmployeesList().forEach(employee1 -> logger.info(employee1.getVisa()));

        // Get employee list =======================================

        EmployeeListServiceGrpc.EmployeeListServiceBlockingStub stub3 =
                EmployeeListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        EmployeeList employeeList = stub3.getEmployeeList(noParam);

        logger.info(employeeList);

        employeeList.getEmployeeListList().forEach(employee1 -> logger.info(employee1.getVisa()));

        // Get group list ============================================

        GroupListServiceGrpc.GroupListServiceBlockingStub stub4 =
                GroupListServiceGrpc.newBlockingStub(channel);

        com.bht.pim.proto.group.NoParam noParam1 =
                com.bht.pim.proto.group.NoParam.newBuilder().build();

        GroupList groupList = stub4.getGroupList(noParam1);

        logger.info(groupList);

        groupList.getGroupListList().forEach(group1 -> logger.info(group1.getGroupLeaderName()));

        // Get project list ==========================================

        ProjectListServiceGrpc.ProjectListServiceBlockingStub stub5 =
                ProjectListServiceGrpc.newBlockingStub(channel);

        com.bht.pim.proto.project.NoParam noParam2 =
                com.bht.pim.proto.project.NoParam.newBuilder().build();

        ProjectList projectList = stub5.getProjectList(noParam2);

        projectList.getProjectListList().forEach(project1 -> logger.info(project1.getName()));

        // Get all project numbers ====================================

        List<Long> projectNumbers = stub5
                .getProjectNumbers(noParam2)
                .getProjectNumbersList();

        logger.info(projectNumbers);

        projectNumbers.forEach(logger::info);

        // Add a new group ============================================

        Group newGroup = Group.newBuilder()
                .setGroupLeaderId(2)
                .build();

        Success success = stub1.addNewGroup(newGroup);
        logger.info(success.getIsSuccess());
    }


    @Override
    public void stop() {
        logger.info("<<< PIM CLIENT - ON STOP  >>>");
        channel.shutdown();
    }


    private void showWindow(Stage window) {
        logger.info("<<< PIM CLIENT - ON SHOW  >>>");

        ClassLoader classLoader = getClass().getClassLoader();

        window.setTitle("Project Information Management");
        window.getIcons().add(
                new Image(Objects.requireNonNull(classLoader
                        .getResourceAsStream("pictures/icon.png"))));

        Scene scene = new Scene(rootNode, 800, 600);
        window.setResizable(true);
        window.setScene(scene);
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
}
