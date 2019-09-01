package com.bht.pim.fragment.supplementary;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
@Fragment(id = Login.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/supplementary/Login.fxml")
public class Login implements Initializable {

    static final String ID = "idfSLogin";

    @Value("${pim.client.username}")
    private String defaultUsername;
    @Value("${pim.client.password}")
    private String defaultPassword;

    @Resource
    private Context context;
    @FXML
    private Label lIncorrect;
    @FXML
    private Button bLogin;
    @FXML
    private Button bForgot;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LanguageUtil.initLabel(bLogin.textProperty(), "label.login.login");
        LanguageUtil.initLabel(bForgot.textProperty(), "label.login.forgot");
        LanguageUtil.initLabel(lIncorrect.textProperty(), "label.login.incorrect");

        lIncorrect.setVisible(false);

        bLogin.setOnMouseClicked(this::onSubmit);
        username.setOnKeyPressed(this::onKeyPressed);
        password.setOnKeyPressed(this::onKeyPressed);
    }

    private void onSubmit(Event event) {
        if (username.getText().equals(defaultUsername)
                && password.getText().equals(defaultPassword)) {

            context.send(AppConfiguration.PERSPECTIVE_PIM, "show");
            AppConfiguration.LOGGED_IN_PROPERTY.set(true);

            username.clear();
            password.clear();
            lIncorrect.setVisible(false);

        } else if (!username.getText().isEmpty() && !password.getText().isEmpty()) {

            lIncorrect.setVisible(true);
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            onSubmit(keyEvent);
        }
    }
}
