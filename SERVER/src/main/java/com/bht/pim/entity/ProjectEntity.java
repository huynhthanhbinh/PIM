package com.bht.pim.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROJECT")
@Entity(name = "PROJECT")
public class ProjectEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(name = "PROJECT_EMPLOYEE",
            joinColumns = @JoinColumn(name = "PROJECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    private Set<EmployeeEntity> enrolledEmployees;

    @Column(name = "PROJECT_NUMBER", nullable = false, unique = true)
    private long number;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CUSTOMER", nullable = false)
    private String customer;

    // CHAR(3) STATUS
    // NEW = New
    // PLA = Planned
    // INP = In progress
    // FIN = Finished
    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "START_DATE", nullable = false)
    private Date start;

    @Column(name = "END_DATE")
    private Date end;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private GroupEntity group;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProjectEntity)) {
            return false;
        }

        ProjectEntity project = (ProjectEntity) obj;

        return number == project.number
                && name.equals(project.name)
                && customer.equals(project.customer);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
