package com.bht.pim.service;


import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.employee.Employee;
import com.bht.pim.proto.employee.EmployeeId;
import com.bht.pim.proto.employee.EmployeeInfo;
import com.bht.pim.proto.employee.EmployeeServiceGrpc;
import com.bht.pim.proto.project.Project;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@GRpcService
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    ProjectDao projectDao;

    @Override
    public void getEmployeeById(EmployeeId request, StreamObserver<EmployeeInfo> responseObserver) {

        EmployeeEntity employeeEntity = employeeDao
                .getEmployeeById(request.getId());

        Employee employee = Employee.newBuilder()
                .setId(employeeEntity.getId())
                .setVisa(employeeEntity.getVisa())
                .setFirstName(employeeEntity.getFirstName())
                .setLastName(employeeEntity.getLastName())
                .setBirthday(employeeEntity.getBirthday().getTime())
                .build();

        List<Project> projects = new ArrayList<>();

        employeeEntity.getEnrolledProjects().forEach(projectEntity -> {
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

        EmployeeInfo employeeInfo = EmployeeInfo.newBuilder()
                .setEmployee(employee)
                .addAllEnrolledProjects(projects)
                .build();

        responseObserver.onNext(employeeInfo);
        responseObserver.onCompleted();
    }
}
