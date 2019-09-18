package com.bht.pim.configuration;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.bht.pim.annotation.InheritedComponent;
import com.bht.pim.base.BaseBean;
import com.bht.pim.base.BasePerspective;
import com.bht.pim.property.FormatProperty;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import com.bht.pim.util.LanguageUtil;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author bht
 */
@Log4j
@Configuration
@PropertySource("classpath:/pim.properties")
@ComponentScan(basePackages = "com.bht.pim", includeFilters = @ComponentScan.Filter(InheritedComponent.class))
public class AppConfiguration implements BaseBean {

    // 2 locale are available at the moment: Locale.English & Locale.French
    // This property is set default locale when turn on the application
    // We can change this locale later on application run time
    public static final LanguageProperty LANGUAGE_PROPERTY = new LanguageProperty(Locale.ENGLISH);

    // DateFormat at this moment, can expand more in the future
    // Depends on current locale, format will be change correspondingly
    // Eg. for dateFormat: FR - dd/MM/yyyy , US - MM/dd/yyyy
    public static final FormatProperty FORMAT_PROPERTY = new FormatProperty();

    @PostConstruct
    public void init() {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        CHANNEL_PROPERTY.set(ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .maxInboundMessageSize(10 * 1024 * 1024)
                .build());

        // Init label for notification util
        // This will use to show notification of application
        LanguageUtil.initLabel(INFORMATION_TITLE, "label.notification.information");
        LanguageUtil.initLabel(SUCCESS_TITLE, "label.notification.success");
        LanguageUtil.initLabel(WARNING_TITLE, "label.notification.warning");
        LanguageUtil.initLabel(ERROR_TITLE, "label.notification.error");
    }

    @Value("${pim.server.host}")
    private String host; // host of gRPC

    @Value("${pim.server.port}")
    private int port; // port of gRPC

    public static final String LANGUAGE_BUNDLES = "bundles.languageBundle";
    public static final ObjectProperty<BasePerspective> PERSPECTIVE_PROPERTY = new SimpleObjectProperty<>(); // current perspective
    public static final ObjectProperty<ManagedChannel> CHANNEL_PROPERTY = new SimpleObjectProperty<>(); // connect to gRPC server
    public static final BooleanProperty LOGGED_IN_PROPERTY = new SimpleBooleanProperty(false); // check if logged-in yet

    public static final String LABEL_PIM_MAIN = "label.pim.main";
    public static final String PERSPECTIVE_PIM = "idPIMPerspective";
    public static final String PERSPECTIVE_DEFAULT = "idDefaultPerspective";

    public static final StringProperty INFORMATION_TITLE = new SimpleStringProperty();
    public static final StringProperty SUCCESS_TITLE = new SimpleStringProperty();
    public static final StringProperty WARNING_TITLE = new SimpleStringProperty();
    public static final StringProperty ERROR_TITLE = new SimpleStringProperty();

    @Bean
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub() {
        log.info("[SPRING] BeanCreation: EmployeeServiceBlockingStub");
        return EmployeeServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
    }

    @Bean
    public GroupServiceGrpc.GroupServiceBlockingStub groupServiceBlockingStub() {
        log.info("[SPRING] BeanCreation: GroupServiceBlockingStub");
        return GroupServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
    }

    @Bean
    public ProjectServiceGrpc.ProjectServiceBlockingStub projectServiceBlockingStub() {
        log.info("[SPRING] BeanCreation: ProjectServiceBlockingStub");
        return ProjectServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
    }
}