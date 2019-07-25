package com.bht.pim.entities;

import javax.persistence.*;

@Entity(name = "PROJECT_EMPLOYEE")
public class ProjectEmployeeEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PROJECT_ID", nullable = false)
    private long projectId;

    @Column(name = "EMPLOYEE_ID", nullable = false)
    private long employeeId;

    // Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
}
