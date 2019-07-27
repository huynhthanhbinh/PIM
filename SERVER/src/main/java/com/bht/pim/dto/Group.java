package com.bht.pim.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Group implements Serializable {

    private static final long serialVersionUID = 3005199802L;

    private long id;
    private long groupLeaderId;
    private String groupLeaderVisa;
    private String groupLeaderName;
    private Set<Project> projects;

    public Group() {
        id = 0;
        groupLeaderId = 0;
        groupLeaderName = "";
        projects = new HashSet<>();
    }


    @Override
    public String toString() {
        return String.format(
                "%,6d | %,6d | %s",
                id,
                groupLeaderId,
                groupLeaderName);
    }

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

    public String getGroupLeaderName() {
        return groupLeaderName;
    }

    public void setGroupLeaderName(String groupLeaderName) {
        this.groupLeaderName = groupLeaderName;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public String getGroupLeaderVisa() {
        return groupLeaderVisa;
    }

    public void setGroupLeaderVisa(String groupLeaderVisa) {
        this.groupLeaderVisa = groupLeaderVisa;
    }
}
