package com.bht.pim.dto;

import java.time.LocalDate;
import java.util.List;

import com.bht.pim.base.BaseDto;

import javafx.beans.property.StringProperty;
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
public class ProjectDto extends BaseDto {

    @lombok.Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
    public ProjectDto(Long id, Long version) {
        super(id, version);
    }

    private Long number;
    private String name;
    private String customer;
    private StringProperty status;
    private LocalDate start;
    private LocalDate end;
    private GroupDto group;
    private List<EmployeeDto> members;
}
