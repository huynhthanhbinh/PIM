package com.bht.pim.service;

import com.bht.pim.dao.GroupDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.proto.group.*;
import com.bht.pim.proto.project.Project;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@GRpcService
public class GroupServiceImpl extends GroupServiceGrpc.GroupServiceImplBase {

    @Autowired
    GroupDao groupDao;
    private Logger logger = Logger.getLogger(GroupServiceImpl.class);

    @Override
    public void getGroupById(GroupId request, StreamObserver<GroupInfo> responseObserver) {

        GroupEntity groupEntity = groupDao
                .getGroupById(request.getId());

        try {
            EmployeeEntity leader = groupEntity.getGroupLeader();

            Group group = Group.newBuilder()
                    .setId(groupEntity.getId())
                    .setGroupLeaderId(leader.getId())
                    .setGroupLeaderVisa(leader.getVisa())
                    .setGroupLeaderName(leader.getLastName() + " " + leader.getFirstName())
                    .build();

            List<Project> projects = new ArrayList<>();

            groupEntity.getJoinedProjects().forEach(projectEntity -> {
                Date end = projectEntity.getEnd();

                Project project = Project.newBuilder()
                        .setId(projectEntity.getId())
                        .setNumber(projectEntity.getNumber())
                        .setGroupId(projectEntity.getGroup().getId())
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
                    .addAllEnrolledProjects(projects)
                    .build();

            responseObserver.onNext(groupInfo);
            responseObserver.onCompleted();
            ;

        } catch (Exception exception) {

            logger.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void addNewGroup(Group request, StreamObserver<Success> responseObserver) {
        super.addNewGroup(request, responseObserver);
    }
}
