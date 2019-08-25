package com.bht.pim.fragment.children.group;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.GroupDto;
import com.bht.pim.fragment.children.ParentOwning;
import com.bht.pim.service.GroupService;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_GROUP_TABLE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/group/GroupTable.fxml")
public class GroupTable implements Initializable, ParentOwning {

    private static final int MAX_TABLE_ROW = 10;

    @Autowired
    private GroupService groupService;

    // binding
    @Getter
    private IntegerProperty pageCountProperty;
    @Getter
    private IntegerProperty pageIndexProperty;

    @FXML
    @Getter
    private VBox mainPane;
    @FXML
    private TableView<GroupDto> table;
    @FXML
    private TableColumn<GroupDto, Long> cId;
    @FXML
    private TableColumn<GroupDto, String> cVisa;
    @FXML
    private TableColumn<GroupDto, String> cFirstName;
    @FXML
    private TableColumn<GroupDto, String> cLastName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Group Table] Initialization");

        // init property for binding purposes
        initAllProperties();

        // for multilingual
        initAllLabels();

        // Init all inputs
        initAllFields();

        // Add all event-listener
        addAllEventListener();
    }

    @Override
    public void onSwitchParentFragment() {
        // Get all necessary data from server
        getListGroup(pageIndexProperty.get());
    }

    private void initAllProperties() {
        pageIndexProperty = new SimpleIntegerProperty();
        pageCountProperty = new SimpleIntegerProperty();
    }

    // for multilingual
    private void initAllLabels() {

    }

    // Init all table fields
    private void initAllFields() {
        cId.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cId.setResizable(false);

        cVisa.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        cVisa.setCellValueFactory(new PropertyValueFactory<>("leaderVisa"));
        cVisa.setResizable(false);

        cFirstName.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        cFirstName.setCellValueFactory(new PropertyValueFactory<>("leaderFirstName"));
        cFirstName.setResizable(false);

        cLastName.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        cLastName.setCellValueFactory(new PropertyValueFactory<>("leaderLastName"));
        cLastName.setResizable(false);
    }

    // Add all event-listener
    private void addAllEventListener() {
        table.skinProperty().addListener((observable, oldSkin, newSkin) -> {
            TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            header.reorderingProperty().addListener((observable0, oldValue, newValue) ->
                    header.setReordering(false));
        });

        pageIndexProperty.addListener((observable, oldValue, newValue) ->
                getListGroup(newValue.intValue()));
    }

    // Get all necessary data
    private void getListGroup(int pageIndex) {

        ObservableList<GroupDto> projectDtoList = groupService
                .getGroupList(MAX_TABLE_ROW, pageIndex);

        table.setItems(projectDtoList);
        double temp = groupService.getNumberOfGroups();
        log.info("Number of groups: " + (long) temp);
        pageCountProperty.set((int) Math.ceil(temp / MAX_TABLE_ROW));
    }
}
