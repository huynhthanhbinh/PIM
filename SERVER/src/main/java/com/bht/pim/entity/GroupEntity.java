package com.bht.pim.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "[GROUP]")
@Entity(name = "GROUP")
public class GroupEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


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
        return super.hashCode();
    }
}
