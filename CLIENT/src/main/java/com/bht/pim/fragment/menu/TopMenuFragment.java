package com.bht.pim.fragment.menu;

import java.util.Locale;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.dialog.dialogs.HelpDialog;
import com.bht.pim.perspective.DefaultPerspective;
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
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@Fragment(id = TopMenuFragment.ID, scope = Scope.PROTOTYPE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/menu/TopMenuFragment.fxml")
public final class TopMenuFragment extends BaseComponentFragment {

    static final String ID = "topMenuFragment";

    @Autowired
    private LanguageProperty languageProperty;
    @Autowired
    private HelpDialog helpDialog;
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
    protected void registerChildren() {
        //
    }

    @Override
    protected void onCreated() {
        initAllLabels();
        initAllStyles();
        addAllEventListeners();
    }

    @Override
    protected void configLayout() {
        //
    }

    @Override
    protected void bindChildren() {
        //
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(lApp.textProperty(), AppConfiguration.LABEL_PIM_MAIN);
    }

    private void initAllStyles() {
        logo.setPreserveRatio(true);
        if (languageProperty.getLocaleProperty().get().equals(Locale.ENGLISH)) {
            lEnglish.getStyleClass().add("active");
        } else {
            lFrench.getStyleClass().add("active");
        }
    }

    private void addAllEventListeners() {
        addLabelEnglishEventHandler();
        addLabelFrenchEventHandler();
        addButtonHelpEventHandler();
        addButtonLogoutEventHandler();
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
        bHelp.setOnMouseClicked(event -> {
            //log.info("[INFO] Clicked Help button");
            showModalDialog(helpDialog);
        });
    }

    private void addButtonLogoutEventHandler() {
        bLogout.setOnMouseClicked(event -> {
            //log.info("[INFO] Clicked Logout button");
            context.send(DefaultPerspective.ID, "show");
            AppConfiguration.LOGGED_IN_PROPERTY.set(false);
        });
    }
}