package com.bht.pim.service;

import com.bht.pim.proto.project.NoParam;
import com.bht.pim.proto.project.ProjectList;
import com.bht.pim.proto.project.ProjectListServiceGrpc;
import com.bht.pim.proto.project.ProjectNumbers;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class ProjectListServiceImpl extends ProjectListServiceGrpc.ProjectListServiceImplBase {

    @Override
    public void getProjectList(NoParam request, StreamObserver<ProjectList> responseObserver) {
        super.getProjectList(request, responseObserver);
    }

    @Override
    public void getProjectNumbers(NoParam request, StreamObserver<ProjectNumbers> responseObserver) {
        super.getProjectNumbers(request, responseObserver);
    }
}
