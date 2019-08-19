package com.bht.pim.configuration;

import com.bht.pim.mapper.*;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.log4j.Log4j;
import org.mapstruct.factory.Mappers;
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

    // Channel is the abstraction to connect to a service endpoint
    // Let's use plaintext communication because we don't have certs
    private final ManagedChannel channel = ManagedChannelBuilder
            .forAddress(HOST, PORT)
            .usePlaintext()
            .maxInboundMessageSize(10 * 1024 * 1024)
            .build();

    // Network configuration ======================================================
    private static final int PORT = 9999;
    private static final String HOST = "localhost";

    // Configurations =============================================================
    public static final String PERSPECTIVE = "idPIMPerspective";
    public static final String LANGUAGE_BUNDLES = "bundles.languageBundle";

    // Containers =================================================================
    public static final String TARGET_CONTAINER_TOP = "paneTop";
    public static final String TARGET_CONTAINER_LEFT = "paneLeft";
    public static final String TARGET_CONTAINER_MAIN = "paneMain";

    // Components =================================================================
    public static final String COMPONENT_TOP = "idcTop";
    public static final String COMPONENT_LEFT = "idcLeft";
    public static final String COMPONENT_MAIN = "idcMain";

<<<<<<< HEAD
    // Main labels ================================================================
    public static final String LABEL_EMPLOYEE_LIST = "EMPLOYEE LIST";
    public static final String LABEL_EMPLOYEE_INFO = "EMPLOYEE INFORMATION";

    public static final String LABEL_GROUP_LIST = "GROUP LIST";
    public static final String LABEL_GROUP_INFO = "GROUP INFORMATION";

    public static final String LABEL_PROJECT_LIST = "PROJECT LIST";
    public static final String LABEL_PROJECT_CREATE = "NEW PROJECT";
    public static final String LABEL_PROJECT_INFO = "PROJECT INFORMATION";
    public static final String LABEL_PROJECT_UPDATE = "UPDATE PROJECT";

    // Parent fragments ===========================================================
=======
    public static final String FRAGMENT_MAIN_LABEL = "idfMLabel";
    public static final String FRAGMENT_CONFIRM = "idfConfirm"; // ok_cancel

>>>>>>> parent of f33f974... [ProjectCreate] Saving Project
    public static final String FRAGMENT_PROJECT_LIST = "idfPList";
    public static final String FRAGMENT_PROJECT_INFO = "idfPInfo";
    public static final String FRAGMENT_PROJECT_CREATE = "idfPCreate";
    public static final String FRAGMENT_PROJECT_UPDATE = "idfPUpdate";

    public static final String FRAGMENT_GROUP_LIST = "idfGList";
    public static final String FRAGMENT_GROUP_INFO = "idfGInfo";

    public static final String FRAGMENT_EMPLOYEE_LIST = "idfEList";
    public static final String FRAGMENT_EMPLOYEE_INFO = "idfEInfo";

    // Child fragments ============================================================
    public static final String FRAGMENT_MAIN_LABEL = "idfMLabel";
    public static final String FRAGMENT_CONFIRM = "idfConfirm"; // ok_cancel
    public static final String FRAGMENT_PAGINATION = "idfPagination";

    public static final String FRAGMENT_EMPLOYEE_DETAIL = "idfEDetail";
    public static final String FRAGMENT_EMPLOYEE_LIST_TABLE = "idfEListTable";

    public static final String FRAGMENT_GROUP_DETAIL = "idfGDetail";
    public static final String FRAGMENT_GROUP_LIST_TABLE = "idfGListTable";

    public static final String FRAGMENT_PROJECT_DETAIL = "idfPDetail";
    public static final String FRAGMENT_PROJECT_LIST_TABLE = "idfPListTable";
    public static final String FRAGMENT_PROJECT_EDITABLE_FORM = "idfPEditableForm";
    public static final String FRAGMENT_PROJECT_LIST_UTIL = "idfPListUtil";

//    // Children-fragment-nodes ====================================================
//    public static final Node MAIN_LABEL = getChildFragment("label/MainLabel");
//    public static final Node CONFIRM_BOX = getChildFragment("confirm/Confirm");
//    public static final Node PAGINATION = getChildFragment("pagination/PimPagination");
//
//    public static final Node EMPLOYEE_DETAIL = getChildFragment("employee/EmployeeDetail");
//    public static final Node EMPLOYEE_LIST_TABLE = getChildFragment("employee/EmployeeListTable");
//
//    public static final Node GROUP_DETAIL = getChildFragment("group/GroupDetail");
//    public static final Node GROUP_LIST_TABLE = getChildFragment("group/GroupListTable");
//
//    public static final Node PROJECT_DETAIL = getChildFragment("project/ProjectDetail");
//    public static final Node PROJECT_LIST_TABLE = getChildFragment("project/ProjectListTable");
//    public static final Node PROJECT_EDITABLE_FORM = getChildFragment("project/ProjectEditableForm");
//    public static final Node PROJECT_LIST_UTIL = getChildFragment("project/ProjectListUtil");

    // Spring Beans injecting/autowiring config ===================================
    @Bean
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub() {
        log.info("[PIM] Creating bean of < EmployeeServiceBlockingStub >");
        return EmployeeServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public GroupServiceGrpc.GroupServiceBlockingStub groupServiceBlockingStub() {
        log.info("[PIM] Creating bean of < GroupServiceBlockingStub >");
        return GroupServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public ProjectServiceGrpc.ProjectServiceBlockingStub projectServiceBlockingStub() {
        log.info("[PIM] Creating bean of < ProjectServiceBlockingStub >");
        return ProjectServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public StatusMapper statusMapper() {
        log.info("[PIM] Creating bean of < StatusMapper >");
        return Mappers.getMapper(StatusMapper.class);
    }

    @Bean
    public DateTimeMapper dateTimeMapper() {
        log.info("[PIM] Creating bean of < DateTimeMapper >");
        return Mappers.getMapper(DateTimeMapper.class);
    }

    @Bean
    public EmployeeMapper employeeMapper() {
        log.info("[PIM] Creating bean of < EmployeeMapper >");
        return Mappers.getMapper(EmployeeMapper.class);
    }

    @Bean
    public GroupMapper groupMapper() {
        log.info("[PIM] Creating bean of < GroupMapper >");
        return Mappers.getMapper(GroupMapper.class);
    }

    @Bean
    public ProjectMapper projectMapper() {
        log.info("[PIM] Creating bean of < ProjectMapper >");
        return Mappers.getMapper(ProjectMapper.class);
    }

//    // Get child-fragment-node
//    private static Node getChildFragment(String path) {
//        try {
//            return FXMLLoader.load(Objects.requireNonNull(AppConfiguration.class.getClassLoader()
//                    .getResource("com/bht/pim/fragment/children/" + path + ".fxml")));
//        } catch (IOException e) {
//            log.info(e);
//            return null;
//        }
//    }
}