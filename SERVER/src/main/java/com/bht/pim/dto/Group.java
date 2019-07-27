package com.bht.pim.dto;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Group implements Serializable {

    private Logger logger = Logger.getLogger(Group.class);
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

    public void printInfo() {
        logger.info("");
        logger.info("=============================================================================");
        logger.info("GROUP INFORMATION");
        logger.info("ID              : " + id);
        logger.info("Leader ID       : " + groupLeaderId);
        logger.info("Leader VISA     : " + groupLeaderVisa);
        logger.info("Group leader    : " + groupLeaderName);
        logger.info("Projects enroll : " + projects.size());
        projects.forEach(project -> logger.info(
                String.format("%,6d | %,6d | %-30s | %s",
                        project.getId(),
                        project.getNumber(),
                        project.getName(),
                        project.getCustomer())));
        logger.info("=============================================================================");
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
