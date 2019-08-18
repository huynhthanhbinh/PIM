package com.bht.pim.fragment.children.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.message.impl.FragmentSwitching;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_LIST_UTIL,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectListUtil.fxml")
public class ProjectListUtil {

    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;
    @FXML
    private Button bNew;
    @FXML
    private Button bDelete;

    // Add all event-listener
    private void addAllEventListener() {
        bNew.setOnMouseClicked(event -> {
            log.info("[NEW] on mouse clicked");

            FragmentSwitching switching = new FragmentSwitching(
                    AppConfiguration.FRAGMENT_PROJECT_LIST,
                    AppConfiguration.FRAGMENT_PROJECT_CREATE);

            context.send(AppConfiguration.COMPONENT_MAIN, switching);
        });
    }
}
