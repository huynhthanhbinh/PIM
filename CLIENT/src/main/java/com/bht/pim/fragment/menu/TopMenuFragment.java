package com.bht.pim.fragment.menu;

import java.util.Locale;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import com.bht.pim.base.BaseFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.util.LanguageUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.extern.log4j.Log4j;

/**
 * @author bht
 */
@Log4j
@Controller
@Fragment(id = TopMenuFragment.ID, scope = Scope.PROTOTYPE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/menu/TopMenuFragment.fxml")
public class TopMenuFragment extends BaseFragment {

    static final String ID = "idfMenuTop";
    private LanguageProperty languageProperty = AppConfiguration.LANGUAGE_PROPERTY;

    @Resource
    private Context context;
    @FXML
    private Label lApp;
    @FXML
    private AnchorPane topMenu;
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

    @Override
    protected void onCreated() {
        LanguageUtil.initLabel(lApp.textProperty(), AppConfiguration.LABEL_PIM_MAIN);
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

    @Override
    protected void configLayout() {

    }

    @Override
    protected void onSwitch() {

    }

    @Override
    protected void preLeft() {

    }

    @Override
    protected void bindChildren() {

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
