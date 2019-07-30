package com.bht.pim;

import com.bht.pim.proto.employee.*;
import com.bht.pim.proto.group.GroupId;
import com.bht.pim.proto.group.GroupInfo;
import com.bht.pim.proto.group.GroupServiceGrpc;
import com.bht.pim.proto.project.ProjectId;
import com.bht.pim.proto.project.ProjectInfo;
import com.bht.pim.proto.project.ProjectList;
import com.bht.pim.proto.project.ProjectServiceGrpc;
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


        EmployeeServiceGrpc.EmployeeServiceBlockingStub stub =
                EmployeeServiceGrpc.newBlockingStub(channel);

        EmployeeId employeeId = EmployeeId.newBuilder()
                .setId(2)
                .build();

        EmployeeInfo employee = stub.getEmployeeById(employeeId);

        logger.info(employee);

        employee.getEnrolledProjectsList().forEach(project -> logger.info(project.getName()));


        GroupServiceGrpc.GroupServiceBlockingStub stub1 =
                GroupServiceGrpc.newBlockingStub(channel);

        GroupId groupId = GroupId.newBuilder()
                .setId(1)
                .build();

        GroupInfo group = stub1.getGroupById(groupId);

        logger.info(group);

        group.getEnrolledProjectsList().forEach(project -> logger.info(project.getName()));

        ProjectServiceGrpc.ProjectServiceBlockingStub stub2 =
                ProjectServiceGrpc.newBlockingStub(channel);

        ProjectId projectId = ProjectId.newBuilder()
                .setId(4)
                .build();

        ProjectInfo project = stub2.getProjectById(projectId);

        logger.info(project);

        project.getEmployeesList().forEach(employee1 -> logger.info(employee1.getVisa()));

        EmployeeListServiceGrpc.EmployeeListServiceBlockingStub stub3 =
                EmployeeListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        EmployeeList employeeList = stub3.getEmployeeList(noParam);

        logger.info(employeeList);

        employeeList.getEmployeeListList().forEach(employee1 -> logger.info(employee1.getVisa()));
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
