package com.bht.pim.mapper;

import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.projects.ProjectInfo;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = CustomizedMapper.class, componentModel = "spring")
public interface ProjectMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "start", target = "start"),
            @Mapping(source = "end", target = "end"),
            @Mapping(source = "group", target = "group")})
    ProjectInfo toProjectInfo(final ProjectEntity projectEntity);
}
