package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.GroupDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.mapper.GroupMapper;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.groups.GroupList;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Log4j
@GRpcService
public class GroupServiceImpl extends GroupServiceGrpc.GroupServiceImplBase {


    @Autowired
    private GroupDao groupDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private GroupMapper groupMapper;


    @Override
    public void getGroupById(Int64Value request, StreamObserver<Group> responseObserver) {
        try {
            GroupEntity groupEntity = groupDao.getGroupById(request.getValue());
            Group group = groupMapper.toGroup(groupEntity);

            responseObserver.onNext(group);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void addNewGroup(Group request, StreamObserver<BoolValue> responseObserver) {
        // each employee just lead 1 group
        // at front end, when create new group
        // send list employee as usual, but then
        // removeAll exist group leaders from this group
        // then show the list after to the UI
        // So that we can prevent from unique constraint
        try {
            EmployeeEntity leader = employeeDao
                    .getEmployeeById(request.getGroupInfo().getLeader().getId());

            // Check constraint again in back-end side
            // If leader not lead group yet, add new group
            // Otherwise, it will throw exception =))
            // As CONSTRAINT GROUP_LEADER_ID is UNIQUE !!!
            if (leader.getLedGroup() == null) {

                GroupEntity groupEntity = groupMapper.toGroupEntity(request);
                boolean isSuccess = groupDao.addGroup(groupEntity);

                BoolValue success = BoolValue.newBuilder()
                        .setValue(isSuccess)
                        .build();

                if (isSuccess) {
                    log.info("<<< Add new group successfully ! >>>");
                } else {
                    log.info("<<< Fail to add new group ! >>>");
                }

                responseObserver.onNext(success);
                responseObserver.onCompleted();
                return;
            }

            log.info("<<< Fail to add new group ! >>>");
            log.info("Group leader is already lead another group");
            log.info("CONSTRAINT: \"1 employee just lead 1 group\"");

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.info("<<< Fail to add new group ! >>>");
            log.info(exception);

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getGroupList(Empty request, StreamObserver<GroupList> responseObserver) {
        try {
            List<GroupEntity> groupEntities = groupDao.getAllGroups();
            List<Group> groups = groupMapper.toGroupList(groupEntities);

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
