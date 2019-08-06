package com.bht.pim.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

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
    public String toString() {
        enrolledEmployees.forEach(System.out::println);

        return super.toString();
    }

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

    // Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Set<EmployeeEntity> getEnrolledEmployees() {
        return enrolledEmployees;
    }

    public void setEnrolledEmployees(Set<EmployeeEntity> enrolledEmployees) {
        this.enrolledEmployees = enrolledEmployees;
    }
}
