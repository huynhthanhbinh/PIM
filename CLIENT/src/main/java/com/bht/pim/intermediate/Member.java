package com.bht.pim.intermediate;

import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.groups.Group;

public class Member {

    private long id;
    private String name;

    public static Member toMember(Employee employee) {
        return new Member(employee.getId(),
                employee.getVisa() + " - " +
                        employee.getLastName() + " " + employee.getFirstName());
    }

    public static Member toMember(Group group) {
        Employee leader = group.getLeader();
        return toMember(leader);
    }

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Member)) {
            return false;
        }

        Member member = (Member) obj;
        return id == member.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
