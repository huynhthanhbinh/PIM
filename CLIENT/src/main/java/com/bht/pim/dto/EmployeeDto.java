package com.bht.pim.dto;

import java.time.LocalDate;
import java.util.List;

import com.bht.pim.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author bht
 */
@Getter
@AllArgsConstructor
public final class EmployeeDto extends BaseDto {

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

    public static Builder newBuilder() {
        return new Builder();
    }

    public final Builder toBuilder() {
        return new Builder()
                .setId(id)
                .setVersion(version)
                .setVisa(visa)
                .setLastName(lastName)
                .setFirstName(firstName)
                .setBirthday(birthday)
                .setEnrolledProjects(enrolledProjects);
    }

    @Setter
    @ToString
    @NoArgsConstructor
    @Accessors(chain = true)
    public static final class Builder {
        private Long id;
        private Long version;
        private String visa;
        private String lastName;
        private String firstName;
        private LocalDate birthday;
        private List<ProjectDto> enrolledProjects;

        public EmployeeDto build() {
            return (EmployeeDto) new EmployeeDto(visa, lastName, firstName, birthday, enrolledProjects)
                    .setId(id).setVersion(version);
        }
    }
}