package com.bht.pim.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Project {

    public Project() {
        id = 0;
        number = 0;
        name = "";
        customer = "";
        group = "";
        status = Status.NEW;
        start = new Date();
        end = new Date();
        members = new HashMap<>();
    }

    private int id;
    private long number;
    private String name;
    private String customer;
    private String group;
    private Status status;
    private Date start;
    private Date end;
    //Map.Entry<String, String> member;
    private Map<String, String> members;

    public Project(Project project) {
        id = project.id;
        number = project.number;
        name = project.name;
        customer = project.customer;
        group = project.group;
        status = project.status;
        start = project.start;
        end = project.end;
        members = project.members;
    }

    private enum Status {
        NEW,
        PLANNED,
        IN_PROGRESS,
        FINISHED,
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public Map<String, String> getMembers() {
        return members;
    }

    public void setMembers(Map<String, String> members) {
        this.members = members;
    }
}