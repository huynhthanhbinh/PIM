package com.bht.pim.fragment.children.confirm;

import com.bht.pim.configuration.AppConfiguration;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_CONFIRM,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/children/confirm/Confirm.fxml")
public class Confirm implements Initializable {

    @FXML
    private VBox confirmPane;

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
        log.info("[Confirm Box] Initialization");
    }

    public void setOnSubmit(EventHandler<MouseEvent> onSubmit) {
        // if user click submit
        bSubmit.setOnMouseClicked(onSubmit);
    }

    public void setOnCancel(EventHandler<MouseEvent> onCancel) {
        // if user click cancel
        bCancel.setOnMouseClicked(onCancel);
    }
}