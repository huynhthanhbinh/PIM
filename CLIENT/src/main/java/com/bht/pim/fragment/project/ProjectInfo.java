package com.bht.pim.fragment.project;

import com.bht.pim.configuration.AppConfiguration;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;

@Log4j
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_INFO,
        viewLocation = "/com/bht/pim/fragment/project/ProjectInfo.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        scope = Scope.PROTOTYPE)
public class ProjectInfo {
}
