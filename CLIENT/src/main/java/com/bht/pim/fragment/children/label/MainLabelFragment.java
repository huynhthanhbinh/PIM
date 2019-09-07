package com.bht.pim.fragment.children.label;

import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

import com.bht.pim.base.BaseFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * @author bht
 */
@Controller
@Fragment(id = MainLabelFragment.ID, scope = Scope.PROTOTYPE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/children/label/MainLabelFragment.fxml")
public final class MainLabelFragment extends BaseFragment {

    static final String ID = "idfMLabel"; // label of main-pane

    @FXML
    private VBox mainLabel;
    @FXML
    private Label label;

    @Override
    public void onCreated() {
        LOGGER.info("[INIT] FXChildFragment  : " + ID);
    }

    @Override
    protected void configLayout() {
        layout = mainLabel;
    }

    @Override
    protected void bindChildren() {
        //
    }

    @FXML
    public void setLabelText(String newLabel) {
        LanguageUtil.initLabel(label.textProperty(), newLabel);
    }
}