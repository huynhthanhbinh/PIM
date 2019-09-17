package com.bht.pim.util;

import java.util.Objects;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.property.LabelProperty;
import com.bht.pim.property.LanguageProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author bht
 */
public final class LanguageUtil {

    private LanguageUtil() {
    }

    private static LanguageProperty languageProperty = AppConfiguration.LANGUAGE_PROPERTY;

    // init a label for multilingual purpose
    public static void initLabel(StringProperty textProperty, final String key) {
        LabelProperty labelProperty = new LabelProperty();
        labelProperty.setBundleKey(key);
        textProperty.bind(labelProperty.getStringProperty());
    }


    // init a label for multilingual purpose
    public static void initLabel(ObjectProperty<String> textProperty, final String key) {
        initLabel(toStringProperty(textProperty), key);
    }


    public static StringProperty getTextPropertyOfKey(String key) {
        StringProperty textProperty = new SimpleStringProperty();
        LanguageUtil.initLabel(textProperty, key);
        return textProperty;
    }


    public static String getCurrentLabelOfKey(String key) {
        return languageProperty.getResourceBundleProperty().get().getString(key);
    }


    private static StringProperty toStringProperty(final Property<String> property) {
        Objects.requireNonNull(property, "Property cannot be null");
        StringProperty stringProperty = new SimpleStringProperty(property.getValue());
        property.bind(stringProperty);
        return stringProperty;
    }
}