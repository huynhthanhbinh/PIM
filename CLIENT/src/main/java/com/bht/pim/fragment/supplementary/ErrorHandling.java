package com.bht.pim.fragment.supplementary;

import com.bht.pim.configuration.AppConfiguration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
@Fragment(id = AppConfiguration.FRAGMENT_SUPPLEMENTARY_ERROR,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/supplementary/ErrorHandling.fxml")
public class ErrorHandling implements Initializable {

    @Resource
    private Context context;
    @FXML
    private VBox errorPane;
    @FXML
    private Label lUnexpected;
    @FXML
    private Label lPlease;
    @FXML
    private Label lContact;
    @FXML
    private Label lOr;
    @FXML
    private Label lReload;
    @FXML
    private Label lError;
    @FXML
    private TextArea detailField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        detailField.setEditable(false);
    }

    public void setDetail(Throwable detail) {
        detailField.setText(detail.getClass().getName()
                + "\n" + detail.getMessage());
    }
}
