package com.bht.pim.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.bht.pim")
@Configuration
public class AppConfig {
    public static final String PERSPECTIVE_ONE = "idPone";

    public static final String COMPONENT_LEFT = "id002";
    public static final String COMPONENT_RIGHT = "id003";

    public static final String TARGET_CONTAINER_LEFT = "PLeft";
    public static final String TARGET_CONTAINER_MAIN = "PMain";
}
