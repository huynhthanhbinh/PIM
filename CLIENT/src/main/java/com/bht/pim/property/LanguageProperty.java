package com.bht.pim.property;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;

import com.bht.pim.configuration.AppConfiguration;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;

/**
 * @author bht
 */
@Scope("singleton")
public final class LanguageProperty {
    @Getter
    private ObjectProperty<Locale> localeProperty;
    @Getter
    private ObjectProperty<ResourceBundle> resourceBundleProperty;

    public LanguageProperty(Locale locale) {
        localeProperty = new SimpleObjectProperty<>(locale);
        resourceBundleProperty = new SimpleObjectProperty<>(ResourceBundle
                .getBundle(AppConfiguration.LANGUAGE_BUNDLES, locale));

        addEventListener();
    }

    private void addEventListener() {
        localeProperty.addListener((observable, oldValue, newValue) ->
                resourceBundleProperty.set(ResourceBundle.getBundle(
                        AppConfiguration.LANGUAGE_BUNDLES,
                        newValue)));
    }
}
