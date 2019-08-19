package com.bht.pim.fragment.children.pagination;

import com.bht.pim.configuration.AppConfiguration;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PAGINATION,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/pagination/PimPagination.fxml")
public class PimPagination {

    @FXML
    private HBox paginationPane;
    @FXML
    private Pagination pagination;
}
