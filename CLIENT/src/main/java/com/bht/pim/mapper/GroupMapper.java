package com.bht.pim.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.dto.GroupDto;
import com.bht.pim.proto.groups.Group;

/**
 * @author bht
 */
@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = CustomizedMapper.class)
public interface GroupMapper extends BaseBean {


    @Mapping(source = "groupInfo.id", target = "id")
    @Mapping(source = "groupInfo.leader", target = "leader", qualifiedByName = "toEmployeeDto")
    @Mapping(source = "enrolledProjectsList", target = "enrolledProjects", qualifiedByName = "toProjectDto")
    GroupDto toGroupDto(final Group group);


    @Mapping(source = "groupDto", target = "groupInfo", qualifiedByName = "toGroupInfo")
    Group toGroup(final GroupDto groupDto);


    default List<GroupDto> toGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::toGroupDto)
                .collect(Collectors.toList());
    }
}
