package com.bht.pim.dto;

import lombok.*;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GroupDto {

    @NonNull
    private long id;

    private EmployeeDto leader;
}
