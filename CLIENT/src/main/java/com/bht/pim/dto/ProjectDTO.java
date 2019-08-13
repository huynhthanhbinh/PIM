package com.bht.pim.dto;

import javafx.beans.property.BooleanProperty;
import lombok.*;
import lombok.extern.log4j.Log4j;

import java.time.LocalDate;
import java.util.Set;

@Log4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectDTO {

    private BooleanProperty isSelected;

    @NonNull
    private long id;

    private long number;
    private String name;
    private String customer;
    private String status;
    private LocalDate start;
    private LocalDate end;
    private GroupDTO group;
    private Set<EmployeeDTO> members;
}
