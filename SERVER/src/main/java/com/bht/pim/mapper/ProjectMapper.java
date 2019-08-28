package com.bht.pim.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.projects.Project;

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


    @Mapping(source = "project.projectInfo.id", target = "id")
    @Mapping(source = "project.projectInfo.number", target = "number")
    @Mapping(source = "project.projectInfo.name", target = "name")
    @Mapping(source = "project.projectInfo.customer", target = "customer")
    @Mapping(source = "project.projectInfo.status", target = "status")
    @Mapping(source = "project.projectInfo.start", target = "start")
    @Mapping(source = "project.projectInfo.end", target = "end")
    @Mapping(source = "project.projectInfo.group", target = "group")
    @Mapping(source = "project.membersList", target = "enrolledEmployees")
    ProjectEntity toProjectEntity(final Project project);
}
