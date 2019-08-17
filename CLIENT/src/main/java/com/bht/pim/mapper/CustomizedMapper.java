package com.bht.pim.mapper;

import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.dto.GroupDto;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.groups.GroupInfo;
import com.bht.pim.proto.projects.ProjectInfo;
import lombok.extern.log4j.Log4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

@Log4j
@Component
@Mapper(uses = {DateTimeMapper.class, StatusMapper.class}, componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class CustomizedMapper {


    @Named("toEmployeeDto")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "visa", target = "visa")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "birthday", target = "birthday")
    abstract EmployeeDto toEmployeeDto(final EmployeeInfo employeeInfo);


    @Named("toEmployeeInfo")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "visa", target = "visa", ignore = true)
    @Mapping(source = "firstName", target = "firstName", ignore = true)
    @Mapping(source = "lastName", target = "lastName", ignore = true)
    @Mapping(source = "birthday", target = "birthday", ignore = true)
    abstract EmployeeInfo toEmployeeInfo(final EmployeeDto employeeDto);


    @Named("toProjectDto")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "start", target = "start")
    @Mapping(source = "end", target = "end")
    @Mapping(source = "status", target = "status", qualifiedByName = "toGuiStatus")
    @Mapping(source = "group", target = "group", qualifiedByName = "toGroupDto")
    abstract ProjectDto toProjectDto(final ProjectInfo projectInfo);


    @Named("toProjectInfo")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "start", target = "start")
    @Mapping(source = "end", target = "end")
    @Mapping(source = "status", target = "status", qualifiedByName = "toSqlStatus")
    @Mapping(source = "group", target = "group", qualifiedByName = "toGroupInfo")
    abstract ProjectInfo toProjectInfo(final ProjectDto projectDto);


    @Named("toGroupDto")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "leader", target = "leader", qualifiedByName = "toEmployeeDto")
    abstract GroupDto toGroupDto(final GroupInfo groupInfo);


    @Named("toGroupInfo")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "leader", target = "leader", ignore = true)
    abstract GroupInfo toGroupInfo(final GroupDto groupDto);
}
