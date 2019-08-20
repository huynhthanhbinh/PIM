package com.bht.pim.fragment.children.label;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.ParentOwning;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.util.ResourceBundle;


@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_MAIN_LABEL,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/label/MainLabel.fxml")
public class MainLabel implements ParentOwning {

    @FXML
    private Label label;

    @Resource
    private Context context;

    @Resource
    private ResourceBundle bundle;

    @FXML
    public void setLabelText(String main) {
        label.setText(main);
    }

    @Override
    public void onSwitchParentFragment() {

    }
}
