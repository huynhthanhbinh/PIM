package com.bht.pim.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bht.pim.entity.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMPLOYEE")
@Entity(name = "EMPLOYEE")
public final class EmployeeEntity extends BaseEntity {

    @Column(name = "VISA", nullable = false, unique = true)
    private String visa;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthday;

    @ManyToMany(mappedBy = "enrolledEmployees", fetch = FetchType.EAGER)
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

        return id.equals(employee.id);
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
