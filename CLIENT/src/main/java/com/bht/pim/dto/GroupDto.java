package com.bht.pim.dto;

import lombok.*;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
public class GroupDto {

    private long id;
    private EmployeeDto leader;
    private List<ProjectDto> enrolledProjects;
}
