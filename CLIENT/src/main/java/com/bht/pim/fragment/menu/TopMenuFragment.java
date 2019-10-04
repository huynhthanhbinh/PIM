package com.bht.pim.fragment.menu;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.dialog.base.HelpDialog;
import com.bht.pim.perspective.DefaultPerspective;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.util.LanguageUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author bht
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@Fragment(id = TopMenuFragment.ID, scope = Scope.PROTOTYPE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/menu/TopMenuFragment.fxml")
public final class TopMenuFragment extends BaseComponentFragment {

    static final String ID = "topMenuFragment";
    private static final String ACTIVE_STYLE_CLASS = "active";

    private Map<Locale, Label> availableLingualLabels;
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
        addAllAvailableLingualLabels();
        addAllEventListeners();
        activateDefaultLingualLabel();
    }

    @Override
    protected void configLayout() {
        //
    }

    @Override
    protected void bindChildren() {
        //
    }

    private void activateDefaultLingualLabel() {
        availableLingualLabels.get(languageProperty.getLocaleProperty().get()).getStyleClass().add(ACTIVE_STYLE_CLASS);
    }

    private void addAllAvailableLingualLabels() {
        availableLingualLabels = new HashMap<>();
        availableLingualLabels.put(Locale.ENGLISH, lEnglish);
        availableLingualLabels.put(Locale.FRENCH, lFrench);
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(lApp.textProperty(), AppConfiguration.LABEL_PIM_MAIN);
    }

    private void initAllStyles() {
        logo.setPreserveRatio(true);
    }

    private void addAllEventListeners() {
        addLanguageChangeListener();
        addAllLanguageLabelListeners();
        addButtonHelpEventHandler();
        addButtonLogoutEventHandler();
    }

    private void addAllLanguageLabelListeners() {
        availableLingualLabels.forEach(this::initLanguageLabel);
    }

    private void addButtonHelpEventHandler() {
        bHelp.setOnMouseClicked(this::onMouseClickedHelp);
    }

    private void addButtonLogoutEventHandler() {
        bLogout.setOnMouseClicked(this::onMouseClickedLogout);
        bLogout.visibleProperty().bind(AppConfiguration.LOGGED_IN_PROPERTY);  // if logged-in --> show logout, vice versa
    }

    private void initLanguageLabel(Locale locale, Label lingualLabel) {
        lingualLabel.getStyleClass().add("clickable");
        lingualLabel.setOnMouseClicked(event -> {

            if (lingualLabel.getStyleClass().contains(ACTIVE_STYLE_CLASS)) {
                event.consume();
                return;
            }

            languageProperty.getLocaleProperty().set(locale);
        });
    }

    private void addLanguageChangeListener() {
        languageProperty.getLocaleProperty().addListener((observable, oldValue, newValue) -> {
            availableLingualLabels.get(oldValue).getStyleClass().remove(ACTIVE_STYLE_CLASS); // deactivate old lingual label
            availableLingualLabels.get(newValue).getStyleClass().add(ACTIVE_STYLE_CLASS);    // activate new lingual label
        });
    }

    @SuppressWarnings("unused")
    private void onMouseClickedHelp(MouseEvent event) {
        helpDialog.show();
    }

    @SuppressWarnings("unused")
    private void onMouseClickedLogout(MouseEvent event) {
        context.send(DefaultPerspective.ID, "show");
        AppConfiguration.LOGGED_IN_PROPERTY.set(false);
    }
}