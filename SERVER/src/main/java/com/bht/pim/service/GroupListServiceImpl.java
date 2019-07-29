package com.bht.pim.service;

import com.bht.pim.proto.group.GroupList;
import com.bht.pim.proto.group.GroupListServiceGrpc;
import com.bht.pim.proto.group.NoParam;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GroupListServiceImpl extends GroupListServiceGrpc.GroupListServiceImplBase {

    @Override
    public void getEmployeeList(NoParam request, StreamObserver<GroupList> responseObserver) {
        super.getEmployeeList(request, responseObserver);
    }
}
