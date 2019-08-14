package com.bht.pim.mapper;

import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.groups.GroupInfo;
import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    @Mappings({
            @Mapping(source = "projectEntity", target = "group", qualifiedByName = "getGroup"),
            @Mapping(source = "projectEntity", target = "start", qualifiedByName = "getStart"),
            @Mapping(source = "projectEntity", target = "end", qualifiedByName = "getEnd")
    })
    ProjectInfo toProjectInfo(final ProjectEntity projectEntity);

    @Named("getGroup")
    default GroupInfo getGroup(final ProjectEntity projectEntity) {
        GroupMapper groupMapper = Mappers.getMapper(GroupMapper.class);
        return groupMapper.toGroupInfo(projectEntity.getGroup());
    }

    @Named("getStart")
    default Timestamp getStart(final ProjectEntity projectEntity) {
        return DateUtil.toTimestamp(projectEntity.getStart());
    }

    @Named("getEnd")
    default Timestamp getEnd(final ProjectEntity projectEntity) {
        return (projectEntity.getEnd() != null)
                ? (DateUtil.toTimestamp(projectEntity.getStart()))
                : (Timestamp.newBuilder().build());
    }
}
