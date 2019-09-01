package com.bht.pim.mapper;

import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.proto.employees.Employee;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bht
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomizedMapper.class, DateTimeMapper.class})
public interface EmployeeMapper {


    @Mapping(source = "employeeInfo.id", target = "id")
    @Mapping(source = "employeeInfo.visa", target = "visa")
    @Mapping(source = "employeeInfo.firstName", target = "firstName")
    @Mapping(source = "employeeInfo.lastName", target = "lastName")
    @Mapping(source = "employeeInfo.birthday", target = "birthday")
    @Mapping(source = "enrolledProjectsList", target = "enrolledProjects", qualifiedByName = "toProjectDto")
    EmployeeDto toEmployeeDto(final Employee employee);


    default List<EmployeeDto> toEmployeeDtoList(final List<Employee> employeeList) {
        return employeeList.stream()
                .map(this::toEmployeeDto)
                .collect(Collectors.toList());
    }
}