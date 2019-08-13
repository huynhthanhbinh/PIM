package com.bht.pim.service.employee;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeList;
import com.bht.pim.proto.employees.EmployeeListServiceGrpc;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j
@GRpcService
public class EmployeeListServiceImpl extends EmployeeListServiceGrpc.EmployeeListServiceImplBase {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void getEmployeeList(Empty request, StreamObserver<EmployeeList> responseObserver) {
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
            log.info(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(EmployeeList.newBuilder()
                    .addAllEmployees(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }
}
