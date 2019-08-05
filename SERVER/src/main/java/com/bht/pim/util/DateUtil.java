package com.bht.pim.util;

import com.google.protobuf.Timestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {

    public static Timestamp toTimestamp(Date date) {
        LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return Timestamp.newBuilder()
                .setSeconds(localDateTime.getSecond())
                .setNanos(localDateTime.getNano())
                .build();
    }
}
