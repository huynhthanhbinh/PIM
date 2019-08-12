package com.bht.pim.pseudo;

import com.bht.pim.dto.employees.Employee;
import com.bht.pim.dto.groups.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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

    public static Employee toEmployee(long memberId) {
        return Employee.newBuilder().setId(memberId).build();
    }

    public Member(long id, String name) {
        this.id = id;
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
