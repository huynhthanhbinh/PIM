package com.bht.pim.dto;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Project implements Serializable {

    private static final long serialVersionUID = 3005199801L;
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd/MM/yyyy");
    private Logger logger = Logger.getLogger(Project.class);

    private long id;
    private long number;
    private long groupId;
    private long groupLeaderId;
    private String groupLeaderName;
    private String name;
    private String customer;
    private String status;
    private Set<Employee> members;
    private Date start;
    private Date end;

    public Project() {
        id = 0;
        number = 0;
        name = "";
        customer = "";
        groupId = 0;
        groupLeaderName = "";
        status = "New";
        start = new Date();
        end = null;
        members = new HashSet<>();
    }

    public Project(Project project) {
        id = project.id;
        number = project.number;
        name = project.name;
        customer = project.customer;
        groupId = project.groupId;
        status = project.status;
        groupLeaderName = project.groupLeaderName;
        start = new Date(project.start.getTime());
        end = new Date(project.end.getTime());
        members = new HashSet<>(project.members);
    }

    @Override
    public String toString() {
        return String.format(
                "%,6d | %,6d | %,6d | %-30s | %-20s | %-11s | %10s | %10s",
                id,
                number,
                groupId,
                name,
                customer,
                status,
                dateFormat.format(start),
                (end != null) ? dateFormat.format(end) : "null"
        );
    }

    public void printInfo() {
        logger.info("=======================================================");
        logger.info("PROJECT INFORMATION");
        logger.info("ID             : " + id);
        logger.info("Project Number : " + number);
        logger.info("Project Name   : " + name);
        logger.info("Customer       : " + customer);
        logger.info("Group joined   : " + groupId);
        logger.info("Group leaderID : " + groupLeaderId);
        logger.info("Group leader   : " + groupLeaderName);
        logger.info("Status         : " + status);
        logger.info("Start-date     : " + dateFormat.format(start));
        logger.info("End-date       : " +
                ((end != null) ? dateFormat.format(end) : "Not finished"));
        logger.info("Members enroll : " + members.size());
        members.forEach(member -> logger.info(
                String.format("%,6d | %3s | %s",
                        member.getId(),
                        member.getVisa(),
                        member.getLastName() + " " + member.getFirstName())));
        logger.info("=======================================================");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Set<Employee> getMembers() {
        return members;
    }

    public void setMembers(Set<Employee> members) {
        this.members = members;
    }

    public String getGroupLeaderName() {
        return groupLeaderName;
    }

    public void setGroupLeaderName(String groupLeaderName) {
        this.groupLeaderName = groupLeaderName;
    }

    public long getGroupLeaderId() {
        return groupLeaderId;
    }

    public void setGroupLeaderId(long groupLeaderId) {
        this.groupLeaderId = groupLeaderId;
    }
}