package com.bht.pim.property;

import com.bht.pim.configuration.AppConfiguration;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import org.springframework.context.annotation.Scope;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author bht
 */
@Scope("singleton")
public class LanguageProperty {
    @Getter
    private ObjectProperty<Locale> localeProperty;
    @Getter
    private ObjectProperty<ResourceBundle> resourceBundleProperty;

    public LanguageProperty(Locale locale) {
        localeProperty = new SimpleObjectProperty<>(locale);
        resourceBundleProperty = new SimpleObjectProperty<>(ResourceBundle
                .getBundle(AppConfiguration.LANGUAGE_BUNDLES, locale));

        addEventListeners();
    }

    private void addEventListeners() {
        localeProperty.addListener((observable, oldValue, newValue) ->
                resourceBundleProperty.set(ResourceBundle.getBundle(
                        AppConfiguration.LANGUAGE_BUNDLES,
                        newValue)));
    }
}
