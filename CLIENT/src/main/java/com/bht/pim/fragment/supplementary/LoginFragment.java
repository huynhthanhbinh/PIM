package com.bht.pim.fragment.supplementary;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Value;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * @author bht
 */
@Fragment(id = LoginFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/supplementary/LoginFragment.fxml")
public final class LoginFragment extends BaseComponentFragment {

    public static final String ID = "idfSLogin";

    @Value("${pim.client.username}")
    private String defaultUsername;
    @Value("${pim.client.password}")
    private String defaultPassword;

    @Resource
    private Context context;
    @FXML
    private VBox loginPane;
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
    protected void onCreated() {
        initAllLabels();
        initAllStyles();
        addAllEventListeners();
    }

    @Override
    protected void configLayout() {
        layout = loginPane;
    }

    @Override
    protected void bindChildren() {
        //
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(bLogin.textProperty(), "label.login.login");
        LanguageUtil.initLabel(bForgot.textProperty(), "label.login.forgot");
        LanguageUtil.initLabel(lIncorrect.textProperty(), "label.login.incorrect");
    }

    private void initAllStyles() {
        lIncorrect.setVisible(false);
    }

    private void addAllEventListeners() {
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

    @Override
    protected void registerChildren() {
        //
    }
}
