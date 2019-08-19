package com.bht.pim.dto;

import lombok.*;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
public class GroupDto {

    @NonNull
    private long id;
    private EmployeeDto leader;
    private List<ProjectDto> enrolledProjects;
}
