package com.bht.pim.fragment.parent.project;

import java.util.List;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.configuration.AppConfiguration;
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
 * @author bht
 */
@Controller
@Fragment(id = ProjectListFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class ProjectListFragment extends BaseComponentFragment implements SuccessNeeding {

    static final String ID = "idfPList";
    static final String LABEL = "label.project.list";

    private MainLabelFragment mainLabelFragment;
    private ProjectUtilFragment projectUtilFragment;
    private ProjectTableFragment projectTableFragment;
    private PaginationFragment pagination;
    private BooleanProperty successProperty;

    @Resource
    private Context context;

    @Override
    protected void onCreated() {
        LOGGER.info("[INIT] FXParentFragment : " + ProjectListFragment.ID);
        successProperty = new SimpleBooleanProperty();
    }

    @Override
    protected void configLayout() {

    }

    @Override
    protected void onSwitch() {

    }

    @Override
    protected void preLeft() {

    }

    @Override
    protected void bindChildren() {

    }

    @Override
    protected void getChildrenFragments(List<ChildFragment> children) {
        mainLabelFragment = (MainLabelFragment) children.get(0);
        projectUtilFragment = (ProjectUtilFragment) children.get(1);
        projectTableFragment = (ProjectTableFragment) children.get(2);
        pagination = (PaginationFragment) children.get(3);
    }

    @Override
    protected void configureEachChildFragment() {
        mainLabelFragment.setLabelText(LABEL);
        projectUtilFragment.getBReset().setOnMouseClicked(projectTableFragment::onReset);
        projectUtilFragment.getBDeleteAll().setOnMouseClicked(projectTableFragment::onDeleteAllSelected);
    }

    @Override
    protected void bindChildrenFragments() {
        projectTableFragment.setSearchBox(projectUtilFragment.getSearchBox());
        pagination.getPagination().currentPageIndexProperty().bindBidirectional(projectTableFragment.getPageIndexProperty());
        pagination.getPagination().pageCountProperty().bind(projectTableFragment.getPageCountProperty());
        projectTableFragment.getStatusProperty().bind(projectUtilFragment.getComboBoxStatus().valueProperty());
        projectTableFragment.getStatusSelection().bindBidirectional(projectUtilFragment.getComboBoxStatus().selectionModelProperty());
        projectTableFragment.getSuccessProperty().bind(successProperty);
        Bindings.bindBidirectional(projectUtilFragment.getLNumberOfProjects().textProperty(), projectTableFragment.getSelectedProperty(),
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