package com.bht.pim.fragment.supplementary;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author bht
 */
@Controller
@Fragment(id = ErrorHandlingFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/supplementary/ErrorHandlingFragment.fxml")
public class ErrorHandlingFragment implements Initializable {

    static final String ID = "idfSError";

    @Value("${pim.server.host}")
    private String host;

    @Value("${pim.server.port}")
    private int port;

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
        LanguageUtil.initLabel(lUnexpected.textProperty(), "label.error.unexpectederror");
        LanguageUtil.initLabel(lPlease.textProperty(), "label.error.please");
        LanguageUtil.initLabel(lContact.textProperty(), "label.error.contactadmin");
        LanguageUtil.initLabel(lOr.textProperty(), "label.error.or");
        LanguageUtil.initLabel(lReload.textProperty(), "label.error.reload");
        LanguageUtil.initLabel(lError.textProperty(), "label.error.detail");

        lContact.setOnMouseClicked(this::onContactAdmin);
        lReload.setOnMouseClicked(this::onReloadApp);
    }

    public void setDetail(Throwable detail) {
        detailField.setText(detail.getClass().getName()
                + "\n" + detail.getMessage());
    }

    private void onContactAdmin(MouseEvent mouseEvent) {
        // ...
    }

    private void onReloadApp(MouseEvent mouseEvent) {
        AppConfiguration.CHANNEL_PROPERTY.get().resetConnectBackoff();
        context.send(AppConfiguration.PERSPECTIVE_PIM, "show");
    }
}
