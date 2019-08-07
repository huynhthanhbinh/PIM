package com.bht.pim.fragment.control;

import com.bht.pim.configuration.AppConfiguration;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;

@Log4j
@Fragment(id = AppConfiguration.FRAGMENT_MENU_TOP,
        resourceBundleLocation = "bundles.languageBundle",
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/control/TopMenu.fxml")
public class TopMenu {
}
