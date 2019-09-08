package com.bht.pim.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseDto;
import com.bht.pim.property.FormatProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author bht
 */
@Log4j
@Component
public final class FormatUtil<T extends BaseDto> {

    @PostConstruct
    public void addAllEventListeners() {
        DATE_FORMATTER_PROPERTY.addListener(observable -> {
            DATE_STRING_CONVERTER.set(dateStringConverter());
            dateCellFormatProperty.set(this::dateCellFormat);
        });
        DATE_FORMATTER_PROPERTY.set(DateTimeFormatter.ofPattern(FormatProperty.DATE_PATTERN_PROPERTY.get()));

        FormatProperty.DATE_PATTERN_PROPERTY.addListener((observable, oldValue, newValue) ->
                DATE_FORMATTER_PROPERTY.set(DateTimeFormatter.ofPattern(newValue)));
    }

    public static final ObjectProperty<StringConverter<LocalDate>> DATE_STRING_CONVERTER = new SimpleObjectProperty<>();
    private static final ObjectProperty<DateTimeFormatter> DATE_FORMATTER_PROPERTY = new SimpleObjectProperty<>();
    public final ObjectProperty<Callback<TableColumn<T, LocalDate>, TableCell<T, LocalDate>>> dateCellFormatProperty = new SimpleObjectProperty<>();

    // Format Date : convert from Timestamp to LocalDate
    private TableCell<T, LocalDate> dateCellFormat(TableColumn<T, LocalDate> column) {
        return new TableCell<T, LocalDate>() {
            @Override
            protected void updateItem(LocalDate localDate, boolean empty) {
                if (localDate == null || empty) {
                    setText(null);
                    setStyle("");

                } else {
                    setText(DATE_FORMATTER_PROPERTY.get().format(localDate));
                    setStyle("");
                }
            }
        };
    }

    // Converter for pim date-picker controls
    private StringConverter<LocalDate> dateStringConverter() {
        return new StringConverter<LocalDate>() {

            @Override
            public String toString(LocalDate object) {
                return (object != null) ?
                        (DATE_FORMATTER_PROPERTY.get().format(object)) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string == null || string.trim().isEmpty()) {
                    return null;
                }
                try {
                    return LocalDate.parse(string, DATE_FORMATTER_PROPERTY.get());

                } catch (Exception exception) {

                    log.info(exception);
                    return null;
                }
            }
        };
    }
}
