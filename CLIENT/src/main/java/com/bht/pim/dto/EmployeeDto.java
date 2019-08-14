package com.bht.pim.dto;

import com.bht.pim.proto.employees.Employee;
import com.bht.pim.proto.employees.EmployeeInfo;
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
public class EmployeeDto {
    @NonNull
    private final long id;
    @NonNull
    private final String visa;
    @NonNull
    private final String name;

    private LocalDate birthday;

    public static EmployeeDto toMember(Employee employee) {
        return toMember(employee.getEmployeeInfo());
    }

    private static EmployeeDto toMember(EmployeeInfo employeeInfo) {
        return new EmployeeDto(employeeInfo.getId(), employeeInfo.getVisa(),
                employeeInfo.getLastName() + " " + employeeInfo.getFirstName());
    }

    public static EmployeeDto toMember(Group group) {
        EmployeeInfo leader = group.getGroupInfo().getLeader();
        return toMember(leader);
    }

    public static EmployeeInfo toEmployeeInfo(long memberId) {
        return EmployeeInfo.newBuilder().setId(memberId).build();
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
        if (!(obj instanceof EmployeeDto)) {
            return false;
        }

        EmployeeDto employeeDTO = (EmployeeDto) obj;
        return id == employeeDTO.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
