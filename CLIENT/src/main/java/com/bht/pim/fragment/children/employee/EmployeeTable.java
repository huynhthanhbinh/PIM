package com.bht.pim.fragment.children.employee;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.fragment.children.ParentOwning;
import com.bht.pim.service.EmployeeService;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.PimUtil;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_EMPLOYEE_TABLE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/employee/EmployeeTable.fxml")
public class EmployeeTable implements Initializable, ParentOwning {

    private static final int MAX_TABLE_ROW = 10;

    @Autowired
    private PimUtil pimUtil;
    @Autowired
    private EmployeeService employeeService;

    // binding
    @Getter
    private IntegerProperty pageCountProperty;
    @Getter
    private IntegerProperty pageIndexProperty;

    @FXML
    @Getter
    private VBox mainPane;
    @FXML
    private TableView<EmployeeDto> table;
    @FXML
    private TableColumn<EmployeeDto, Long> cId;
    @FXML
    private TableColumn<EmployeeDto, String> cVisa;
    @FXML
    private TableColumn<EmployeeDto, String> cFirstName;
    @FXML
    private TableColumn<EmployeeDto, String> cLastName;
    @FXML
    private TableColumn<EmployeeDto, LocalDate> cBirthday;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Employee Table] Initialization");

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
        getListEmployee(pageIndexProperty.get());
    }

    // for multilingual
    private void initAllLabels() {
        LanguageUtil.initLabel(cId.textProperty(), "label.table.id");
        LanguageUtil.initLabel(cVisa.textProperty(), "label.table.visa");
        LanguageUtil.initLabel(cFirstName.textProperty(), "label.table.name.first");
        LanguageUtil.initLabel(cLastName.textProperty(), "label.table.name.last");
        LanguageUtil.initLabel(cBirthday.textProperty(), "label.table.birthday");
    }

    private void initAllProperties() {
        pageIndexProperty = new SimpleIntegerProperty();
        pageCountProperty = new SimpleIntegerProperty();
    }

    // Get all necessary data
    private void getListEmployee(int pageIndex) {

        ObservableList<EmployeeDto> projectDtoList = employeeService
                .getEmployeeList(MAX_TABLE_ROW, pageIndex);

        table.setItems(projectDtoList);
        double temp = employeeService.getNumberOfEmployees();
        log.info("Number of employees: " + (long) temp);
        pageCountProperty.set((int) Math.ceil(temp / MAX_TABLE_ROW));
    }

    // Init all table fields
    private void initAllFields() {
        cId.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cId.setResizable(false);

        cVisa.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        cVisa.setCellValueFactory(new PropertyValueFactory<>("visa"));
        cVisa.setResizable(false);

        cFirstName.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        cFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        cFirstName.setResizable(false);

        cLastName.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        cLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cLastName.setResizable(false);


        cBirthday.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        cBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        cBirthday.setCellFactory(pimUtil::dateFormat);
        cBirthday.setResizable(false);
    }

    // Add all event-listener
    private void addAllEventListener() {
        table.skinProperty().addListener((observable, oldSkin, newSkin) -> {
            TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            header.reorderingProperty().addListener((observable0, oldValue, newValue) ->
                    header.setReordering(false));
        });

        pageIndexProperty.addListener((observable, oldValue, newValue) ->
                getListEmployee(newValue.intValue()));
    }
}
