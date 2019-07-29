package com.bht.pim.service;

import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.proto.employee.Employee;
import com.bht.pim.proto.project.*;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@GRpcService
public class ProjectServiceImpl extends ProjectServiceGrpc.ProjectServiceImplBase {

    @Autowired
    ProjectDao projectDao;
    private Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    @Override
    public void getProjectById(ProjectId request, StreamObserver<ProjectInfo> responseObserver) {

        ProjectEntity projectEntity = projectDao
                .getProjectById(request.getId());

        try {
            Date end = projectEntity.getEnd();

            Project project = Project.newBuilder()
                    .setId(projectEntity.getId())
                    .setNumber(projectEntity.getNumber())
                    .setName(projectEntity.getName())
                    .setCustomer(projectEntity.getCustomer())
                    .setGroupId(projectEntity.getGroup().getId())
                    .setStatus(projectEntity.getStatus())
                    .setStart(projectEntity.getStart().getTime())
                    .setEnd((end != null) ? end.getTime() : 0)
                    .build();

            List<Employee> employees = new ArrayList<>();

            projectEntity.getEnrolledEmployees().forEach(employeeEntity -> {
                Employee employee = Employee.newBuilder()
                        .setId(employeeEntity.getId())
                        .setVisa(employeeEntity.getVisa())
                        .setFirstName(employeeEntity.getFirstName())
                        .setLastName(employeeEntity.getLastName())
                        .build();

                employees.add(employee);
            });

            ProjectInfo projectInfo = ProjectInfo.newBuilder()
                    .setGroupId(projectEntity.getGroup().getId())
                    .setProject(project)
                    .addAllEmployees(employees)
                    .build();

            responseObserver.onNext(projectInfo);
            responseObserver.onCompleted();

        } catch (Exception exception) {

            logger.info(exception);
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void addNewProject(Project request, StreamObserver<Success> responseObserver) {
        super.addNewProject(request, responseObserver);
    }

    @Override
    public void editProject(Project request, StreamObserver<Success> responseObserver) {
        super.editProject(request, responseObserver);
    }

    @Override
    public void deleteProject(ProjectId request, StreamObserver<Success> responseObserver) {
        super.deleteProject(request, responseObserver);
    }
}
