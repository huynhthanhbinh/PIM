package com.bht.pim.fragment.children.project;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.service.ProjectService;
import com.bht.pim.util.LanguageUtil;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * @author bht
 */
@Controller
@Fragment(id = ProjectPanelFragment.ID,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectPanelFragment.fxml")
public class ProjectPanelFragment extends ChildFragment {

    static final String ID = "idfPPanel";

    @Resource
    private Context context;
    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private ProjectService projectService;

    @FXML
    private HBox mainPane;
    @FXML
    private BarChart<String, Long> barChart;
    @FXML
    private TableView<Map.Entry<String, Long>> table;
    @FXML
    private PieChart pieChart;
    @FXML
    private TableColumn<Map.Entry<String, Long>, String> cStatus;
    @FXML
    private TableColumn<Map.Entry<String, Long>, Long> cCount;

    @Override
    public void onCreated() {
        LOGGER.info("[INIT] FXChildFragment  : " + ID);
        cStatus.setCellValueFactory(param -> statusMapper.getAvailableStatus().get(param.getValue().getKey()));
        cCount.setCellValueFactory(param -> new SimpleLongProperty(param.getValue().getValue()).asObject());

        LanguageUtil.initLabel(cStatus.textProperty(), "label.project.form.status");
        LanguageUtil.initLabel(cCount.textProperty(), "label.project.form.quantity");

        table.skinProperty().addListener((observable, oldSkin, newSkin) -> {
            TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            header.reorderingProperty().addListener((observable0, oldValue, newValue) ->
                    header.setReordering(false));
        });
    }

    @Override
    public Pane getLayout() {
        return mainPane;
    }

    @Override
    public void onSwitchParentFragment() {
        ObservableList<Map.Entry<String, Long>> items = FXCollections
                .observableArrayList(projectService.getProjectsGroupByStatus().entrySet());
        table.setItems(items);
    }
}
