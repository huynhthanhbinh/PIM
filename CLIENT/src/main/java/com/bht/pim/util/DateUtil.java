package com.bht.pim.util;

import javafx.util.StringConverter;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    // Converter for pim date-picker controls
    public static final StringConverter<LocalDate> DATE_STRING_CONVERTER
            = new DateUtil().dateStringConverter();
    private Logger logger = Logger.getLogger(DateUtil.class);

    private StringConverter<LocalDate> dateStringConverter() {
        return new StringConverter<LocalDate>() {

            private DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate object) {
                return (object != null) ?
                        (dateTimeFormatter.format(object)) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string == null || string.trim().isEmpty()) {
                    return null;
                }
                try {
                    return LocalDate.parse(string, dateTimeFormatter);

                } catch (Exception exception) {

                    logger.info(exception);
                    return null;
                }
            }
        };
    }
}
