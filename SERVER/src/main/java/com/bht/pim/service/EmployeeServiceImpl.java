package com.bht.pim.service;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.mapper.EmployeeMapper;
import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeList;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.util.DateUtil;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Log4j
@GRpcService
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

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

            log.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getEmployeeList(Empty request, StreamObserver<EmployeeList> responseObserver) {
        try {
            List<EmployeeEntity> employeeEntities = employeeDao.getAllEmployees();
            List<Employee> employees = employeeMapper.toEmployeeList(employeeEntities);

            EmployeeList employeeList = EmployeeList.newBuilder()
                    .addAllEmployees(employees)
                    .build();

            Timestamp timestamp;
            timestamp = DateUtil.toTimestamp(employeeDao.getEmployeeById(11).getBirthday());
            log.info(timestamp);
            timestamp = DateUtil.toTimestamp(employeeDao.getEmployeeById(12).getBirthday());
            log.info(timestamp);
            timestamp = DateUtil.toTimestamp(employeeDao.getEmployeeById(13).getBirthday());
            log.info(timestamp);
            timestamp = DateUtil.toTimestamp(employeeDao.getEmployeeById(14).getBirthday());
            log.info(timestamp);
            timestamp = DateUtil.toTimestamp(employeeDao.getEmployeeById(15).getBirthday());
            log.info(timestamp);
            timestamp = DateUtil.toTimestamp(employeeDao.getEmployeeById(16).getBirthday());
            log.info(timestamp);
            timestamp = DateUtil.toTimestamp(employeeDao.getEmployeeById(17).getBirthday());
            log.info(timestamp);
            timestamp = DateUtil.toTimestamp(employeeDao.getEmployeeById(18).getBirthday());
            log.info(timestamp);

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
