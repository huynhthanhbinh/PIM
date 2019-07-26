package com.bht.pim.entities;

import javax.persistence.*;

@Table(name = "PROJECT_EMPLOYEE")
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ProjectEmployeeEntity)) {
            return false;
        }

        ProjectEmployeeEntity entity = (ProjectEmployeeEntity) obj;
        return entity.employeeId == employeeId
                && entity.projectId == projectId;
    }

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
