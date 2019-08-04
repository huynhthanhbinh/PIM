package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.GroupDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.groups.*;
import com.bht.pim.proto.projects.Project;
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
    public void getGroupById(GroupId request, StreamObserver<GroupInfo> responseObserver) {

        GroupEntity groupEntity = groupDao
                .getGroupById(request.getId());

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
                        .setStart(projectEntity.getStart().getTime())
                        .setEnd((end != null) ? end.getTime() : 0)
                        .build();

                projects.add(project);
            });

            GroupInfo groupInfo = GroupInfo.newBuilder()
                    .setGroup(group)
                    .addAllEnrolledProject(projects)
                    .build();

            responseObserver.onNext(groupInfo);
            responseObserver.onCompleted();

            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info("Successfully get Group " + request.getId());
            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

        } catch (Exception exception) {

            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info("Fail to get Group " + request.getId());
            logger.info(exception);
            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void addNewGroup(Group request, StreamObserver<Success> responseObserver) {
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

                Success success = Success.newBuilder()
                        .setIsSuccess(isSuccess)
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

            responseObserver.onNext(Success.newBuilder()
                    .setIsSuccess(false).build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info("<<< Fail to add new group ! >>>");
            logger.info(exception);
            logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");

            responseObserver.onNext(Success.newBuilder()
                    .setIsSuccess(false).build());
            responseObserver.onCompleted();
        }
    }
}
