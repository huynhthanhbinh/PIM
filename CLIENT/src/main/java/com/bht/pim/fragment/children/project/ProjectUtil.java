package com.bht.pim.fragment.children.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.ParentOwning;
import com.bht.pim.fragment.parent.project.ProjectCreate;
import com.bht.pim.fragment.parent.project.ProjectList;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.util.PimUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_UTIL,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectUtil.fxml")
public class ProjectUtil implements Initializable, ParentOwning {

    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;

    @FXML
    private AnchorPane utilPane;
    @FXML
    private TextField searchBox;
    @FXML
    private Button bSearch;
    @FXML
    private ComboBox<String> comboBoxStatus;
    @FXML
    private Label lNumberOfProjects;
    @FXML
    private Button bDeleteAll;
    @FXML
    private Button bNew;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView iSearch = new ImageView(PimUtil.getImage("search"));
        ImageView iDelete = new ImageView(PimUtil.getImage("delete"));
        ImageView iNew = new ImageView(PimUtil.getImage("add"));

        iSearch.setFitWidth(30);
        iSearch.setFitHeight(30);

        bSearch.setGraphic(iSearch);
        bNew.setGraphic(iNew);
        bDeleteAll.setGraphic(iDelete);
        bDeleteAll.setText("DELETE ALL");

        comboBoxStatus.getItems().addAll(
                "New", "Planned", "In progress", "Finished");

        bNew.setOnMouseClicked(event -> {
            log.info("[NEW] on mouse clicked");

            FragmentSwitching switching = new FragmentSwitching(
                    ProjectList.class,
                    ProjectCreate.class);

            context.send(AppConfiguration.COMPONENT_MAIN, switching);
        });
    }

    @Override
    public void onSwitchParentFragment() {

    }
}
