package com.bht.pim.fragment.children.project;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.component.MainPane;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.parent.project.ProjectCreateFragment;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.property.LanguageProperty;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.PimUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author bht
 */
@Controller
@Fragment(id = ProjectUtilFragment.ID,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectUtilFragment.fxml")
public class ProjectUtilFragment extends ChildFragment {

    static final String ID = "idfPUtil";

    private LanguageProperty languageProperty = AppConfiguration.LANGUAGE_PROPERTY;
    @Resource
    private Context context;
    @Autowired
    private StatusMapper statusMapper;

    @FXML
    private AnchorPane utilPane;
    @FXML
    @Getter
    private TextField searchBox;
    @FXML
    @Getter
    private ComboBox<String> comboBoxStatus;
    @FXML
    @Getter
    private Label lNumberOfProjects;
    @FXML
    private Label lSelected;
    @FXML
    @Getter
    private Button bDeleteAll;
    @FXML
    @Getter
    private Button bReset;
    @FXML
    private Button bNew;

    @Override
    public void onCreated() {
        LOGGER.info("[INIT] FXChildFragment  : " + ID);

        initComboBoxStatus();

        LanguageUtil.initLabel(searchBox.promptTextProperty(), "label.project.util.searchbox");
        LanguageUtil.initLabel(comboBoxStatus.promptTextProperty(), "label.project.util.status");
        LanguageUtil.initLabel(lSelected.textProperty(), "label.project.util.selected");
        LanguageUtil.initLabel(bDeleteAll.textProperty(), "label.project.util.deleteall");


        ImageView iReset = new ImageView(PimUtil.getImage("reset"));
        ImageView iDelete = new ImageView(PimUtil.getImage("delete"));
        ImageView iNew = new ImageView(PimUtil.getImage("add"));

        iReset.setFitWidth(110);
        iReset.setFitHeight(110);
        iReset.setPreserveRatio(true);

        bReset.setGraphic(iReset);
        bNew.setGraphic(iNew);
        bDeleteAll.setGraphic(iDelete);
        bDeleteAll.setVisible(false);

        bNew.setOnMouseClicked(event -> {
            LOGGER.info("[NEW] on mouse clicked");

            FragmentSwitching switching = new FragmentSwitching(
                    ProjectListFragment.class,
                    ProjectCreateFragment.class);

            context.send(MainPane.ID, switching);
        });

        bDeleteAll.setOnMouseClicked(event -> LOGGER.info("[DELETE ALL] on mouse clicked"));

        lNumberOfProjects.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Integer.valueOf(newValue) > 0) {
                bDeleteAll.setVisible(true);
                return;
            }
            bDeleteAll.setVisible(false);
        });
    }

    @Override
    public void onSwitchParentFragment() {
        // ...
    }

    private void initComboBoxStatus() {
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("NEW").get());
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("PLA").get());
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("INP").get());
        comboBoxStatus.getItems().add(statusMapper.toGuiStatus("FIN").get());

        languageProperty.getResourceBundleProperty()
                .addListener((observable, oldValue, newValue) -> {
                    int index = comboBoxStatus.getSelectionModel().getSelectedIndex();
                    comboBoxStatus.getItems().clear();

                    comboBoxStatus.getItems().add(statusMapper.toGuiStatus("NEW").get());
                    comboBoxStatus.getItems().add(statusMapper.toGuiStatus("PLA").get());
                    comboBoxStatus.getItems().add(statusMapper.toGuiStatus("INP").get());
                    comboBoxStatus.getItems().add(statusMapper.toGuiStatus("FIN").get());

                    comboBoxStatus.getSelectionModel().select(index);
                });
    }
}
