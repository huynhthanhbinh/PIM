package com.bht.pim.dto;

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
public class GroupDto {

    @NonNull
    private long id;
    private EmployeeDto leader;
    private List<ProjectDto> enrolledProjects;

    public String getLeaderVisa() {
        return leader.getVisa();
    }

    public String getLeaderFirstName() {
        return leader.getFirstName();
    }

    public String getLeaderLastName() {
        return leader.getLastName();
    }
}
