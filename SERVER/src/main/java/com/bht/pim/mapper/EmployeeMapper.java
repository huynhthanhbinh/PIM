package com.bht.pim.mapper;

import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.employees.Employee;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomizedMapper.class, DateTimeMapper.class})
public interface EmployeeMapper {

    @Named("toEmployee")
    @Mapping(source = "employeeEntity", target = "employeeInfo")
    @Mapping(source = "enrolledProjects", target = "enrolledProjectsList", qualifiedByName = "getProjectInfoIgnoreGroup")
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