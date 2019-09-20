package com.bht.pim.fragment.children.pagination;

import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;

import com.bht.pim.base.BaseFragment;
import com.bht.pim.configuration.AppConfiguration;

import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import lombok.Getter;

/**
 * @author bht
 */
@Fragment(id = PaginationFragment.ID,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/pagination/PaginationFragment.fxml")
public final class PaginationFragment extends BaseFragment {

    static final String ID = "paginationFragment"; // pagination-pane

    @FXML
    private VBox paginationPane;
    @FXML
    @Getter
    private Pagination pagination;


    @Override
    public void onCreated() {
        pagination.setMaxPageIndicatorCount(10);
    }

    @Override
    protected void configLayout() {
        layout = paginationPane;
    }

    @Override
    protected void bindChildren() {
        //
    }
}