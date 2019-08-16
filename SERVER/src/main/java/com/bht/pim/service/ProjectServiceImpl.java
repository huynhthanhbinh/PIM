package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.mapper.DateTimeMapper;
import com.bht.pim.mapper.ProjectMapper;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.projects.*;
import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j
@GRpcService
public class ProjectServiceImpl extends ProjectServiceGrpc.ProjectServiceImplBase {


    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private DateTimeMapper dateTimeMapper;


    @Override
    public void getProjectById(Int64Value request, StreamObserver<Project> responseObserver) {
        try {
            ProjectEntity projectEntity = projectDao.getProjectById(request.getValue());
            Project project = projectMapper.toProject(projectEntity);

            responseObserver.onNext(project);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void addNewProject(Project request, StreamObserver<BoolValue> responseObserver) {

        ProjectInfo projectInfo = request.getProjectInfo();
        EmployeeInfo groupLeader = projectInfo.getGroup().getLeader();
        List<EmployeeInfo> employees = request.getMembersList();

        try {
            EmployeeEntity leader = employeeDao.getEmployeeById(groupLeader.getId());

            if (leader != null && leader.getLedGroup() != null) {
                Set<EmployeeEntity> employeeEntities = employees.stream()
                        .map(employee -> employeeDao.getEmployeeById(employee.getId()))
                        .collect(Collectors.toSet());

                GroupEntity groupEntity = leader.getLedGroup();
                ProjectEntity projectEntity = new ProjectEntity();

                projectEntity.setStatus("NEW");
                projectEntity.setNumber(projectInfo.getNumber());
                projectEntity.setName(projectInfo.getName());
                projectEntity.setCustomer(projectInfo.getCustomer());
                projectEntity.setGroup(groupEntity);
                projectEntity.setEnrolledEmployees(employeeEntities);
                projectEntity.setStart(dateTimeMapper.toSqlDate(projectInfo.getStart()));

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

            log.info(exception);
            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void editProject(Project request, StreamObserver<BoolValue> responseObserver) {
        super.editProject(request, responseObserver);
    }

    @Override
    public void deleteProject(Int64Value request, StreamObserver<BoolValue> responseObserver) {
        super.deleteProject(request, responseObserver);
    }

    @Override
    public void getProjectList(Empty request, StreamObserver<ProjectList> responseObserver) {
        try {
            List<ProjectEntity> projectEntities = projectDao.getAllProjects();
            List<Project> projects = projectMapper.toProjectList(projectEntities);

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
