package com.bht.pim.mapper;

import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.projects.Project;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomizedMapper.class, DateTimeMapper.class})
public interface ProjectMapper {


    @Named("toProject")
    @Mapping(source = "projectEntity", target = "projectInfo", qualifiedByName = "getProjectInfo")
    @Mapping(source = "projectEntity.enrolledEmployees", target = "membersList", qualifiedByName = "getEmployeeInfo")
    Project toProject(final ProjectEntity projectEntity);


    @Named("toProjectIgnoreMembers")
    @Mapping(source = "projectEntity", target = "projectInfo", qualifiedByName = "getProjectInfoIgnoreGroupLeader")
    @Mapping(source = "projectEntity.enrolledEmployees", target = "membersList", ignore = true)
    Project toProjectIgnoreMembers(final ProjectEntity projectEntity);


    default List<Project> toProjectList(final List<ProjectEntity> projectEntities) {
        return projectEntities.stream()
                .map(this::toProjectIgnoreMembers)
                .collect(Collectors.toList());
    }
}
