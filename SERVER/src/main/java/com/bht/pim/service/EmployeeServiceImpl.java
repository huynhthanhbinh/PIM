package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.employees.EmployeeList;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.GroupInfo;
import com.bht.pim.proto.projects.ProjectInfo;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
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
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void getEmployeeById(Int64Value request, StreamObserver<Employee> responseObserver) {

        EmployeeEntity employeeEntity = employeeDao
                .getEmployeeById(request.getValue());

        try {
            EmployeeInfo employeeInfo = EmployeeInfo.newBuilder()
                    .setId(employeeEntity.getId())
                    .setVisa(employeeEntity.getVisa())
                    .setFirstName(employeeEntity.getFirstName())
                    .setLastName(employeeEntity.getLastName())
                    .setBirthday(DateUtil.toTimestamp(employeeEntity.getBirthday()))
                    .build();

            List<ProjectInfo> projects = new ArrayList<>();

            employeeEntity.getEnrolledProjects().forEach(projectEntity -> {
                Date end = projectEntity.getEnd();

                EmployeeEntity leader = projectEntity.getGroup().getGroupLeader();

                EmployeeInfo leaderInfo = EmployeeInfo.newBuilder()
                        .setId(leader.getId())
                        .setVisa(leader.getVisa())
                        .setFirstName(leader.getFirstName())
                        .setLastName(leader.getLastName())
                        .setBirthday(DateUtil.toTimestamp(leader.getBirthday()))
                        .build();

                GroupInfo groupInfo = GroupInfo.newBuilder()
                        .setId(projectEntity.getGroup().getId())
                        .setLeader(leaderInfo)
                        .build();

                ProjectInfo projectInfo = ProjectInfo.newBuilder()
                        .setId(projectEntity.getId())
                        .setNumber(projectEntity.getNumber())
                        .setGroup(groupInfo)
                        .setName(projectEntity.getName())
                        .setCustomer(projectEntity.getCustomer())
                        .setStatus(projectEntity.getStatus())
                        .setStart(DateUtil.toTimestamp(projectEntity.getStart()))
                        .setEnd((end != null)
                                ? DateUtil.toTimestamp(end)
                                : Timestamp.newBuilder().build())
                        .build();

                projects.add(projectInfo);
            });

            Employee employee = Employee.newBuilder()
                    .setEmployeeInfo(employeeInfo)
                    .addAllEnrolledProjects(projects)
                    .build();

            responseObserver.onNext(employee);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getEmployeeList(Empty request, StreamObserver<EmployeeList> responseObserver) {
        try {

            List<EmployeeEntity> employeeEntities = employeeDao
                    .getAllEmployees();

            List<Employee> employees = new ArrayList<>();

            employeeEntities.forEach(employeeEntity -> {

                EmployeeInfo employeeInfo = EmployeeInfo.newBuilder()
                        .setId(employeeEntity.getId())
                        .setVisa(employeeEntity.getVisa())
                        .setFirstName(employeeEntity.getFirstName())
                        .setLastName(employeeEntity.getLastName())
                        .setBirthday(DateUtil.toTimestamp(employeeEntity.getBirthday()))
                        .build();

                Employee employee = Employee.newBuilder()
                        .setEmployeeInfo(employeeInfo)
                        .build();

                employees.add(employee);
            });

            EmployeeList employeeList = EmployeeList.newBuilder()
                    .addAllEmployees(employees)
                    .build();

            responseObserver.onNext(employeeList);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(EmployeeList.newBuilder()
                    .addAllEmployees(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }
}
