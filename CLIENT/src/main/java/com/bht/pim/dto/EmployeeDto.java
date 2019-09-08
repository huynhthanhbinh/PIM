package com.bht.pim.dto;

import java.time.LocalDate;
import java.util.List;

import com.bht.pim.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bht
 */
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
public final class EmployeeDto extends BaseDto {

    @lombok.Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
    public EmployeeDto(Long id, Long version) {
        super(id, version);
    }

    private String visa;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private List<ProjectDto> enrolledProjects;

    @Override
    public String toString() {
        return visa + " - " + lastName + " " + firstName;
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
        return id.equals(employeeDTO.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}