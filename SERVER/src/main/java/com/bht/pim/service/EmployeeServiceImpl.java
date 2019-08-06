package com.bht.pim.service;


import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeId;
import com.bht.pim.proto.employees.EmployeeInfo;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.projects.Project;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@GRpcService
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    private Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void getEmployeeById(EmployeeId request, StreamObserver<EmployeeInfo> responseObserver) {

        EmployeeEntity employeeEntity = employeeDao
                .getEmployeeById(request.getId());

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

            logger.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }
}
