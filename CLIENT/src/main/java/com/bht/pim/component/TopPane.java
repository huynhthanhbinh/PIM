package com.bht.pim.component;

import com.bht.pim.configuration.AppConfiguration;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

import java.util.ResourceBundle;

@Log4j
@DeclarativeView(id = AppConfiguration.COMPONENT_TOP, name = "TopPane",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_TOP,
        viewLocation = "/com/bht/pim/component/TopPane.fxml")
public class TopPane implements FXComponent {
    @FXML
    private AnchorPane topPane;

    @FXML
    private ImageView logo;

    @FXML
    private Label lEnglish;

    @FXML
    private Label lFrench;

    @FXML
    private Label lHelp;

    @FXML
    private Label lLogout;

    @Resource
    private Context context;

    @Resource
    private ResourceBundle bundle;

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
        logo.setPreserveRatio(true);
        lEnglish.getStyleClass().add("active");

        lEnglish.getStyleClass().add("clickable");
        lFrench.getStyleClass().add("clickable");
        lHelp.getStyleClass().add("clickable");
        lLogout.getStyleClass().add("clickable");

        lEnglish.setOnMouseClicked(event -> {

            log.info("[TopPane] Clicked English");

            if (lEnglish.getStyleClass().contains("active")) {
                event.consume();
                return;
            }

            lFrench.getStyleClass().remove("active");
            lEnglish.getStyleClass().add("active");
        });

        lFrench.setOnMouseClicked(event -> {

            log.info("[TopPane] Clicked French");

            if (lFrench.getStyleClass().contains("active")) {
                event.consume();
                return;
            }

            lEnglish.getStyleClass().remove("active");
            lFrench.getStyleClass().add("active");
        });

        lHelp.setOnMouseClicked(event -> log.info("[TopPane] Clicked Help"));

        lLogout.setOnMouseClicked(event -> log.info("[TopPane] Clicked Log-out"));

        topPane.prefWidthProperty().bind(
                layout.getGlassPane().widthProperty().subtract(20));
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