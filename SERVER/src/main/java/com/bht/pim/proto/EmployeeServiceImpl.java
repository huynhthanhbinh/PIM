package com.bht.pim.proto;


import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
import com.bht.pim.proto.date.Date;
import com.bht.pim.proto.employee.Employee;
import com.bht.pim.proto.employee.EmployeeId;
import com.bht.pim.proto.employee.EmployeeInfo;
import com.bht.pim.proto.employee.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void getEmployeeById(EmployeeId request, StreamObserver<EmployeeInfo> responseObserver) {

        EmployeeEntity employeeEntity = employeeDao
                .getEmployeeById(request.getId());

        java.sql.Date day = employeeEntity.getBirthday();

        Date birthday = Date.newBuilder()
                .setDd(day.getDate())
                .setMm(day.getMonth() + 1)
                .setYyyy(day.getYear() + 1900)
                .build();

        Employee employee = Employee.newBuilder()
                .setId(employeeEntity.getId())
                .setVisa(employeeEntity.getVisa())
                .setFirstName(employeeEntity.getFirstName())
                .setLastName(employeeEntity.getLastName())
                .setBirthday(birthday)
                .build();

        EmployeeInfo employeeInfo = EmployeeInfo.newBuilder()
                .setEmployee(employee)
                .build();

        responseObserver.onNext(employeeInfo);
        responseObserver.onCompleted();
    }
}
