package com.bht.pim.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.ProjectServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author bht
 */
public final class SpringBeanRegistration {

    @Autowired
    private SpringConfiguration config;

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder                            // Channel is the abstraction to connect to a service endpoint
                .forAddress(config.getHost(), config.getPort()) // Port and Host of gRPC server, not of client !
                .usePlaintext()                                 // Let's use plaintext communication because we don't have certs
                .maxInboundMessageSize(10 * 1024 * 1024)        // 10KB * 1024 = 10MB --> max message size to transfer together
                .build();                                       // Builder-design-pattern --> using build method to get object
    }

    @Bean
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub() { // for autowiring service
        return EmployeeServiceGrpc.newBlockingStub(SpringApplicationContext.getBean(ManagedChannel.class));
    }

    @Bean
    public GroupServiceGrpc.GroupServiceBlockingStub groupServiceBlockingStub() { // for autowiring service
        return GroupServiceGrpc.newBlockingStub(SpringApplicationContext.getBean(ManagedChannel.class));
    }

    @Bean
    public ProjectServiceGrpc.ProjectServiceBlockingStub projectServiceBlockingStub() { // for autowiring service
        return ProjectServiceGrpc.newBlockingStub(SpringApplicationContext.getBean(ManagedChannel.class));
    }
}