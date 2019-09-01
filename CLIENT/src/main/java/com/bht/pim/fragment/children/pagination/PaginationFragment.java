package com.bht.pim.fragment.children.pagination;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.configuration.AppConfiguration;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import lombok.Getter;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author bht
 */
@Controller
@Fragment(id = PaginationFragment.ID,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/pagination/PaginationFragment.fxml")
public class PaginationFragment extends ChildFragment {

    static final String ID = "idfPagination"; // pagination-pane

    @FXML
    private HBox paginationPane;
    @FXML
    @Getter
    private Pagination pagination;


    @Override
    public void onCreated() {
        LOGGER.info("[Pagination] Initialization");
        pagination.setMaxPageIndicatorCount(10);
    }

    @Override
    public void onSwitchParentFragment() {
        // ...
    }
}