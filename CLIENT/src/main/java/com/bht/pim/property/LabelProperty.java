package com.bht.pim.property;

import java.util.ResourceBundle;

import com.bht.pim.configuration.SpringApplicationContext;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

/**
 * @author bht
 */
public final class LabelProperty {

    @Getter
    private StringProperty stringProperty;
    private LanguageProperty languageProperty;

    public LabelProperty() {
        languageProperty = SpringApplicationContext.getBean(LanguageProperty.class);
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