package com.bht.pim.service;

import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.projects.*;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@GRpcService
public class ProjectListServiceImpl extends ProjectListServiceGrpc.ProjectListServiceImplBase {

    @Autowired
    ProjectDao projectDao;
    private Logger logger = Logger.getLogger(ProjectListServiceImpl.class);

    @Override
    public void getProjectList(NoParam request, StreamObserver<ProjectList> responseObserver) {
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
                        .setStart(projectEntity.getStart().getTime())
                        .setEnd((end != null) ? end.getTime() : 0)
                        .build();

                projects.add(project);
            });

            ProjectList projectList = ProjectList.newBuilder()
                    .addAllProject(projects)
                    .build();

            responseObserver.onNext(projectList);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            logger.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(ProjectList.newBuilder()
                    .addAllProject(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getProjectNumbers(NoParam request, StreamObserver<ProjectNumbers> responseObserver) {
        try {

            List<Long> numbers = projectDao.getAllProjectsNumber();

            ProjectNumbers projectNumbers = ProjectNumbers.newBuilder()
                    .addAllProjectNumber(numbers)
                    .build();

            responseObserver.onNext(projectNumbers);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            logger.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(ProjectNumbers.newBuilder()
                    .addAllProjectNumber(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }
}
