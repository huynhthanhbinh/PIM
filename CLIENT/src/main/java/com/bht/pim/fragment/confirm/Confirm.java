package com.bht.pim.fragment.confirm;

import com.bht.pim.configuration.AppConfiguration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Fragment(id = AppConfiguration.FRAGMENT_CONFIRM,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/confirm/Confirm.fxml")
public class Confirm implements Initializable {

    @FXML
    private Button bSubmit;
    @FXML
    private Button bCancel;

    @Resource
    private Context context;

    @Resource
    private ResourceBundle bundle;

    @FXML
    public void setLabelText(String label) {
        bSubmit.setText(label);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if user click create
        bSubmit.setOnMouseClicked(null);

        // if user click cancel
        bCancel.setOnMouseClicked(null);
    }
}
