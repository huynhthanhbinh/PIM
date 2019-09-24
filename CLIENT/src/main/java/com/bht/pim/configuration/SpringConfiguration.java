package com.bht.pim.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.bht.pim.annotation.InheritedComponent;
import com.bht.pim.base.BaseBean;
import com.bht.pim.base.BasePerspective;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;

/**
 *
 * @author bht
 */
@Configuration
@Import(SpringBeanRegistration.class) // import other dependent configuration classes to main configuration
@PropertySource("classpath:/pim.properties") // specify properties files, using together with @Value("${key}")
@ComponentScan(basePackages = SpringConfiguration.BASE_PACKAGE, includeFilters = @ComponentScan.Filter(InheritedComponent.class))
public class SpringConfiguration implements BaseBean {

    @Getter
    @Value("${pim.server.host}")
    private String host; // host of gRPC --> using properties file to read it, see clearly: @PropertySource

    @Getter
    @Value("${pim.server.port}")
    private int port; // port of gRPC --> using properties file to read it, see clearly: @PropertySource

    // default app start-up locale, can be changed later on runtime
    // available languages at the moment: ENGLISH & FRENCH
    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    public static final String BASE_PACKAGE = "com.bht.pim"; // base package of this project
    public static final String LABEL_PIM_MAIN = "label.pim.main"; // label of GUI application
    public static final String LANGUAGE_BUNDLES = "bundles.languageBundle"; // path to languageBundle from root
    public static final BooleanProperty LOGGED_IN_PROPERTY = new SimpleBooleanProperty(false); // check if logged-in yet
    public static final ObjectProperty<BasePerspective> PERSPECTIVE_PROPERTY = new SimpleObjectProperty<>(); // current perspective
}