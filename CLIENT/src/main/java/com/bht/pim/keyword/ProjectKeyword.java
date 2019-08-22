package com.bht.pim.keyword;

import lombok.Builder;

@Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
public class ProjectKeyword {

    private String status;
    private String keyword;
}
