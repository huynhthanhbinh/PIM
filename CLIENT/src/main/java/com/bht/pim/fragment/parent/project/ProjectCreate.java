package com.bht.pim.fragment.parent.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.Confirm;
import com.bht.pim.fragment.children.confirm.ConfirmBoxContaining;
import com.bht.pim.fragment.children.label.MainLabelContaining;
import com.bht.pim.message.impl.ConfirmBoxSetting;
import com.bht.pim.message.impl.MainLabelUpdating;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_CREATE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.SINGLETON)
public class ProjectCreate implements Initializable, ConfirmBoxContaining, MainLabelContaining {

    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;
    @FXML
    private VBox mainPane;
    @FXML
    private GridPane gridPane;


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Init this scene code go here
        log.info("[Project Create] On init scene ");

        log.info(mainPane.prefHeightProperty());
        log.info(context
                .getComponentLayout().getGlassPane().heightProperty());

        mainPane.prefHeightProperty().bind(context
                .getComponentLayout().getGlassPane().heightProperty().subtract(220));
        mainPane.prefWidthProperty().bind(context
                .getComponentLayout().getGlassPane().widthProperty().subtract(220));

        ConfirmBoxSetting confirmBoxSetting = new ConfirmBoxSetting(
                AppConfiguration.FRAGMENT_PROJECT_CREATE, "CREATE");

        context.send(AppConfiguration.COMPONENT_MAIN, confirmBoxSetting);

        MainLabelUpdating mainLabelUpdating = new MainLabelUpdating(
                AppConfiguration.FRAGMENT_PROJECT_CREATE,
                AppConfiguration.LABEL_PROJECT_CREATE);

        context.send(AppConfiguration.COMPONENT_MAIN, mainLabelUpdating);
    }

    @Override
    public void setConfirmLabel(String confirmLabel) {

    }

    @Override
    public Confirm getConfirmBox() {
        return null;
    }

    @Override
    public ManagedFragmentHandler getConfirmForm() {
        return null;
    }

    @Override
    public void setMainLabelText(String mainLabelText) {

    }
}