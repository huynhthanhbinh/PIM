package com.bht.pim.configuration;

import com.bht.pim.proto.employees.EmployeeListServiceGrpc;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.GroupListServiceGrpc;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.ProjectListServiceGrpc;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Log4j
@ComponentScan("com.bht.pim")
@Configuration
public class AppConfiguration {

    public AppConfiguration() {
        log.info("[PIM] On init configuration beans !\n");
    }

    private static final int PORT = 9999;
    private static final String HOST = "localhost";

    // Channel is the abstraction to connect to a service endpoint
    // Let's use plaintext communication because we don't have certs
    private final ManagedChannel channel = ManagedChannelBuilder
            .forAddress(HOST, PORT)
            .usePlaintext()
            .maxInboundMessageSize(10 * 1024 * 1024)
            .build();

    public static final String PERSPECTIVE = "idPIMPerspective";
    public static final String LANGUAGE_BUNDLES = "bundles.languageBundle";

    public static final String TARGET_CONTAINER_TOP = "PTop";
    public static final String TARGET_CONTAINER_LEFT = "PLeft";
    public static final String TARGET_CONTAINER_MAIN = "PMain";

    public static final String COMPONENT_TOP = "idcTop";
    public static final String COMPONENT_LEFT = "idcLeft";
    public static final String COMPONENT_MAIN = "idcMain";

    public static final String FRAGMENT_MAIN_LABEL = "idfMLabel";
    public static final String FRAGMENT_CONFIRM = "idfConfirm"; // ok_cancel

    public static final String FRAGMENT_PROJECT_LIST = "idfPList";
    public static final String FRAGMENT_PROJECT_INFO = "idfPInfo";
    public static final String FRAGMENT_PROJECT_CREATE = "idfPCreate";
    public static final String FRAGMENT_PROJECT_UPDATE = "idfPUpdate";

    public static final String FRAGMENT_GROUP_LIST = "idfGList";
    public static final String FRAGMENT_GROUP_INFO = "idfGInfo";

    public static final String FRAGMENT_EMPLOYEE_LIST = "idfEList";
    public static final String FRAGMENT_EMPLOYEE_INFO = "idfEInfo";

    public static final String LABEL_PROJECT_LIST = "PROJECT LIST";
    public static final String LABEL_PROJECT_CREATE = "NEW PROJECT";
    public static final String LABEL_PROJECT_INFO = "PROJECT INFORMATION";
    public static final String LABEL_PROJECT_UPDATE = "UPDATE PROJECT";

    @Bean
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub() {
        log.info("[PIM] Creating bean of < EmployeeServiceBlockingStub >");
        return EmployeeServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public EmployeeListServiceGrpc.EmployeeListServiceBlockingStub employeeListServiceBlockingStub() {
        log.info("[PIM] Creating bean of < EmployeeListServiceBlockingStub >");
        return EmployeeListServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public GroupServiceGrpc.GroupServiceBlockingStub groupServiceBlockingStub() {
        log.info("[PIM] Creating bean of < GroupServiceBlockingStub >");
        return GroupServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public GroupListServiceGrpc.GroupListServiceBlockingStub groupListServiceBlockingStub() {
        log.info("[PIM] Creating bean of < GroupListServiceBlockingStub >");
        return GroupListServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public ProjectServiceGrpc.ProjectServiceBlockingStub projectServiceBlockingStub() {
        log.info("[PIM] Creating bean of < ProjectServiceBlockingStub >");
        return ProjectServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public ProjectListServiceGrpc.ProjectListServiceBlockingStub projectListServiceBlockingStub() {
        log.info("[PIM] Creating bean of < ProjectListServiceBlockingStub >");
        return ProjectListServiceGrpc.newBlockingStub(channel);
    }
}