package com.bht.pim.service;

import com.bht.pim.dao.GroupDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.groups.GroupList;
import com.bht.pim.proto.groups.GroupListServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j
@GRpcService
public class GroupListServiceImpl extends GroupListServiceGrpc.GroupListServiceImplBase {

    @Autowired
    GroupDao groupDao;

    @Override
    public void getGroupList(Empty request, StreamObserver<GroupList> responseObserver) {
        try {

            List<GroupEntity> groupEntities = groupDao
                    .getAllGroups();

            List<Group> groups = new ArrayList<>();

            groupEntities.forEach(groupEntity -> {
                EmployeeEntity leader = groupEntity.getGroupLeader();

                Employee groupLeader = Employee.newBuilder()
                        .setId(leader.getId())
                        .setVisa(leader.getVisa())
                        .setFirstName(leader.getFirstName())
                        .setLastName(leader.getLastName())
                        .build();

                Group group = Group.newBuilder()
                        .setId(groupEntity.getId())
                        .setLeader(groupLeader)
                        .build();

                groups.add(group);
            });

            GroupList groupList = GroupList.newBuilder()
                    .addAllGroups(groups)
                    .build();

            responseObserver.onNext(groupList);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(GroupList.newBuilder()
                    .addAllGroups(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }
}
