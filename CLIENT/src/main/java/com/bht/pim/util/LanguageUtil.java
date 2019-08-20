package com.bht.pim.util;

import com.bht.pim.property.LabelProperty;
import javafx.beans.property.StringProperty;

public class LanguageUtil {

    public static void initLabel(StringProperty textProperty, final String key) {
        LabelProperty labelProperty = new LabelProperty();
        labelProperty.setBundleKey(key);
        textProperty.bind(labelProperty.getStringProperty());
    }
}
