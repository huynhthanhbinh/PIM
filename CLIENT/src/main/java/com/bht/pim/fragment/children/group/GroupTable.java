package com.bht.pim.fragment.children.group;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.ParentOwning;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_GROUP_TABLE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/group/GroupTable.fxml")
public class GroupTable implements Initializable, ParentOwning {

    @FXML
    @Getter
    private VBox mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Group Table] Initialization");
    }

    @Override
    public void onSwitchParentFragment() {

    }
}
