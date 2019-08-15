package com.bht.pim.mapper;

import com.bht.pim.entity.GroupEntity;
import com.bht.pim.proto.groups.Group;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomizedMapper.class, DateTimeMapper.class})
public interface GroupMapper {

    List<Group> toGroupList(final List<GroupEntity> groupEntities);

    @Mappings({
            @Mapping(source = "groupEntity", target = "groupInfo"),
            @Mapping(source = "groupEntity.joinedProjects", target = "enrolledProjectsList")})
    Group toGroup(final GroupEntity groupEntity);

    @Mappings({
            @Mapping(source = "groupInfo.leader", target = "groupLeader")})
    GroupEntity toGroupEntity(final Group group);
}
