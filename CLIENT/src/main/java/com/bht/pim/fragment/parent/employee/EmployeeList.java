package com.bht.pim.fragment.parent.employee;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.employee.EmployeeTable;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.pagination.PimPagination;
import com.bht.pim.fragment.parent.ChildrenContaining;
import com.bht.pim.util.PimUtil;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
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
@Fragment(id = AppConfiguration.FRAGMENT_EMPLOYEE_LIST,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/parent/employee/EmployeeList.fxml")
public class EmployeeList implements Initializable, ChildrenContaining {

    private MainLabel mainLabel;
    private EmployeeTable employeeTable;
    private PimPagination pagination;

    @Resource
    private Context context;
    @FXML
    private VBox mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Employee List] On init scene\n");
        PimUtil.alignPane(mainPane, context);
    }

    @Override
    public <T> void addAllChildren(Pair<T, Node>[] children) {
        for (Pair<T, Node> child : children) {
            mainPane.getChildren().add(child.getValue());
        }

        mainLabel = (MainLabel) children[0].getKey();
        employeeTable = (EmployeeTable) children[1].getKey();
        pagination = (PimPagination) children[2].getKey();

        mainLabel.setLabelText(AppConfiguration.LABEL_EMPLOYEE_LIST);

        employeeTable.getMainPane().prefWidthProperty().bind(Bindings.
                when(mainPane.widthProperty().lessThan(1500))
                .then(mainPane.widthProperty().subtract(10))
                .otherwise(1500));
    }

    @Override
    public void onSwitchParentFragment() {
        log.info("Switching fragment, new fragment: " + getClass().getSimpleName());

        mainLabel.onSwitchParentFragment();
        employeeTable.onSwitchParentFragment();
        pagination.onSwitchParentFragment();
    }
}
