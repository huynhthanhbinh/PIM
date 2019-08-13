package com.bht.pim.dto;

import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.groups.Group;
import lombok.*;
import lombok.extern.log4j.Log4j;

import java.time.LocalDate;

@Log4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeDTO {

    @NonNull
    private final long id;
    @NonNull
    private final String visa;
    @NonNull
    private final String name;

    private LocalDate birthday;

    public static EmployeeDTO toMember(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getVisa(),
                employee.getLastName() + " " + employee.getFirstName());
    }

    public static EmployeeDTO toMember(Group group) {
        Employee leader = group.getLeader();
        return toMember(leader);
    }

    public static Employee toEmployee(long memberId) {
        return Employee.newBuilder().setId(memberId).build();
    }

    @Override
    public String toString() {
        return visa + " - " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmployeeDTO)) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) obj;
        return id == employeeDTO.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
