package com.bht.pim.entities;

import javax.persistence.*;

@Table(name = "[GROUP]")
@Entity(name = "[GROUP]")
public class GroupEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "GROUP_LEADER_ID", nullable = false)
    private long groupLeaderId;

    // Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupLeaderId() {
        return groupLeaderId;
    }

    public void setGroupLeaderId(long groupLeaderId) {
        this.groupLeaderId = groupLeaderId;
    }
}
