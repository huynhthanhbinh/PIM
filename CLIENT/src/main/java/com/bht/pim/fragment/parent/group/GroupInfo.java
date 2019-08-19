package com.bht.pim.fragment.parent.group;

import com.bht.pim.configuration.AppConfiguration;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_GROUP_INFO,
<<<<<<< HEAD:CLIENT/src/main/java/com/bht/pim/fragment/parent/group/GroupInfo.java
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/parent/group/GroupInfo.fxml")
=======
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/group/GroupInfo.fxml")
>>>>>>> parent of f33f974... [ProjectCreate] Saving Project:CLIENT/src/main/java/com/bht/pim/fragment/group/GroupInfo.java
public class GroupInfo {
}
