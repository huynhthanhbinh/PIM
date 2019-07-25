package com.bht.pim.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "PROJECT")
public class ProjectEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "GROUP_ID", nullable = false)
    private long groupId;

    @Column(name = "PROJECT_NUMBER", nullable = false, unique = true)
    private long number;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CUSTOMER", nullable = false)
    private String customer;

    // CHAR(3) STATUS
    // NEW = New
    // PLA = Planned
    // INP = In progress
    // FIN = Finished
    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "START_DATE", nullable = false)
    private Date start;

    @Column(name = "END_DATE")
    private Date end;


    // Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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
}