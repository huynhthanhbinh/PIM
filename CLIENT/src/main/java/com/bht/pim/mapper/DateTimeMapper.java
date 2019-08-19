package com.bht.pim.mapper;

import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Mapper
public interface DateTimeMapper {

    default Timestamp toTimestamp(final LocalDate localDate) {
        if (localDate == null) {
            return Timestamp.newBuilder().build();
        }

        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);

        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    default LocalDate toLocalDate(final Timestamp timestamp) {
        if (timestamp.equals(Timestamp.newBuilder().build())) {
            return null;
        }

        return Instant
                .ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }
}
