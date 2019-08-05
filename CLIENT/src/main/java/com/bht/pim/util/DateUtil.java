package com.bht.pim.util;

import com.google.protobuf.Timestamp;
import javafx.util.StringConverter;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {
    }

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

    public static Timestamp toTimestamp(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);

        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    public static Timestamp toTimestamp(Date date) {
        LocalDate localDate = date.toLocalDate();
        return toTimestamp(localDate);
    }

    public static LocalDate toLocalDate(Timestamp timestamp) {
        return Instant
                .ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }

    public static Date toSqlDate(Timestamp timestamp) {
        return Date.valueOf(toLocalDate(timestamp));
    }
}
