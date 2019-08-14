package com.bht.pim.mapper;

import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.mapper.CustomizeMapping.EmployeeCustomizeMapping;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Timestamp;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = EmployeeCustomizeMapping.class)
public interface EmployeeMapper {

    @AfterMapping
    default void init(EmployeeEntity employeeEntity,
                      @MappingTarget EmployeeInfo employee) {

        System.out.println("\nA\nB\nC\nD\nE\n");
    }

    @Named("toEmployeeInfo")
    @Mapping(source = "employeeEntity", target = "birthday", qualifiedByName = "getBirthday")
    EmployeeInfo toEmployeeInfo(final EmployeeEntity employeeEntity);


    @Mappings({
            @Mapping(source = "employeeEntity", target = "employeeInfo",
                    qualifiedByName = "toEmployeeInfo"),
            @Mapping(source = "employeeEntity", target = "enrolledProjectsList",
                    ignore = true)})
    Employee toEmployee(final EmployeeEntity employeeEntity);

    @Mappings({
            @Mapping(source = "employeeEntity", target = "employeeInfo",
                    qualifiedByName = "toEmployeeInfo"),
            @Mapping(source = "employeeEntity", target = "enrolledProjectsList",
                    ignore = true)})
    Employee toEmployeeIgnoreProjects(final EmployeeEntity employeeEntity);


    @Named("getBirthday")
    default Timestamp toTimestamp(final EmployeeEntity employeeEntity) {
        return DateUtil.toTimestamp(employeeEntity.getBirthday());
    }

    @Named("getAllProjects")
    default List<ProjectInfo> projects(final EmployeeEntity employeeEntity) {

        ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

        return employeeEntity.getEnrolledProjects().stream()
                .map(projectMapper::toProjectInfo).collect(Collectors.toList());
    }

    EmployeeEntity toEmployeeEnity(Employee employee);
}
