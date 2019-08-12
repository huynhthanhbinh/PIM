package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.ProjectDao;
import com.bht.pim.dto.employees.Employee;
import com.bht.pim.dto.groups.Group;
import com.bht.pim.dto.projects.Project;
import com.bht.pim.dto.projects.ProjectInfo;
import com.bht.pim.dto.projects.ProjectServiceGrpc;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.entity.GroupEntity;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.BoolValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j
@GRpcService
public class ProjectServiceImpl extends ProjectServiceGrpc.ProjectServiceImplBase {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    ProjectDao projectDao;

    @Override
    public void getProjectById(Int64Value request, StreamObserver<ProjectInfo> responseObserver) {

        ProjectEntity projectEntity = projectDao
                .getProjectById(request.getValue());

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

            log.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void addNewProject(ProjectInfo request, StreamObserver<BoolValue> responseObserver) {

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
    public void editProject(ProjectInfo request, StreamObserver<BoolValue> responseObserver) {
        super.editProject(request, responseObserver);
    }

    @Override
    public void deleteProject(Int64Value request, StreamObserver<BoolValue> responseObserver) {
        super.deleteProject(request, responseObserver);
    }
}
