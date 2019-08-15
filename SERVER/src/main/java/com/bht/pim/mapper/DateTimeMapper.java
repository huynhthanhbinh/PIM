package com.bht.pim.mapper;

import com.bht.pim.util.DateUtil;
import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;

import java.sql.Date;

@Mapper
public interface DateTimeMapper {

    default Timestamp map(final Date date) {

        return (date != null)
                ? (DateUtil.toTimestamp(date))
                : (Timestamp.newBuilder().build());
    }
}
