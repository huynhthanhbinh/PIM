package com.bht.pim.fragment.children.pagination;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.ParentOwning;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PAGINATION,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/pagination/PimPagination.fxml")
public class PimPagination implements Initializable, ParentOwning {

    @FXML
    private HBox paginationPane;
    @FXML
    private Pagination pagination;


    @Override
    public void onSwitchParentFragment() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Pagination] On init view");
    }
}
