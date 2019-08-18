package com.bht.pim.fragment.children.group;

import com.bht.pim.configuration.AppConfiguration;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_GROUP_LIST_TABLE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/children/group/GroupListTable.fxml")
public class GroupListTable {
}
