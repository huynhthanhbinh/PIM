package com.bht.pim.spring;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.proto.employees.EmployeeServiceGrpc;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.bht.pim.proto.projects.ProjectServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author bht
 */
@Component
public final class SpringBeanRegistration implements BaseBean {

    @Bean
    public ManagedChannel managedChannel(@Value("${pim.server.host}") String host,
                                         @Value("${pim.server.port}") int port) {
        return ManagedChannelBuilder                            // Channel is the abstraction to connect to a service endpoint
                .forAddress(host, port)                         // Port and Host of gRPC server, not of client !
                .usePlaintext()                                 // Let's use plaintext communication because we don't have certs
                .maxInboundMessageSize(10 * 1024 * 1024)        // 10KB * 1024 = 10MB --> max message size to transfer together
                .idleTimeout(3000, TimeUnit.MILLISECONDS)     // 3000 milliseconds / 1000 = 3 seconds --> request time-out
                .build();                                       // Builder-design-pattern --> using build method to get object
    }

    @Bean
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub(@Autowired ManagedChannel channel) { // for autowiring service
        return EmployeeServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public GroupServiceGrpc.GroupServiceBlockingStub groupServiceBlockingStub(@Autowired ManagedChannel channel) { // for autowiring service
        return GroupServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public ProjectServiceGrpc.ProjectServiceBlockingStub projectServiceBlockingStub(@Autowired ManagedChannel channel) { // for autowiring service
        return ProjectServiceGrpc.newBlockingStub(channel);
    }
}