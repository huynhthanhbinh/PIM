package com.bht.pim.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.bht.pim.entity.GroupEntity;
import com.bht.pim.proto.groups.Group;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomizedMapper.class, DateTimeMapper.class})
public interface GroupMapper {


    @Named("toGroup")
    @Mapping(source = "groupEntity", target = "groupInfo", qualifiedByName = "getGroupInfo")
    @Mapping(source = "groupEntity.joinedProjects", target = "enrolledProjectsList", qualifiedByName = "getProjectInfoIgnoreGroupLeader")
    Group toGroup(final GroupEntity groupEntity);


    @Named("toGroupIgnoreProjects")
    @Mapping(source = "groupEntity", target = "groupInfo", qualifiedByName = "getGroupInfo")
    @Mapping(source = "groupEntity.joinedProjects", target = "enrolledProjectsList", ignore = true)
    Group toGroupIgnoreProjects(final GroupEntity groupEntity);


    default List<Group> toGroupList(final List<GroupEntity> groupEntities) {
        return groupEntities.stream()
                .map(this::toGroupIgnoreProjects)
                .collect(Collectors.toList());
    }


    @Mapping(source = "groupInfo.leader", target = "groupLeader")
    GroupEntity toGroupEntity(final Group group);
}