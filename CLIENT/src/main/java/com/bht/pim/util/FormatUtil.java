package com.bht.pim.util;

import java.time.LocalDate;

import com.bht.pim.property.FormatProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author bht
 */
public final class FormatUtil {

    private FormatUtil() {
    }

    public static StringProperty toStringProperty(LocalDate localDate) {
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.set(FormatProperty.getString(localDate));
        FormatProperty.DATE_FORMATTER_PROPERTY.addListener(observable ->
                stringProperty.set(FormatProperty.getString(localDate)));
        return stringProperty;
    }
}