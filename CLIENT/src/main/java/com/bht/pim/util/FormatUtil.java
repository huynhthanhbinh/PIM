package com.bht.pim.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.bht.pim.property.FormatProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author bht
 */
@Component
public final class FormatUtil {

    public static final ObjectProperty<StringConverter<LocalDate>> DATE_STRING_CONVERTER = new SimpleObjectProperty<>();
    private static final ObjectProperty<DateTimeFormatter> DATE_FORMATTER_PROPERTY = new SimpleObjectProperty<>();

    @PostConstruct
    public void addAllEventListeners() {
        DATE_FORMATTER_PROPERTY.addListener(observable -> {
            DATE_STRING_CONVERTER.set(dateStringConverter());
        });
        DATE_FORMATTER_PROPERTY.set(DateTimeFormatter.ofPattern(FormatProperty.DATE_PATTERN_PROPERTY.get()));

        FormatProperty.DATE_PATTERN_PROPERTY.addListener((observable, oldValue, newValue) ->
                DATE_FORMATTER_PROPERTY.set(DateTimeFormatter.ofPattern(newValue)));
    }

    public static <T> ObjectProperty<Callback<TableColumn<T, LocalDate>, TableCell<T, LocalDate>>> getDateCellFormatProperty() {
        ObjectProperty<Callback<TableColumn<T, LocalDate>, TableCell<T, LocalDate>>> dateCellFormatProperty = new SimpleObjectProperty<>();
        dateCellFormatProperty.set(FormatUtil::dateCellFormat);
        DATE_FORMATTER_PROPERTY.addListener(observable -> {
            dateCellFormatProperty.set(FormatUtil::dateCellFormat);
        });
        return dateCellFormatProperty;
    }


    // Format Date : convert from Timestamp to LocalDate
    private static <T> TableCell<T, LocalDate> dateCellFormat(TableColumn<T, LocalDate> column) {
        return new TableCell<T, LocalDate>() {

            boolean isBound = false;

            @Override
            protected void updateItem(LocalDate localDate, boolean empty) {
                if (!isBound) {
                    textProperty().bind(toStringProperty(localDate));
                }
            }
        };
    }


    private static StringProperty toStringProperty(LocalDate localDate) {
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.set(getString(localDate));
        DATE_FORMATTER_PROPERTY.addListener(observable -> stringProperty.set(getString(localDate)));
        return stringProperty;
    }

    private static String getString(LocalDate localDate) {
        return (localDate != null)
                ? DATE_FORMATTER_PROPERTY.get().format(localDate)
                : "";
    }


    // Converter for pim date-picker controls
    private StringConverter<LocalDate> dateStringConverter() {
        return new StringConverter<LocalDate>() {

            @Override
            public String toString(LocalDate localDate) {
                return getString(localDate);
            }

            @Override
            public LocalDate fromString(String string) {
                if (string == null || string.trim().isEmpty()) {
                    return null;
                }
                try {
                    return LocalDate.parse(string, DATE_FORMATTER_PROPERTY.get());

                } catch (Exception exception) {

                    return null;
                }
            }
        };
    }
}