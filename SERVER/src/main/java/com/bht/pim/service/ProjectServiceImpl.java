package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.projects.*;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@GRpcService
public class ProjectServiceImpl extends ProjectServiceGrpc.ProjectServiceImplBase {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    ProjectDao projectDao;
    private Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    @Override
    public void getProjectById(ProjectId request, StreamObserver<ProjectInfo> responseObserver) {

        ProjectEntity projectEntity = projectDao
                .getProjectById(request.getId());

        try {
            Date end = projectEntity.getEnd();

            EmployeeEntity leader = projectEntity.getGroup().getGroupLeader();

            Employee groupLeader = Employee.newBuilder()
                    .setId(leader.getId())
                    .setVisa(leader.getVisa())
                    .setLastName(leader.getLastName())
                    .setFirstName(leader.getFirstName())
                    .build();

            Group group = Group.newBuilder()
                    .setId(projectEntity.getGroup().getId())
                    .setLeader(groupLeader)
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

            List<Employee> employees = new ArrayList<>();

            projectEntity.getEnrolledEmployees().forEach(employeeEntity -> {
                Employee employee = Employee.newBuilder()
                        .setId(employeeEntity.getId())
                        .setVisa(employeeEntity.getVisa())
                        .setFirstName(employeeEntity.getFirstName())
                        .setLastName(employeeEntity.getLastName())
                        .build();

                employees.add(employee);
            });


            ProjectInfo projectInfo = ProjectInfo.newBuilder()
                    .setProject(project)
                    .addAllEmployees(employees)
                    .build();

            responseObserver.onNext(projectInfo);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            logger.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void addNewProject(ProjectInfo request, StreamObserver<Success> responseObserver) {

        Project project = request.getProject();
        Employee groupLeader = project.getGroup().getLeader();
        List<Employee> employees = request.getEmployeesList();

        try {
            EmployeeEntity leader = employeeDao.getEmployeeById(groupLeader.getId());

            if (leader != null && leader.getLedGroup() != null) {
                Set<EmployeeEntity> employeeEntities = employees.stream()
                        .map(employee -> employeeDao.getEmployeeById(employee.getId()))
                        .collect(Collectors.toSet());

                GroupEntity groupEntity = leader.getLedGroup();
                ProjectEntity projectEntity = new ProjectEntity();

                projectEntity.setStatus("NEW");
                projectEntity.setNumber(project.getNumber());
                projectEntity.setName(project.getName());
                projectEntity.setCustomer(project.getCustomer());
                projectEntity.setGroup(groupEntity);
                projectEntity.setEnrolledEmployees(employeeEntities);
                projectEntity.setStart(DateUtil.toSqlDate(project.getStart()));

                Success success = Success.newBuilder()
                        .setIsSuccess(projectDao.addProject(projectEntity))
                        .build();

                responseObserver.onNext(success);
                responseObserver.onCompleted();

                return;
            }

            responseObserver.onNext(Success.newBuilder().setIsSuccess(false).build());
            responseObserver.onCompleted();

        } catch (Exception exception) {

            logger.info(exception);
            responseObserver.onNext(Success.newBuilder().setIsSuccess(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void editProject(ProjectInfo request, StreamObserver<Success> responseObserver) {
        super.editProject(request, responseObserver);
    }

    @Override
    public void deleteProject(ProjectId request, StreamObserver<Success> responseObserver) {
        super.deleteProject(request, responseObserver);
    }
}
