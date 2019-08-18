package com.bht.pim.fragment.parent.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.confirm.Confirm;
import com.bht.pim.fragment.children.confirm.ConfirmBoxContaining;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.label.MainLabelContaining;
import com.bht.pim.fragment.children.project.ProjectEditableForm;
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

    private ManagedFragmentHandler<MainLabel> mainLabelFragment;
    private ManagedFragmentHandler<ProjectEditableForm> projectEditableFormFragment;
    private ManagedFragmentHandler<Confirm> confirmFragment;


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public String getConfirmLabel() {
        return "CREATE";
    }

    @Override
    public void setConfirmLabel(String confirmLabel) {
        confirmFragment.getController().setConfirmLabelText(confirmLabel);
    }

    @Override
    public ManagedFragmentHandler getConfirmBox() {
        return confirmFragment;
    }

    @Override
    public ManagedFragmentHandler getConfirmForm() {
        return projectEditableFormFragment;
    }

    @Override
    public void setMainLabelText(String mainLabelText) {
        mainLabelFragment.getController().setLabelText(mainLabelText);
    }
}