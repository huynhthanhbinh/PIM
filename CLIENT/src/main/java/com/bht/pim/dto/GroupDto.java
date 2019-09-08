package com.bht.pim.dto;

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
@ToString
@AllArgsConstructor
public final class GroupDto extends BaseDto {

    private EmployeeDto leader;
    private List<ProjectDto> enrolledProjects;

    public static Builder newBuilder() {
        return new Builder();
    }

    public final Builder toBuilder() {
        return new Builder()
                .setId(id)
                .setVersion(version)
                .setLeader(leader)
                .setEnrolledProjects(enrolledProjects);
    }

    @Setter
    @ToString
    @NoArgsConstructor
    @Accessors(chain = true)
    public static final class Builder {
        private Long id;
        private Long version;
        private EmployeeDto leader;
        private List<ProjectDto> enrolledProjects;

        public GroupDto build() {
            return (GroupDto) new GroupDto(leader, enrolledProjects)
                    .setId(id).setVersion(version);
        }
    }
}