package com.bht.pim.dto;

import lombok.*;
import lombok.extern.log4j.Log4j;

import java.time.LocalDate;
import java.util.List;

@Log4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeDto {

    @NonNull
    private long id;
    @NonNull
    private String visa;
    @NonNull
    private String lastName;
    @NonNull
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
