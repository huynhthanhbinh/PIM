package com.bht.pim.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.bht.pim.dto.ProjectDto;
import com.bht.pim.proto.projects.Project;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomizedMapper.class, DateTimeMapper.class, StatusMapper.class})
public interface ProjectMapper {


    @Mapping(source = "projectInfo.id", target = "id")
    @Mapping(source = "projectInfo.number", target = "number")
    @Mapping(source = "projectInfo.name", target = "name")
    @Mapping(source = "projectInfo.customer", target = "customer")
    @Mapping(source = "projectInfo.start", target = "start")
    @Mapping(source = "projectInfo.end", target = "end")
    @Mapping(source = "projectInfo.status", target = "status", qualifiedByName = "toGuiStatus")
    @Mapping(source = "projectInfo.group", target = "group", qualifiedByName = "toGroupDto")
    @Mapping(source = "membersList", target = "members", qualifiedByName = "toEmployeeDto")
    ProjectDto toProjectDto(final Project project);


    @Mapping(source = "projectDto", target = "projectInfo", qualifiedByName = "toProjectInfo")
    @Mapping(source = "members", target = "membersList", qualifiedByName = "toEmployeeInfo")
    Project toProject(final ProjectDto projectDto);


    default List<ProjectDto> toProjectDtoList(final List<Project> projectList) {
        return projectList.stream()
                .map(this::toProjectDto)
                .collect(Collectors.toList());
    }
}
