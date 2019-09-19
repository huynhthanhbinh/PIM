package com.bht.pim.fragment.menu;

import java.util.Locale;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.perspective.DefaultPerspective;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.util.ImageUtil;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.workbench.PimWorkbench;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    static final String ID = "idfMenuTop";

    @Autowired
    private LanguageProperty languageProperty;
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
            log.info("[INFO] Clicked help button");
            showModalDialog(getHelpDialog());
        });
    }

    private void addButtonLogoutEventHandler() {
        bLogout.setOnMouseClicked(event -> {
            log.info("[INFO] Clicked Logout button");
            context.send(DefaultPerspective.ID, "show");
            AppConfiguration.LOGGED_IN_PROPERTY.set(false);
        });
    }

    private VBox getHelpDialog() {
        PimWorkbench.LAYOUT.getChildren().clear();


        VBox dialog = new VBox();
        dialog.getStylesheets().add("/com/bht/pim/dialog/style/DialogStyle.css");
        dialog.setId("dialog");
        dialog.setMaxSize(500, 300);
        dialog.setPrefSize(500, 300);
        dialog.setAlignment(Pos.TOP_CENTER);
        PimWorkbench.LAYOUT.setOnMouseClicked(event -> {
            if (!dialog.isHover()) {
                hideModalDialog();
            }
        });

        ImageView icon = new ImageView(ImageUtil.getImage("icon_inverse"));
        icon.setPreserveRatio(true);
        icon.setFitHeight(20);
        AnchorPane.setTopAnchor(icon, 5.0);
        AnchorPane.setLeftAnchor(icon, 3.0);


        Label title = new Label();
        title.setId("title");
        AnchorPane.setTopAnchor(title, 8.0);
        AnchorPane.setLeftAnchor(title, 30.0);
        title.setText("ABOUT");


        Button bClose = new Button();
        bClose.setId("bClose");
        AnchorPane.setTopAnchor(bClose, 2.0);
        AnchorPane.setRightAnchor(bClose, 2.0);
        AnchorPane.setBottomAnchor(bClose, 2.0);
        bClose.setOnMouseClicked(event -> hideModalDialog());
        bClose.setText("X");


        AnchorPane titleBar = new AnchorPane();
        titleBar.setId("titleBar");
        titleBar.setPrefSize(500, 30);
        titleBar.setMinSize(500, 30);
        titleBar.getChildren().addAll(icon, title, bClose);


        dialog.getChildren().add(titleBar);
        PimWorkbench.LAYOUT.getChildren().add(dialog);
        PimWorkbench.LAYOUT.setAlignment(Pos.CENTER);

        return PimWorkbench.LAYOUT;
    }
}