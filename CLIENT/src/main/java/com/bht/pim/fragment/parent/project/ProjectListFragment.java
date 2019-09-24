package com.bht.pim.fragment.parent.project;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.configuration.SpringConfiguration;
import com.bht.pim.fragment.children.label.MainLabelFragment;
import com.bht.pim.fragment.children.pagination.PaginationFragment;
import com.bht.pim.fragment.children.project.ProjectTableFragment;
import com.bht.pim.fragment.children.project.ProjectUtilFragment;
import com.bht.pim.fragment.parent.SuccessNeeding;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.StringConverter;

/**
 *
 * @author bht
 */
@Fragment(id = ProjectListFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = SpringConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/parent/common/MainFragment.fxml")
public final class ProjectListFragment extends BaseComponentFragment implements SuccessNeeding {

    static final String ID = "projectListFragment";
    private static final String LABEL = "label.project.list";

    private MainLabelFragment mainLabelFragment;
    private ProjectUtilFragment projectUtilFragment;
    private ProjectTableFragment projectTableFragment;
    private PaginationFragment pagination;
    private BooleanProperty successProperty;

    @Resource
    private Context context;

    @Override
    protected void registerChildren() {
        mainLabelFragment = registerNewFragment(MainLabelFragment.class);
        projectUtilFragment = registerNewFragment(ProjectUtilFragment.class);
        projectTableFragment = registerNewFragment(ProjectTableFragment.class);
        pagination = registerNewFragment(PaginationFragment.class);
    }

    @Override
    protected void onCreated() {
        successProperty = new SimpleBooleanProperty();

        mainLabelFragment.setLabelText(LABEL);
        projectUtilFragment.getBReset().setOnMouseClicked(projectTableFragment::onReset);
        projectUtilFragment.getBDeleteAll().setOnMouseClicked(projectTableFragment::onDeleteAllSelected);
    }

    @Override
    protected void configLayout() {
        // layout.doSomething
    }

    @Override
    protected void bindChildren() {
        projectTableFragment.setSearchBox(projectUtilFragment.getSearchBox());
        pagination.getPagination().currentPageIndexProperty().bindBidirectional(projectTableFragment.getPageIndexProperty());
        pagination.getPagination().pageCountProperty().bind(projectTableFragment.getPageCountProperty());
        projectTableFragment.getStatusProperty().bind(projectUtilFragment.getComboBoxStatus().valueProperty());
        projectTableFragment.getStatusSelection().bindBidirectional(projectUtilFragment.getComboBoxStatus().selectionModelProperty());
        projectTableFragment.getSuccessProperty().bind(successProperty);
        Bindings.bindBidirectional(
                projectUtilFragment.getLNumberOfProjects().textProperty(),
                projectTableFragment.getSelectedProperty(),
                new StringConverter<Number>() {
                    @Override
                    public String toString(Number number) {
                        return String.valueOf(number);
                    }

                    @Override
                    public Number fromString(String string) {
                        return Integer.valueOf(string);
                    }
                });
    }

    @Override
    public BooleanProperty getSuccessProperty() {
        return successProperty;
    }
}