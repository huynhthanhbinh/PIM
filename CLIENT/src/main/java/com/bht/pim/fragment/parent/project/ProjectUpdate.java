package com.bht.pim.fragment.parent.project;

import com.bht.pim.configuration.AppConfiguration;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_UPDATE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.SINGLETON)
public class ProjectUpdate {
}
