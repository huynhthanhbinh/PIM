package com.bht.pim.fragment.children.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.ParentOwning;
import javafx.fxml.Initializable;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_DETAIL,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectDetail.fxml")
public class ProjectDetail implements Initializable, ParentOwning {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void onSwitchParentFragment() {

    }

    public boolean getProjectById(long projectId) {
        return false;
    }
}
