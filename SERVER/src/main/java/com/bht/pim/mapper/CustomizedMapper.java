package com.bht.pim.mapper;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.groups.GroupInfo;
import com.bht.pim.proto.projects.ProjectInfo;
import lombok.extern.log4j.Log4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j
@Component
@Mapper(uses = DateTimeMapper.class, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class CustomizedMapper {

    @Autowired
    private EmployeeDao employeeDao;


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "visa", target = "visa"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "birthday", target = "birthday")})
    abstract EmployeeInfo map(final EmployeeEntity employeeEntity);


    EmployeeEntity map(final EmployeeInfo employeeInfo) {
        return employeeDao.getEmployeeById(employeeInfo.getId());
    }


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "groupLeader", target = "leader")})
    abstract GroupInfo map(final GroupEntity groupEntity);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "start", target = "start"),
            @Mapping(source = "end", target = "end"),
            @Mapping(source = "group", target = "group")})
    abstract ProjectInfo map(final ProjectEntity projectEntity);
}
