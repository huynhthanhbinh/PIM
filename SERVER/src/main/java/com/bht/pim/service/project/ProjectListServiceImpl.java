package com.bht.pim.service.project;

import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.projects.Project;
import com.bht.pim.proto.projects.ProjectList;
import com.bht.pim.proto.projects.ProjectListServiceGrpc;
import com.bht.pim.proto.projects.ProjectNumbers;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Empty;
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
public class ProjectListServiceImpl extends ProjectListServiceGrpc.ProjectListServiceImplBase {

    @Autowired
    ProjectDao projectDao;

    @Override
    public void getProjectList(Empty request, StreamObserver<ProjectList> responseObserver) {
        try {

            List<ProjectEntity> projectEntities = projectDao
                    .getAllProjects();

            List<Project> projects = new ArrayList<>();

            projectEntities.forEach(projectEntity -> {
                Date end = projectEntity.getEnd();

                Group group = Group.newBuilder()
                        .setId(projectEntity.getGroup().getId())
                        .build();

                Project project = Project.newBuilder()
                        .setId(projectEntity.getId())
                        .setNumber(projectEntity.getNumber())
                        .setName(projectEntity.getName())
                        .setCustomer(projectEntity.getCustomer())
                        .setGroup(group)
                        .setStatus(projectEntity.getStatus())
                        .setStart(DateUtil.toTimestamp(projectEntity.getStart()))
                        .setEnd((end != null)
                                ? DateUtil.toTimestamp(end)
                                : Timestamp.newBuilder().build())
                        .build();

                projects.add(project);
            });

            ProjectList projectList = ProjectList.newBuilder()
                    .addAllProjects(projects)
                    .build();

            responseObserver.onNext(projectList);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(ProjectList.newBuilder()
                    .addAllProjects(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getProjectNumbers(Empty request, StreamObserver<ProjectNumbers> responseObserver) {
        try {

            List<Long> numbers = projectDao.getAllProjectsNumber();

            ProjectNumbers projectNumbers = ProjectNumbers.newBuilder()
                    .addAllProjectNumbers(numbers)
                    .build();

            responseObserver.onNext(projectNumbers);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(ProjectNumbers.newBuilder()
                    .addAllProjectNumbers(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }
}
