package com.bht.pim.mapper;

import com.bht.pim.entity.GroupEntity;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.groups.GroupInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {

    @Mapping(source = "groupEntity", target = "leader", qualifiedByName = "getLeader")
    GroupInfo toGroupInfo(final GroupEntity groupEntity);

    @Named("getLeader")
    default EmployeeInfo getLeader(final GroupEntity groupEntity) {
        EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
        return employeeMapper.toEmployeeInfo(groupEntity.getGroupLeader());
    }
}
