package com.bht.pim.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
public class EmployeeDto {

    @NonNull
    private long id;
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
        return id == employeeDTO.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
