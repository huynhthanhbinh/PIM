package com.bht.pim.dto;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Employee implements Serializable {

    private Logger logger = Logger.getLogger(Employee.class);
    private static final long serialVersionUID = 3005199803L;

    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd/MM/yyyy");

    private long id;
    private String visa;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Set<Project> enrolledProjects;

    @Override
    public String toString() {
        return String.format(
                "%,6d | %3s | %-10s | %-20s | %10s",
                id,
                visa,
                firstName,
                lastName,
                dateFormat.format(birthday));
    }

    public void printInfo() {
        logger.info("");
        logger.info("=============================================================================");
        logger.info("EMPLOYEE INFORMATION");
        logger.info("ID              : " + id);
        logger.info("VISA            : " + visa);
        logger.info("First-name      : " + firstName);
        logger.info("Last-name       : " + lastName);
        logger.info("Birthday        : " + dateFormat.format(birthday));
        logger.info("Projects enroll : " + enrolledProjects.size());
        enrolledProjects.forEach(project -> logger.info(
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

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Project> getEnrolledProjects() {
        return enrolledProjects;
    }

    public void setEnrolledProjects(Set<Project> enrolledProjects) {
        this.enrolledProjects = enrolledProjects;
    }
}
