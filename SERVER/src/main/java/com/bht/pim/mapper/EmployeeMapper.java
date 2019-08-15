package com.bht.pim.mapper;

import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeInfo;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = CustomizedMapper.class)
public interface EmployeeMapper {

    @Named("toEmployeeInfo")
    EmployeeInfo toEmployeeInfo(final EmployeeEntity employeeEntity);


    @Mappings({
            @Mapping(source = "enrolledProjects", target = "enrolledProjectsList"),
            @Mapping(source = "employeeEntity", target = "employeeInfo", qualifiedByName = "toEmployeeInfo")})
    Employee toEmployee(final EmployeeEntity employeeEntity);


    EmployeeEntity toEmployeeEnity(Employee employee);
}
