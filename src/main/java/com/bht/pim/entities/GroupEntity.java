package com.bht.pim.entities;

import javax.persistence.*;
import java.util.Set;

@Table(name = "[GROUP]")
@Entity(name = "[GROUP]")
public class GroupEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne
    @JoinColumn(name = "GROUP_LEADER_ID", nullable = false,
            referencedColumnName = "ID")
    private EmployeeEntity groupLeader;


    @OneToMany(mappedBy = "group")
    private Set<ProjectEntity> joinedProjects;

    // Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeeEntity getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(EmployeeEntity groupLeader) {
        this.groupLeader = groupLeader;
    }
}
