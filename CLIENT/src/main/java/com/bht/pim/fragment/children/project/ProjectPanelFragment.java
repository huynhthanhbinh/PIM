package com.bht.pim.fragment.children.project;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.property.LanguageProperty;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
    private static final LanguageProperty LANGUAGE_PROPERTY = AppConfiguration.LANGUAGE_PROPERTY;
    private ObservableList<Map.Entry<String, Long>> items;

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
        initAllLabels();
        addAllEventListeners();
    }

    @Override
    public Pane getLayout() {
        return mainPane;
    }

    @Override
    public void onSwitchParentFragment() {
        Map<String, Long> groups = projectService.getProjectsGroupByStatus();
        items = FXCollections.observableArrayList(groups.entrySet());
        loadBarChart();
        loadPieChart();

        ObservableList<Map.Entry<String, Long>> itemsWithTotal =
                FXCollections.observableArrayList(groups.entrySet());
        itemsWithTotal.add(4, totalEntry());
        table.setItems(itemsWithTotal);
        table.getSelectionModel().clearSelection();
    }

    private void loadPieChart() {
        pieChart.setData(items.stream()
                .map(item -> new PieChart.Data(statusMapper.toGuiStatus(item.getKey()).get(), item.getValue()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }

    private void loadBarChart() {
        barChart.setData(FXCollections.observableArrayList(Collections.singletonList(new XYChart.Series<>(items.stream()
                .map(item -> new BarChart.Data<>(statusMapper.toGuiStatus(item.getKey()).get(), item.getValue()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList))))));
    }

    private void initAllLabels() {
        cStatus.setCellValueFactory(param -> statusMapper.getAvailableStatus().get(param.getValue().getKey()));
        cCount.setCellValueFactory(param -> new SimpleLongProperty(param.getValue().getValue()).asObject());

        LanguageUtil.initLabel(cStatus.textProperty(), "label.project.form.status");
        LanguageUtil.initLabel(cCount.textProperty(), "label.project.form.quantity");
        LanguageUtil.initLabel(barChart.getXAxis().labelProperty(), "label.project.form.status");
        LanguageUtil.initLabel(barChart.getYAxis().labelProperty(), "label.project.form.quantity");

        barChart.setLegendVisible(false);
        pieChart.setLegendVisible(false);
    }

    private void addAllEventListeners() {
        table.skinProperty().addListener((observable, oldSkin, newSkin) -> {
            TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            header.reorderingProperty().addListener((observable0, oldValue, newValue) ->
                    header.setReordering(false));
        });

        items = FXCollections.emptyObservableList();
        LANGUAGE_PROPERTY.getLocaleProperty().addListener((observable, oldValue, newValue) -> {
            loadBarChart();
            loadPieChart();
        });
    }

    private Map.Entry<String, Long> totalEntry() {
        return new Map.Entry<String, Long>() {
            @Override
            public String getKey() {
                return "TOT"; //TOTAL
            }

            @Override
            public Long getValue() {
                return projectService.getNumberOfProjects();
            }

            @Override
            public Long setValue(Long value) {
                return value;
            }
        };
    }
}