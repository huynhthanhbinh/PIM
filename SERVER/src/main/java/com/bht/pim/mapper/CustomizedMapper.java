package com.bht.pim.mapper;

import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.Date;

@Mapper
public class CustomizedMapper {

    Timestamp map(final Date date) {
        return (date != null)
                ? (DateUtil.toTimestamp(date))
                : (Timestamp.newBuilder().build());
    }

    ProjectInfo map(final ProjectEntity projectEntity) {
        ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);
        return projectMapper.toProjectInfo(projectEntity);
    }
}
