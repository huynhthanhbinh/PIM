package com.bht.pim.service;

import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.project.*;
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

                Project project = Project.newBuilder()
                        .setId(projectEntity.getId())
                        .setNumber(projectEntity.getNumber())
                        .setName(projectEntity.getName())
                        .setCustomer(projectEntity.getCustomer())
                        .setGroupId(projectEntity.getGroup().getId())
                        .setStatus(projectEntity.getStatus())
                        .setStart(projectEntity.getStart().getTime())
                        .setEnd((end != null) ? end.getTime() : 0)
                        .build();

                projects.add(project);
            });

            ProjectList projectList = ProjectList.newBuilder()
                    .addAllProjectList(projects)
                    .build();

            responseObserver.onNext(projectList);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            logger.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(ProjectList.newBuilder()
                    .addAllProjectList(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getProjectNumbers(NoParam request, StreamObserver<ProjectNumbers> responseObserver) {
        super.getProjectNumbers(request, responseObserver);
    }
}
