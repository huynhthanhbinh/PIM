package com.bht.pim.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SelectBeforeUpdate;

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
@SelectBeforeUpdate
@Table(name = "PROJECT")
@Entity(name = "PROJECT")
public final class ProjectEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "PROJECT_EMPLOYEE",
            joinColumns = @JoinColumn(name = "PROJECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    private Set<EmployeeEntity> enrolledEmployees;

    @Column(name = "PROJECT_NUMBER", nullable = false, unique = true)
    private Long number;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CUSTOMER", nullable = false)
    private String customer;

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

        return id.equals(project.id);
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
