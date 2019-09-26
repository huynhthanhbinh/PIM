package com.bht.pim.mapper;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.google.protobuf.Timestamp;

@Component
@Mapper(componentModel = "spring")
public interface DateTimeMapper {

    default Timestamp toTimestamp(final LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);

        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    default Timestamp toTimestamp(final Date date) {
        return (date != null)
                ? toTimestamp(date.toLocalDate())
                : Timestamp.getDefaultInstance();
    }

    default LocalDate toLocalDate(final Timestamp timestamp) {
        return Instant
                .ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }

    default Date toSqlDate(final Timestamp timestamp) {
        return (!timestamp.equals(Timestamp.getDefaultInstance()))
                ? Date.valueOf(toLocalDate(timestamp))
                : null;
    }
}