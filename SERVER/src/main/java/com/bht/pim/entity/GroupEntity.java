package com.bht.pim.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "[GROUP]")
@Entity(name = "GROUP")
public final class GroupEntity extends BaseEntity {

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

        return groupLeader.getId().equals(group.groupLeader.getId());
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
