package com.bht.pim.fragment.supplementary;

import java.net.URL;
import java.util.ResourceBundle;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

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
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_SUPPLEMENTARY_LOGIN,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/supplementary/Login.fxml")
public class Login implements Initializable {

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
