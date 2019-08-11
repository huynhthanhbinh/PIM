package com.bht.pim.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.bht.pim")
@Configuration
public class AppConfiguration {
    public static final String PERSPECTIVE = "idPIMPerspective";

    public static final String LANGUAGE_BUNDLES = "bundles.languageBundle";

    private AppConfiguration() {
    }

    public static final String TARGET_CONTAINER_TOP = "PTop";
    public static final String TARGET_CONTAINER_LEFT = "PLeft";
    public static final String TARGET_CONTAINER_MAIN = "PMain";

    public static final String COMPONENT_TOP = "idcTop";
    public static final String COMPONENT_LEFT = "idcLeft";
    public static final String COMPONENT_MAIN = "idcMain";

    public static final String FRAGMENT_MAIN_LABEL = "idfMLabel";

    public static final String FRAGMENT_PROJECT_LIST = "idfPList";
    public static final String FRAGMENT_PROJECT_INFO = "idfPInfo";
    public static final String FRAGMENT_PROJECT_CREATE = "idfPCreate";
    public static final String FRAGMENT_PROJECT_UPDATE = "idfPUpdate";

    public static final String FRAGMENT_GROUP_LIST = "idfGList";
    public static final String FRAGMENT_GROUP_INFO = "idfGInfo";

    public static final String FRAGMENT_EMPLOYEE_LIST = "idfEList";
    public static final String FRAGMENT_EMPLOYEE_INFO = "idfEInfo";

    public static final String LABEL_PROJECT_LIST = "PROJECT LIST";
    public static final String LABEL_PROJECT_CREATE = "NEW PROJECT";
}
