package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeList;
import com.bht.pim.proto.employees.EmployeeListServiceGrpc;
import com.bht.pim.proto.employees.NoParam;
import com.bht.pim.util.DateUtil;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@GRpcService
public class EmployeeListServiceImpl extends EmployeeListServiceGrpc.EmployeeListServiceImplBase {

    @Autowired
    EmployeeDao employeeDao;
    private Logger logger = Logger.getLogger(EmployeeListServiceImpl.class);

    @Override
    public void getEmployeeList(NoParam request, StreamObserver<EmployeeList> responseObserver) {
        try {

            List<EmployeeEntity> employeeEntities = employeeDao
                    .getAllEmployees();

            List<Employee> employees = new ArrayList<>();

            employeeEntities.forEach(employeeEntity -> {

                Employee employee = Employee.newBuilder()
                        .setId(employeeEntity.getId())
                        .setVisa(employeeEntity.getVisa())
                        .setFirstName(employeeEntity.getFirstName())
                        .setLastName(employeeEntity.getLastName())
                        .setBirthday(DateUtil.toTimestamp(employeeEntity.getBirthday()))
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
            logger.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(EmployeeList.newBuilder()
                    .addAllEmployees(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }
}
