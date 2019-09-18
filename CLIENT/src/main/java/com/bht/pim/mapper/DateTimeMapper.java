package com.bht.pim.mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import org.mapstruct.Mapper;

import com.bht.pim.annotation.InheritedComponent;
import com.bht.pim.base.BaseBean;
import com.google.protobuf.Timestamp;

/**
 *
 * @author bht
 */
@InheritedComponent
@Mapper(componentModel = "spring")
public interface DateTimeMapper extends BaseBean {

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
