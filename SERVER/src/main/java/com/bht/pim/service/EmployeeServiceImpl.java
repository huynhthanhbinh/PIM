package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.dto.employees.Employee;
import com.bht.pim.dto.employees.EmployeeInfo;
import com.bht.pim.dto.employees.EmployeeServiceGrpc;
import com.bht.pim.dto.groups.Group;
import com.bht.pim.dto.projects.Project;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Log4j
@GRpcService
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void getEmployeeById(Int64Value request, StreamObserver<EmployeeInfo> responseObserver) {

        EmployeeEntity employeeEntity = employeeDao
                .getEmployeeById(request.getValue());

        try {
            Employee employee = Employee.newBuilder()
                    .setId(employeeEntity.getId())
                    .setVisa(employeeEntity.getVisa())
                    .setFirstName(employeeEntity.getFirstName())
                    .setLastName(employeeEntity.getLastName())
                    .setBirthday(DateUtil.toTimestamp(employeeEntity.getBirthday()))
                    .build();

            List<Project> projects = new ArrayList<>();

            employeeEntity.getEnrolledProjects().forEach(projectEntity -> {
                Date end = projectEntity.getEnd();

                Group group = Group.newBuilder()
                        .setId(projectEntity.getGroup().getId())
                        .build();

                Project project = Project.newBuilder()
                        .setId(projectEntity.getId())
                        .setNumber(projectEntity.getNumber())
                        .setGroup(group)
                        .setName(projectEntity.getName())
                        .setCustomer(projectEntity.getCustomer())
                        .setStatus(projectEntity.getStatus())
                        .setStart(DateUtil.toTimestamp(projectEntity.getStart()))
                        .setEnd((end != null)
                                ? DateUtil.toTimestamp(end)
                                : Timestamp.newBuilder().build())
                        .build();

                projects.add(project);
            });

            EmployeeInfo employeeInfo = EmployeeInfo.newBuilder()
                    .setEmployee(employee)
                    .addAllEnrolledProjects(projects)
                    .build();

            responseObserver.onNext(employeeInfo);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }
}
