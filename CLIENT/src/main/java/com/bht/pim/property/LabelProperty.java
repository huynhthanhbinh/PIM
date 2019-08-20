package com.bht.pim.property;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
@Scope("prototype")
public class LabelProperty {
    @Autowired
    private LanguageProperty languageProperty;
    @Getter
    private StringProperty stringProperty;

    public LabelProperty() {
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
