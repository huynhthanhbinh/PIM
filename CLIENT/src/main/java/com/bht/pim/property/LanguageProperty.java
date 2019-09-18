package com.bht.pim.property;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.configuration.AppConfiguration;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;

/**
 * @author bht
 */
@Component
public final class LanguageProperty implements BaseBean {
    @Getter
    private ObjectProperty<Locale> localeProperty;
    @Getter
    private ObjectProperty<ResourceBundle> resourceBundleProperty;

    public LanguageProperty() {
        localeProperty = new SimpleObjectProperty<>(AppConfiguration.DEFAULT_LOCALE);
        resourceBundleProperty = new SimpleObjectProperty<>(ResourceBundle
                .getBundle(AppConfiguration.LANGUAGE_BUNDLES, AppConfiguration.DEFAULT_LOCALE));

        addEventListener();
    }

    private void addEventListener() {
        localeProperty.addListener((observable, oldValue, newValue) ->
                resourceBundleProperty.set(ResourceBundle.getBundle(
                        AppConfiguration.LANGUAGE_BUNDLES,
                        newValue)));
    }
}
