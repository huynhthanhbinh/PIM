package com.bht.pim.util;

import com.google.protobuf.Timestamp;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class DateUtil {

    private DateUtil() {
    }

    private static Timestamp toTimestamp(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);

        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    public static Timestamp toTimestamp(Date date) {
        return (date != null)
                ? toTimestamp(date.toLocalDate())
                : Timestamp.getDefaultInstance();
    }

    private static LocalDate toLocalDate(Timestamp timestamp) {
        return Instant
                .ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }

    public static Date toSqlDate(Timestamp timestamp) {
        return (timestamp != Timestamp.getDefaultInstance())
                ? Date.valueOf(toLocalDate(timestamp))
                : null;
    }
}
