package com.bht.pim.fragment.children.label;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author bht
 */
@Controller
@Fragment(id = MainLabelFragment.ID, scope = Scope.PROTOTYPE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/children/label/MainLabelFragment.fxml")
public class MainLabelFragment extends ChildFragment {

    static final String ID = "idfMLabel"; // label of main-pane

    @FXML
    private Label label;

    @Override
    public void onCreated() {
        LOGGER.info("[Main Label] Initialization");
    }

    @FXML
    public void setLabelText(String newLabel) {
        LanguageUtil.initLabel(label.textProperty(), newLabel);
    }

    @Override
    public void onSwitchParentFragment() {
        // ...
    }
}