package com.bht.pim.property;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseBean;
import com.bht.pim.spring.SpringApplicationContext;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;

/**
 *
 * @author bht
 */
@Component(LanguageProperty.ID)
@DependsOn(SpringApplicationContext.ID)
public final class LanguageProperty implements BaseBean {

    public static final String ID = "languageProperty";

    @Getter
    private ObjectProperty<Locale> localeProperty;

    @Getter
    private ObjectProperty<ResourceBundle> resourceBundleProperty;

    @Override
    public void initialize() throws IOException {
        BaseBean.super.initialize();

        localeProperty = new SimpleObjectProperty<>(AppConfiguration.DEFAULT_LOCALE);
        resourceBundleProperty = new SimpleObjectProperty<>(ResourceBundle.getBundle(
                AppConfiguration.LANGUAGE_BUNDLES,
                AppConfiguration.DEFAULT_LOCALE));

        addEventListener();
    }

    private void addEventListener() {
        localeProperty.addListener((observable, oldValue, newValue) ->
                resourceBundleProperty.set(ResourceBundle.getBundle(
                        AppConfiguration.LANGUAGE_BUNDLES,
                        newValue)));
    }

    public static LanguageProperty getCurrentInstance() {
        return SpringApplicationContext.getBean(LanguageProperty.class);
    }
}