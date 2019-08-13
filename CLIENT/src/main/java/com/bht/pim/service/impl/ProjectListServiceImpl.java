package com.bht.pim.service.impl;

import com.bht.pim.proto.projects.Project;
import com.bht.pim.proto.projects.ProjectListServiceGrpc;
import com.bht.pim.service.ProjectListService;
import com.google.protobuf.Empty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectListServiceImpl implements ProjectListService {

    @Autowired
    private ProjectListServiceGrpc.ProjectListServiceBlockingStub stub;

    // Get all project numbers
    @Override
    public List<Long> getProjectNumbers() {
        return stub.getProjectNumbers(Empty.getDefaultInstance())
                .getProjectNumbersList();
    }

    // Get all of projects
    @Override
    public ObservableList<Project> getAllProjects() {
        return FXCollections.observableArrayList(stub
                .getProjectList(Empty.getDefaultInstance())
                .getProjectsList());
    }
}
