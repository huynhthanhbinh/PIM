package com.bht.pim.component;


import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Value;

import java.util.ResourceBundle;

@Log4j
@Getter
@Setter
@DeclarativeView(id = AppConfiguration.COMPONENT_BOTTOM, name = "BottomPane",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_BOTTOM,
        viewLocation = "/com/bht/pim/component/BottomPane.fxml")
public class BottomPane implements FXComponent {

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
    public Node postHandle(Node node, Message<Event, Object> message) throws Exception {

        if (message.getMessageBody() instanceof Exception) {
            log.info(message.getMessageBody());
            ((Exception) message.getMessageBody()).printStackTrace();
        } else {
            log.info("[PIM] show login page");
        }

        return null;
    }

    @Override
    public Node handle(Message<Event, Object> message) throws Exception {
        return null;
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout arg0,
                                 final ResourceBundle resourceBundle) {

        LanguageUtil.initLabel(bLogin.textProperty(), "label.login.login");
        LanguageUtil.initLabel(bForgot.textProperty(), "label.login.forgot");
        LanguageUtil.initLabel(lIncorrect.textProperty(), "label.login.incorrect");

        lIncorrect.setVisible(false);

        bLogin.setOnMouseClicked(event -> {
            if (username.getText().equals(defaultUsername)
                    && password.getText().equals(defaultPassword)) {

                context.send(AppConfiguration.PERSPECTIVE_PIM, "show");
                username.clear();
                password.clear();
                lIncorrect.setVisible(false);

            } else if (!username.getText().isEmpty() && !password.getText().isEmpty()) {

                lIncorrect.setVisible(true);
            }
        });
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout componentLayout) {
        log.info("[DESTROY] FXComponentLayout: " + context.getId());
    }

    @OnShow
    public void onShowComponent(final FXComponentLayout componentLayout) {
        log.info("[SHOW] FXComponentLayout: " + context.getId());
    }

    @OnHide
    public void onHide(final FXComponentLayout componentLayout) {
        log.info("[HIDE] FXComponentLayout: " + context.getId());
    }
}
