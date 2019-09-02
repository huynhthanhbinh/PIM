package com.bht.pim.entity.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Group by status
@AllArgsConstructor
public class ProjectEntityGroup {

    @Getter
    private String status;
    @Getter
    private long count;

    @Override
    public String toString() {
        return status + ": " + count;
    }
}