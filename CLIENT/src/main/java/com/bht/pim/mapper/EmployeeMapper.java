package com.bht.pim.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.proto.employees.Employee;

/**
 * @author bht
 */
@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomizedMapper.class, DateTimeMapper.class})
public interface EmployeeMapper extends BaseBean {


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