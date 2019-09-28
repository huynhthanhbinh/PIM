package com.bht.pim.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "[GROUP]")
@Entity(name = "GROUP")
public final class GroupEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "GROUP_LEADER_ID", nullable = false,
            referencedColumnName = "ID", unique = true)
    private EmployeeEntity groupLeader;


    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<ProjectEntity> joinedProjects;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GroupEntity)) {
            return false;
        }

        GroupEntity group = (GroupEntity) obj;

        return groupLeader.getVisa()
                .equals(group.groupLeader.getVisa());
    }


    @Override
    public int hashCode() {
        return groupLeader.getVisa().hashCode();
    }
}
