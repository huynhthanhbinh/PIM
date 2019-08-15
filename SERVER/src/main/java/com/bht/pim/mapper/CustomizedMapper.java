package com.bht.pim.mapper;

import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Timestamp;
import lombok.extern.log4j.Log4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Log4j
@Mapper
@Component
public abstract class CustomizedMapper {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Named("toEmployeeInfo")
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "visa", target = "visa"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "birthday", target = "birthday")})
    abstract EmployeeInfo map(final EmployeeEntity employeeEntity);

    Timestamp map(final Date date) {
        return (date != null)
                ? (DateUtil.toTimestamp(date))
                : (Timestamp.newBuilder().build());
    }

    ProjectInfo map(final ProjectEntity projectEntity) {
        return projectMapper.toProjectInfo(projectEntity);
    }
}
