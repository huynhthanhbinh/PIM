package com.bht.pim.util;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.property.LabelProperty;
import com.bht.pim.property.LanguageProperty;
import javafx.beans.property.StringProperty;

public class LanguageUtil {

    private static LanguageProperty languageProperty = AppConfiguration.LANGUAGE_PROPERTY;

    public static void initLabel(StringProperty textProperty, final String key) {
        LabelProperty labelProperty = new LabelProperty();
        labelProperty.setBundleKey(key);
        textProperty.bind(labelProperty.getStringProperty());
    }

    public static String getCurrentLabelOfKey(String key) {
        return languageProperty.getResourceBundleProperty().get().getString(key);
    }
}
