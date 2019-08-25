package com.bht.pim.fragment.children.label;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.ParentOwning;
import com.bht.pim.util.LanguageUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;


@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_MAIN_LABEL,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/label/MainLabel.fxml")
public class MainLabel implements ParentOwning, Initializable {

    @FXML
    private Label label;

    @FXML
    public void setLabelText(String newLabel) {
        LanguageUtil.initLabel(label.textProperty(), newLabel);
    }

    @Override
    public void onSwitchParentFragment() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Main Label] Initialization");
    }
}
