package com.bht.pim.dto;

import java.time.LocalDate;
import java.util.List;

import com.bht.pim.base.BaseDto;

import javafx.beans.property.StringProperty;
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
@ToString
@AllArgsConstructor
public final class ProjectDto extends BaseDto {

    private Long number;
    private String name;
    private String customer;
    private StringProperty status;
    private LocalDate start;
    private LocalDate end;
    private GroupDto group;
    private List<EmployeeDto> members;

    public static Builder newBuilder() {
        return new Builder();
    }

    public final Builder toBuilder() {
        return new Builder()
                .setId(id)
                .setVersion(version)
                .setNumber(number)
                .setName(name)
                .setCustomer(customer)
                .setStatus(status)
                .setStart(start)
                .setEnd(end)
                .setGroup(group)
                .setMembers(members);
    }

    @Setter
    @ToString
    @NoArgsConstructor
    @Accessors(chain = true)
    public static final class Builder {
        private Long id;
        private Long version;
        private Long number;
        private String name;
        private String customer;
        private StringProperty status;
        private LocalDate start;
        private LocalDate end;
        private GroupDto group;
        private List<EmployeeDto> members;

        public final ProjectDto build() {
            return (ProjectDto) new ProjectDto(number, name, customer, status, start, end, group, members)
                    .setId(id).setVersion(version);
        }
    }
}