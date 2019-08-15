package com.bht.pim.mapper;

import com.bht.pim.entity.GroupEntity;
import com.bht.pim.proto.groups.GroupInfo;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = CustomizedMapper.class, componentModel = "spring")
public interface GroupMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "groupLeader", target = "leader")})
    GroupInfo toGroupInfo(final GroupEntity groupEntity);
}
