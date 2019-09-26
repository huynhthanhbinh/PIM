package com.bht.pim.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.groups.GroupInfo;
import com.bht.pim.proto.projects.ProjectInfo;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
@Mapper(uses = DateTimeMapper.class, componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class CustomizedMapper {

    @Autowired
    private EmployeeDao employeeDao;


    EmployeeEntity map(final EmployeeInfo employeeInfo) {
        return employeeDao
                .getEmployeeById(employeeInfo.getId());
    }


    GroupEntity map(final GroupInfo groupInfo) {
        return employeeDao
                .getEmployeeById(groupInfo.getLeader().getId())
                .getLedGroup();
    }


    @Named("getGroupInfo")
    @Mapping(source = "groupLeader", target = "leader")
    abstract GroupInfo getGroupInfo(final GroupEntity groupEntity);

    @Named("getGroupInfoIgnoreLeader")
    @Mapping(source = "groupLeader", target = "leader", ignore = true)
    abstract GroupInfo getGroupInfoIgnoreLeader(final GroupEntity groupEntity);


    @Named("getProjectInfo")
    abstract ProjectInfo getProjectInfo(final ProjectEntity projectEntity);


    @Named("getProjectInfoIgnoreGroupLeader")
    @Mapping(source = "group", target = "group", qualifiedByName = "getGroupInfoIgnoreLeader")
    abstract ProjectInfo getProjectInfoIgnoreGroupLeader(final ProjectEntity projectEntity);


    @Named("getEmployeeInfo")
    abstract EmployeeInfo getEmployeeInfo(final EmployeeEntity employeeEntity);
}