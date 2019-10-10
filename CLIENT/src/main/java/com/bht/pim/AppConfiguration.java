package com.bht.pim;

import java.util.Locale;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.bht.pim.annotation.InheritedComponent;
import com.bht.pim.base.BaseBean;
import com.bht.pim.base.BasePerspective;
import com.bht.pim.spring.SpringBeanRegistration;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author bht
 */
@Configuration
@SuppressWarnings("squid:S1118")
@Import(SpringBeanRegistration.class) // import other dependent configuration classes to main configuration
@PropertySource("classpath:/pim.properties") // specify properties files, using together with @Value("${key}")
@ComponentScan(basePackages = AppConfiguration.BASE_PACKAGE, includeFilters = @ComponentScan.Filter(InheritedComponent.class))
public class AppConfiguration implements BaseBean {

    // default app start-up locale, can be changed later on runtime
    // available languages at the moment: ENGLISH & FRENCH
    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    public static final String BASE_PACKAGE = "com.bht.pim"; // base package of this project
    public static final String LABEL_PIM_MAIN = "label.pim.main"; // multilingual properties key of GUI application's label
    public static final String LANGUAGE_BUNDLES = "bundles.languageBundle"; // path to languageBundle from root
    public static final BooleanProperty LOGGED_IN_PROPERTY = new SimpleBooleanProperty(false); // check if logged-in yet
    public static final ObjectProperty<BasePerspective> PERSPECTIVE_PROPERTY = new SimpleObjectProperty<>(); // current perspective
}