package com.bht.pim.util;

import java.util.Objects;

import com.bht.pim.property.LabelProperty;
import com.bht.pim.property.LanguageProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * for making all labels multilingual available
 *
 * @author bht
 */
public final class LanguageUtil {

    private LanguageUtil() {
    }

    private static LanguageProperty languageProperty = LanguageProperty.getCurrentInstance();

    // init a label for multilingual purpose
    public static void initLabel(StringProperty textProperty, final String key) {
        LabelProperty labelProperty = new LabelProperty(key);
        initLabel(textProperty, labelProperty.getStringProperty());
    }


    public static void initLabel(ObjectProperty<String> textProperty, final String key) {
        initLabel(toStringProperty(textProperty), key);
    }


    public static void initLabel(StringProperty textProperty, StringProperty targetProperty) {
        textProperty.bind(targetProperty);
    }


    public static StringProperty getTextPropertyOfKey(String key) {
        StringProperty textProperty = new SimpleStringProperty();
        initLabel(textProperty, key);
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