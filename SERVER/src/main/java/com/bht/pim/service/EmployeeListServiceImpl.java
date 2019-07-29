package com.bht.pim.service;

import com.bht.pim.proto.employee.EmployeeList;
import com.bht.pim.proto.employee.EmployeeListServiceGrpc;
import com.bht.pim.proto.employee.NoParam;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class EmployeeListServiceImpl extends EmployeeListServiceGrpc.EmployeeListServiceImplBase {

    @Override
    public void getEmployeeList(NoParam request, StreamObserver<EmployeeList> responseObserver) {
        super.getEmployeeList(request, responseObserver);
    }
}
