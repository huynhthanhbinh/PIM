package com.bht.pim.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.bht.pim.annotation.InheritedComponent;
import com.bht.pim.base.BaseBean;
import com.bht.pim.base.BasePerspective;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.ProjectServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author bht
 */
@Configuration
@PropertySource("classpath:/pim.properties") // specify properties files, using together with @Value("${key}")
@ComponentScan(basePackages = "com.bht.pim", includeFilters = @ComponentScan.Filter(InheritedComponent.class))
public class AppConfiguration implements BaseBean {

    // default app start-up locale, can be changed later on runtime
    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    // other constants
    public static final String LABEL_PIM_MAIN = "label.pim.main"; // label of GUI application
    public static final String LANGUAGE_BUNDLES = "bundles.languageBundle"; // path to languageBundle from root
    public static final ObjectProperty<BasePerspective> PERSPECTIVE_PROPERTY = new SimpleObjectProperty<>(); // current perspective
    public static final ObjectProperty<ManagedChannel> CHANNEL_PROPERTY = new SimpleObjectProperty<>(); // connect to gRPC server
    public static final BooleanProperty LOGGED_IN_PROPERTY = new SimpleBooleanProperty(false); // check if logged-in yet

    @Value("${pim.server.host}")
    private String host; // host of gRPC --> using properties file to read it, see clearly: @PropertySource

    @Value("${pim.server.port}")
    private int port; // port of gRPC --> using properties file to read it, see clearly: @PropertySource

    @Override
    public void initialize() {
        BaseBean.super.initialize();
        CHANNEL_PROPERTY.set(ManagedChannelBuilder      // Channel is the abstraction to connect to a service endpoint
                .forAddress(host, port)                 // Port and Host of gRPC server, not of client !
                .usePlaintext()                         // Let's use plaintext communication because we don't have certs
                .maxInboundMessageSize(10 * 1024 * 1024)// 10KB * 1024 = 10MB --> max message size to transfer together
                .build());                              // Builder-design-pattern --> using build method to get object
    }

    @Override
    public void destroy() {
        BaseBean.super.destroy();
        CHANNEL_PROPERTY.get().shutdown();              // Closing current connection between this app with Server
    }

    @Bean
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub() { // for autowiring service
        return EmployeeServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
    }

    @Bean
    public GroupServiceGrpc.GroupServiceBlockingStub groupServiceBlockingStub() { // for autowiring service
        return GroupServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
    }

    @Bean
    public ProjectServiceGrpc.ProjectServiceBlockingStub projectServiceBlockingStub() { // for autowiring service
        return ProjectServiceGrpc.newBlockingStub(CHANNEL_PROPERTY.get());
    }
}