package com.bht.pim.mapper;

import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.groups.GroupInfo;
import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Mapper
@Component
public interface CustomizedMapper {

    @Named("toEmployeeInfo")
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "visa", target = "visa"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "birthday", target = "birthday")})
    EmployeeInfo map(final EmployeeEntity employeeEntity);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "groupLeader", target = "leader")})
    GroupInfo map(final GroupEntity groupEntity);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "start", target = "start"),
            @Mapping(source = "end", target = "end"),
            @Mapping(source = "group", target = "group")})
    ProjectInfo map(final ProjectEntity projectEntity);


    default Timestamp map(final Date date) {
        return (date != null)
                ? (DateUtil.toTimestamp(date))
                : (Timestamp.newBuilder().build());
    }
}
