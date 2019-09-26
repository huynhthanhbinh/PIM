package com.bht.pim.service;

import java.util.Collections;
import java.util.List;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.mapper.EmployeeMapper;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeList;
import com.bht.pim.proto.employees.EmployeePagination;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;

import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;

@Log4j
@GRpcService
public final class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {


    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public void getEmployeeById(Int64Value request, StreamObserver<Employee> responseObserver) {
        try {
            Employee employee = employeeMapper.toEmployee(
                    employeeDao.getEmployeeById(request.getValue()));

            responseObserver.onNext(employee);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            log.warn(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getEmployeeList(EmployeePagination pagination, StreamObserver<EmployeeList> responseObserver) {
        try {
            List<EmployeeEntity> employeeEntities = employeeDao.getEmployeeList(
                    pagination.getMaxRow(),
                    pagination.getPageIndex());

            List<Employee> employees = employeeMapper.toEmployeeList(employeeEntities);

            EmployeeList employeeList = EmployeeList.newBuilder()
                    .addAllEmployees(employees)
                    .build();

            responseObserver.onNext(employeeList);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            // log the exception out
            log.warn(exception);

            // return an empty list not return null value for list
            responseObserver.onNext(EmployeeList.newBuilder()
                    .addAllEmployees(Collections.emptyList()).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getNumberOfEmployees(Empty request, StreamObserver<Int64Value> responseObserver) {
        try {
            responseObserver.onNext(Int64Value.newBuilder()
                    .setValue(employeeDao.getNumberOfEmployees())
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
}
