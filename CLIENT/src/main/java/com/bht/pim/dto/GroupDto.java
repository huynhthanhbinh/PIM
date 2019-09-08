package com.bht.pim.dto;

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
public final class GroupDto extends BaseDto {

    @lombok.Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
    public GroupDto(Long id, Long version) {
        super(id, version);
    }

    private EmployeeDto leader;
    private List<ProjectDto> enrolledProjects;
}