package com.bht.pim.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

    private long id;
    private long groupId;
    private long number;
    private String name;
    private String customer;
    private String status;
    private List<Long> members;
    private Date start;
    private Date end;

    public Project() {
        id = 0;
        number = 0;
        name = "";
        customer = "";
        groupId = 0;
        status = "New";
        start = new Date();
        end = null;
        members = new ArrayList<>();
    }

    public Project(Project project) {
        id = project.id;
        number = project.number;
        name = project.name;
        customer = project.customer;
        groupId = project.groupId;
        status = project.status;
        start = project.start;
        end = project.end;
        members = project.members;
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

    public List<Long> getMembers() {
        return members;
    }

    public void setMembers(List<Long> members) {
        this.members = members;
    }
}