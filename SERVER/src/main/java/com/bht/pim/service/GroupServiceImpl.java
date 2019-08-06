package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.GroupDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.groups.GroupInfo;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.Project;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.BoolValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@GRpcService
public class GroupServiceImpl extends GroupServiceGrpc.GroupServiceImplBase {

    @Autowired
    GroupDao groupDao;
    private Logger logger = Logger.getLogger(GroupServiceImpl.class);

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void getGroupById(Int64Value request, StreamObserver<GroupInfo> responseObserver) {

        GroupEntity groupEntity = groupDao
                .getGroupById(request.getValue());

        try {
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

            List<Project> projects = new ArrayList<>();

            groupEntity.getJoinedProjects().forEach(projectEntity -> {
                Date end = projectEntity.getEnd();

                Project project = Project.newBuilder()
                        .setId(projectEntity.getId())
                        .setNumber(projectEntity.getNumber())
                        .setGroup(group)
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

            GroupInfo groupInfo = GroupInfo.newBuilder()
                    .setGroup(group)
                    .addAllEnrolledProjects(projects)
                    .build();

            responseObserver.onNext(groupInfo);
            responseObserver.onCompleted();

            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info("Successfully get Group " + request.getValue());
            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

        } catch (Exception exception) {

            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info("Fail to get Group " + request.getValue());
            logger.info(exception);
            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
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
                    .getEmployeeById(request.getLeader().getId());

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

                logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                if (isSuccess) {
                    logger.info("<<< Add new group successfully ! >>>");
                } else {
                    logger.info("<<< Fail to add new group ! >>>");
                }
                logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

                responseObserver.onNext(success);
                responseObserver.onCompleted();
                return;
            }

            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info("<<< Fail to add new group ! >>>");
            logger.info("Group leader is already lead another group");
            logger.info("CONSTRAINT: \"1 employee just lead 1 group\"");
            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info("<<< Fail to add new group ! >>>");
            logger.info(exception);
            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();
        }
    }
}
