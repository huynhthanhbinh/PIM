package com.bht.pim.component;

import java.util.Locale;
import java.util.ResourceBundle;

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
import org.springframework.stereotype.Controller;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.util.LanguageUtil;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@DeclarativeView(id = AppConfiguration.COMPONENT_TOP, name = "TopPane",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_TOP,
        viewLocation = "/com/bht/pim/component/TopPane.fxml")
public class TopPane implements FXComponent {

    private LanguageProperty languageProperty = AppConfiguration.LANGUAGE_PROPERTY;
    @FXML
    private Label lApp;
    @FXML
    private AnchorPane topPane;
    @FXML
    private ImageView logo;
    @FXML
    private Label lEnglish;
    @FXML
    private Label lFrench;
    @FXML
    private ImageView bHelp;
    @FXML
    private ImageView bLogout;
    @Resource
    private Context context;

    @Override
    public Node handle(Message<Event, Object> message) {
        return null;
    }

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) {
        return null;
    }

    @PostConstruct
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {

        LanguageUtil.initLabel(lApp.textProperty(), AppConfiguration.LABEL_PIM_MAIN);

        topPane.prefWidthProperty().bind(
                layout.getGlassPane().widthProperty().subtract(20));

        logo.setPreserveRatio(true);

        if (languageProperty.getLocaleProperty().get().equals(Locale.ENGLISH)) {
            lEnglish.getStyleClass().add("active");
        } else {
            lFrench.getStyleClass().add("active");
        }

        addLabelEnglishEventHandler();
        addLabelFrenchEventHandler();
        addButtonHelpEventHandler();
        addButtonLogoutEventHandler();
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

    private void addLabelEnglishEventHandler() {
        lEnglish.getStyleClass().add("clickable");
        lEnglish.setOnMouseClicked(event -> {

            if (lEnglish.getStyleClass().contains("active")) {
                event.consume();
                return;
            }

            lFrench.getStyleClass().remove("active");
            lEnglish.getStyleClass().add("active");

            languageProperty.getLocaleProperty().set(Locale.ENGLISH);
        });
    }

    private void addLabelFrenchEventHandler() {
        lFrench.getStyleClass().add("clickable");
        lFrench.setOnMouseClicked(event -> {

            if (lFrench.getStyleClass().contains("active")) {
                event.consume();
                return;
            }

            lEnglish.getStyleClass().remove("active");
            lFrench.getStyleClass().add("active");

            languageProperty.getLocaleProperty().set(Locale.FRENCH);
        });
    }

    private void addButtonHelpEventHandler() {
        bHelp.setOnMouseClicked(event -> log.info("[PIM] Clicked help button"));
    }

    private void addButtonLogoutEventHandler() {
        bLogout.setOnMouseClicked(event -> {
            log.info("[PIM} Clicked Logout button");
            context.send(AppConfiguration.PERSPECTIVE_DEFAULT, "show");
            AppConfiguration.LOGGED_IN_PROPERTY.set(false);
        });
    }
}
