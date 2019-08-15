package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.GroupDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.mapper.GroupMapper;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.groups.GroupInfo;
import com.bht.pim.proto.groups.GroupList;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j
@GRpcService
public class GroupServiceImpl extends GroupServiceGrpc.GroupServiceImplBase {

    @Autowired
    GroupDao groupDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    GroupMapper groupMapper;

    @Override
    public void getGroupById(Int64Value request, StreamObserver<Group> responseObserver) {

        GroupEntity groupEntity = groupDao
                .getGroupById(request.getValue());

        try {
            EmployeeEntity leader = groupEntity.getGroupLeader();

            EmployeeInfo groupLeader = EmployeeInfo.newBuilder()
                    .setId(leader.getId())
                    .setVisa(leader.getVisa())
                    .setFirstName(leader.getFirstName())
                    .setLastName(leader.getLastName())
                    .build();

            GroupInfo groupInfo = GroupInfo.newBuilder()
                    .setId(groupEntity.getId())
                    .setLeader(groupLeader)
                    .build();

            List<ProjectInfo> projects = new ArrayList<>();

            groupEntity.getJoinedProjects().forEach(projectEntity -> {
                Date end = projectEntity.getEnd();

                ProjectInfo project = ProjectInfo.newBuilder()
                        .setId(projectEntity.getId())
                        .setNumber(projectEntity.getNumber())
                        .setGroup(groupInfo)
                        .setName(projectEntity.getName())
                        .setCustomer(projectEntity.getCustomer())
                        .setStatus(projectEntity.getStatus())
                        .setStart(DateUtil.toTimestamp(projectEntity.getStart()))
                        .setEnd((end != null)
                                ? DateUtil.toTimestamp(end)
                                : Timestamp.newBuilder().build())
                        .build();

                projects.add(project);
            });

            Group group = Group.newBuilder()
                    .setGroupInfo(groupInfo)
                    .addAllEnrolledProjects(projects)
                    .build();

            responseObserver.onNext(group);
            responseObserver.onCompleted();

            log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            log.info("Successfully get Group " + request.getValue());
            log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

        } catch (Exception exception) {

            log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            log.info("Fail to get Group " + request.getValue());
            log.info(exception);
            log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
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

                GroupEntity groupEntity = new GroupEntity();

                groupEntity.setGroupLeader(leader);
                groupEntity.setJoinedProjects(Collections.emptySet());

                boolean isSuccess = groupDao.addGroup(groupEntity);

                BoolValue success = BoolValue.newBuilder()
                        .setValue(isSuccess)
                        .build();

                log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                if (isSuccess) {
                    log.info("<<< Add new group successfully ! >>>");
                } else {
                    log.info("<<< Fail to add new group ! >>>");
                }
                log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

                responseObserver.onNext(success);
                responseObserver.onCompleted();
                return;
            }

            log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            log.info("<<< Fail to add new group ! >>>");
            log.info("Group leader is already lead another group");
            log.info("CONSTRAINT: \"1 employee just lead 1 group\"");
            log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            log.info("<<< Fail to add new group ! >>>");
            log.info(exception);
            log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getGroupList(Empty request, StreamObserver<GroupList> responseObserver) {
        try {

            List<GroupEntity> groupEntities = groupDao
                    .getAllGroups();

            List<Group> groups = new ArrayList<>();

            groupEntities.forEach(groupEntity -> {
                EmployeeEntity leader = groupEntity.getGroupLeader();

                EmployeeInfo groupLeader = EmployeeInfo.newBuilder()
                        .setId(leader.getId())
                        .setVisa(leader.getVisa())
                        .setFirstName(leader.getFirstName())
                        .setLastName(leader.getLastName())
                        .build();

                GroupInfo groupInfo = GroupInfo.newBuilder()
                        .setId(groupEntity.getId())
                        .setLeader(groupLeader)
                        .build();

                Group group = Group.newBuilder()
                        .setGroupInfo(groupInfo)
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
