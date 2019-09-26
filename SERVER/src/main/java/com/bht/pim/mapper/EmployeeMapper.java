package com.bht.pim.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.employees.Employee;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomizedMapper.class, DateTimeMapper.class})
public interface EmployeeMapper {

    @Named("toEmployee")
    @Mapping(source = "employeeEntity", target = "employeeInfo")
    @Mapping(source = "enrolledProjects", target = "enrolledProjectsList", qualifiedByName = "getProjectInfoIgnoreGroupLeader")
    Employee toEmployee(final EmployeeEntity employeeEntity);


    @Named("toEmployeeIgnoreProjects")
    @Mapping(source = "employeeEntity", target = "employeeInfo")
    @Mapping(source = "enrolledProjects", target = "enrolledProjectsList", ignore = true)
    Employee toEmployeeIgnoreProjects(final EmployeeEntity employeeEntity);


    default List<Employee> toEmployeeList(final List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream()
                .map(this::toEmployeeIgnoreProjects)
                .collect(Collectors.toList());
    }
}