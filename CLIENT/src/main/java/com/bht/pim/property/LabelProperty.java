package com.bht.pim.property;

import com.bht.pim.configuration.AppConfiguration;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

import java.util.ResourceBundle;

public class LabelProperty {

    @Getter
    private StringProperty stringProperty;
    private LanguageProperty languageProperty;

    public LabelProperty() {
        languageProperty = AppConfiguration.LANGUAGE_PROPERTY;
        stringProperty = new SimpleStringProperty();
    }

    public void setBundleKey(String bundleKey) {
        ObjectProperty<ResourceBundle> resourceBundleProperty =
                languageProperty.getResourceBundleProperty();

        resourceBundleProperty.addListener((observable, oldValue, newValue) ->
                stringProperty.set(resourceBundleProperty.get()
                        .getString(bundleKey)));

        stringProperty.set(resourceBundleProperty.get().getString(bundleKey));
    }
}
