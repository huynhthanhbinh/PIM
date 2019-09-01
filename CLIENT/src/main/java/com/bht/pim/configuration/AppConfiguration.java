package com.bht.pim.configuration;

import com.bht.pim.mapper.*;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import com.bht.pim.util.LanguageUtil;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.beans.property.*;
import lombok.extern.log4j.Log4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Log4j
@Configuration
@SuppressWarnings("all")
@ComponentScan("com.bht.pim")
@PropertySource("classpath:/pim.properties")
public class AppConfiguration {

    public static final LanguageProperty LANGUAGE_PROPERTY = new LanguageProperty(Locale.FRENCH);

    @PostConstruct
    public void init() {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        CHANNEL_PROPERTY.set(ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .maxInboundMessageSize(10 * 1024 * 1024)
                .build());

        LanguageUtil.initLabel(INFORMATION_TITLE, "label.notification.information");
        LanguageUtil.initLabel(SUCCESS_TITLE, "label.notification.success");
        LanguageUtil.initLabel(WARNING_TITLE, "label.notification.warning");
        LanguageUtil.initLabel(ERROR_TITLE, "label.notification.error");
    }

    @Value("${pim.server.host}")
    private String host; // host of gRPC

    @Value("${pim.server.port}")
    private int port; // port of gRPC

    public static final ObjectProperty<ManagedChannel> CHANNEL_PROPERTY = new SimpleObjectProperty<>(); // connect to gRPC server
    public static final BooleanProperty LOGGED_IN_PROPERTY = new SimpleBooleanProperty(false); // check if logged-in yet

    public static final String PERSPECTIVE_PIM = "idPIMPerspective";
    public static final String PERSPECTIVE_DEFAULT = "idPIMDefault";
    public static final String LANGUAGE_BUNDLES = "bundles.languageBundle";

    public static final String FRAGMENT_SUPPLEMENTARY_LOGIN = "idfSLogin";
    public static final String FRAGMENT_SUPPLEMENTARY_ERROR = "idfSError";

    public static final String LABEL_PIM_MAIN = "label.pim.main";
    public static final String LABEL_LEFT_PROJECT_LIST = "label.pim.left.list.project";

    public static final StringProperty INFORMATION_TITLE = new SimpleStringProperty();
    public static final StringProperty SUCCESS_TITLE = new SimpleStringProperty();
    public static final StringProperty WARNING_TITLE = new SimpleStringProperty();
    public static final StringProperty ERROR_TITLE = new SimpleStringProperty();

    @Bean
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub() {
        log.info("[PIM] Creating bean of < EmployeeServiceBlockingStub >");
        return EmployeeServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
    }

    @Bean
    public GroupServiceGrpc.GroupServiceBlockingStub groupServiceBlockingStub() {
        log.info("[PIM] Creating bean of < GroupServiceBlockingStub >");
        return GroupServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
    }

    @Bean
    public ProjectServiceGrpc.ProjectServiceBlockingStub projectServiceBlockingStub() {
        log.info("[PIM] Creating bean of < ProjectServiceBlockingStub >");
        return ProjectServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
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
}