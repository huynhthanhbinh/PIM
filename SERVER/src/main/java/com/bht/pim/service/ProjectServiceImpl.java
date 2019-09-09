package com.bht.pim.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.mapper.ProjectMapper;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.projects.Project;
import com.bht.pim.proto.projects.ProjectGroup;
import com.bht.pim.proto.projects.ProjectGroups;
import com.bht.pim.proto.projects.ProjectList;
import com.bht.pim.proto.projects.ProjectNumbers;
import com.bht.pim.proto.projects.ProjectPagination;
import com.bht.pim.proto.projects.ProjectServiceGrpc;
import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;

import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;

@Log4j
@GRpcService
public class ProjectServiceImpl extends ProjectServiceGrpc.ProjectServiceImplBase {


    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public void getProjectById(Int64Value request, StreamObserver<Project> responseObserver) {
        try {
            ProjectEntity projectEntity = projectDao.getProjectById(request.getValue());
            Project project = projectMapper.toProject(projectEntity);

            responseObserver.onNext(project);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.warn(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void addNewProject(Project request, StreamObserver<BoolValue> responseObserver) {
        try {
            EmployeeInfo groupLeader = request.getProjectInfo().getGroup().getLeader();
            EmployeeEntity leader = employeeDao.getEmployeeById(groupLeader.getId());

            if (leader != null && leader.getLedGroup() != null) {
                ProjectEntity projectEntity = projectMapper.toProjectEntity(request);

                BoolValue success = BoolValue.newBuilder()
                        .setValue(projectDao.addProject(projectEntity))
                        .build();

                responseObserver.onNext(success);
                responseObserver.onCompleted();

                return;
            }

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.warn(exception);
            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void editProject(Project request, StreamObserver<BoolValue> responseObserver) {
        try {
            ProjectEntity projectEntity = projectDao.getProjectById(request.getProjectInfo().getId());

            if (projectEntity != null) {
                BoolValue success = BoolValue.newBuilder()
                        .setValue(projectDao.updateProject(projectMapper.toProjectEntity(request)))
                        .build();

                responseObserver.onNext(success);
                responseObserver.onCompleted();

                return;
            }

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.warn(exception);
            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void deleteProject(Int64Value request, StreamObserver<BoolValue> responseObserver) {
        try {
            ProjectEntity projectEntity = projectDao.getProjectById(request.getValue());

            if (projectEntity != null) {
                BoolValue success = BoolValue.newBuilder()
                        .setValue(projectDao.deleteProject(request.getValue()))
                        .build();

                responseObserver.onNext(success);
                responseObserver.onCompleted();

                return;
            }

            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.warn(exception);
            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getProjectList(ProjectPagination pagination, StreamObserver<ProjectList> responseObserver) {
        try {
            if (!pagination.getKeyword().isEmpty()) {
                log.info("Search all projects with keyword = " + pagination.getKeyword() +
                        ", maxRow = " + pagination.getMaxRow() +
                        ", pageIndex = " + pagination.getPageIndex());

                List<ProjectEntity> projectEntities = projectDao
                        .getProjectListByKeyword(
                                pagination.getMaxRow(),
                                pagination.getPageIndex(),
                                pagination.getKeyword());

                List<Project> projects = projectMapper.toProjectList(projectEntities);

                ProjectList projectList = ProjectList.newBuilder()
                        .addAllProjects(projects)
                        .build();

                responseObserver.onNext(projectList);
                responseObserver.onCompleted();
                return;
            }

            if (!pagination.getStatus().isEmpty()) {
                log.info("Search all projects with status = " + pagination.getStatus() +
                        ", maxRow = " + pagination.getMaxRow() +
                        ", pageIndex = " + pagination.getPageIndex());

                List<ProjectEntity> projectEntities = projectDao
                        .getProjectListByStatus(
                                pagination.getMaxRow(),
                                pagination.getPageIndex(),
                                pagination.getStatus());

                List<Project> projects = projectMapper.toProjectList(projectEntities);

                ProjectList projectList = ProjectList.newBuilder()
                        .addAllProjects(projects)
                        .build();

                responseObserver.onNext(projectList);
                responseObserver.onCompleted();
                return;
            }

            log.info("Search all projects" +
                    ", maxRow = " + pagination.getMaxRow() +
                    ", pageIndex = " + pagination.getPageIndex());

            List<ProjectEntity> projectEntities = projectDao
                    .getProjectList(
                            pagination.getMaxRow(),
                            pagination.getPageIndex());

            List<Project> projects = projectMapper.toProjectList(projectEntities);

            ProjectList projectList = ProjectList.newBuilder()
                    .addAllProjects(projects)
                    .build();

            responseObserver.onNext(projectList);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.warn(exception);

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
            log.warn(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(ProjectNumbers.newBuilder()
                    .addAllProjectNumbers(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getNumberOfProjects(Empty request, StreamObserver<Int64Value> responseObserver) {
        try {
            responseObserver.onNext(Int64Value.newBuilder()
                    .setValue(projectDao.getNumberOfProjects())
                    .build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.warn(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(Int64Value.newBuilder().build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getNumberOfProjectsByStatus(StringValue request, StreamObserver<Int64Value> responseObserver) {
        try {
            responseObserver.onNext(Int64Value.newBuilder()
                    .setValue(projectDao
                            .getNumberOfProjectsByStatus(request.getValue()))
                    .build());

            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.warn(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(Int64Value.newBuilder().build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getNumberOfProjectsByKeyword(StringValue request, StreamObserver<Int64Value> responseObserver) {
        try {
            responseObserver.onNext(Int64Value.newBuilder()
                    .setValue(projectDao
                            .getNumberOfProjectsByKeyword(request.getValue()))
                    .build());

            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.warn(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(Int64Value.newBuilder().build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getProjectByNumber(Int64Value request, StreamObserver<Project> responseObserver) {
        try {
            ProjectEntity projectEntity = projectDao.getProjectByNumber(request.getValue());
            Project project = projectMapper.toProject(projectEntity);

            responseObserver.onNext(project);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.warn(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getProjectGroups(Empty request, StreamObserver<ProjectGroups> responseObserver) {
        try {
            ProjectGroups projectGroups = ProjectGroups.newBuilder()
                    .addAllProjectGroups(projectDao.getProjectGroupByStatus().stream()
                            .map(projectEntityGroup -> ProjectGroup.newBuilder()
                                    .setStatus(projectEntityGroup.getStatus())
                                    .setCount(projectEntityGroup.getCount())
                                    .build())
                            .collect(Collectors.toList()))
                    .build();

            responseObserver.onNext(projectGroups);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.warn(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }
}
