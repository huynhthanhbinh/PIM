package com.bht.pim.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.bht.pim.property.FormatProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;

/**
 *
 * @author bht
 */
public final class FormatUtil {

    private FormatUtil() {
    }

    public static final ObjectProperty<StringConverter<LocalDate>> DATE_STRING_CONVERTER = new SimpleObjectProperty<>();
    public static final ObjectProperty<DateTimeFormatter> DATE_FORMATTER_PROPERTY = new SimpleObjectProperty<>();

    public static StringProperty toStringProperty(LocalDate localDate) {
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.set(FormatProperty.getString(localDate));
        DATE_FORMATTER_PROPERTY.addListener(observable ->
                stringProperty.set(FormatProperty.getString(localDate)));
        return stringProperty;
    }
}