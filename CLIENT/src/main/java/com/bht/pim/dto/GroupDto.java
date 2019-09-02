package com.bht.pim.dto;

import lombok.*;
import lombok.extern.log4j.Log4j;

import java.util.List;

/**
 * @author bht
 */
@Log4j
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
public final class GroupDto {

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
