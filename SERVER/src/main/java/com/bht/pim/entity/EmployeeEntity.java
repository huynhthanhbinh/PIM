package com.bht.pim.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Table(name = "EMPLOYEE")
@Entity(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "VISA", nullable = false, unique = true)
    private String visa;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthday;

    @ManyToMany(mappedBy = "enrolledEmployees")
    private Set<ProjectEntity> enrolledProjects;

    @OneToOne(mappedBy = "groupLeader", cascade = CascadeType.ALL)
    private GroupEntity ledGroup;


    @Override
    public String toString() {
        return visa + " - " + lastName + " " + firstName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmployeeEntity)) {
            return false;
        }

        EmployeeEntity employee = (EmployeeEntity) obj;

        return visa.equals(employee.visa);
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

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<ProjectEntity> getEnrolledProjects() {
        return enrolledProjects;
    }

    public void setEnrolledProjects(Set<ProjectEntity> enrolledProjects) {
        this.enrolledProjects = enrolledProjects;
    }

    public GroupEntity getLedGroup() {
        return ledGroup;
    }

    public void setLedGroup(GroupEntity ledGroup) {
        this.ledGroup = ledGroup;
    }
}
