package com.bht.pim.fragment.group;

import com.bht.pim.configuration.AppConfiguration;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;

@Log4j
@Fragment(id = AppConfiguration.FRAGMENT_GROUP_LIST,
        viewLocation = "GroupList.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        scope = Scope.PROTOTYPE)
public class GroupList {
}
