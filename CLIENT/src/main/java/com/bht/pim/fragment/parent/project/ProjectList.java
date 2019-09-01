package com.bht.pim.fragment.parent.project;

import com.bht.pim.base.ChildFragment;
import com.bht.pim.base.ParentFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.pagination.PimPagination;
import com.bht.pim.fragment.children.project.ProjectTable;
import com.bht.pim.fragment.children.project.ProjectUtil;
import com.bht.pim.fragment.parent.SuccessNeeding;
import com.bht.pim.util.PimUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.StringConverter;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author bht
 */
@Controller
@Fragment(id = ProjectList.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public class ProjectList extends ParentFragment implements SuccessNeeding {

    static final String ID = "idfPList";
    static final String LABEL = "label.project.list";

    private MainLabel mainLabel;
    private ProjectUtil projectUtil;
    private ProjectTable projectTable;
    private PimPagination pagination;
    private BooleanProperty successProperty;

    @Resource
    private Context context;

    @Override
    protected void onCreated() {
        LOGGER.info("[Project List] On init scene\n");
        PimUtil.alignPane(this, context);
        successProperty = new SimpleBooleanProperty();
    }

    @Override
    protected void getChildrenFragments(List<ChildFragment> children) {
        mainLabel = (MainLabel) children.get(0);
        projectUtil = (ProjectUtil) children.get(1);
        projectTable = (ProjectTable) children.get(2);
        pagination = (PimPagination) children.get(3);
    }

    @Override
    protected void configureEachChildFragment() {
        mainLabel.setLabelText(LABEL);
        projectUtil.getBReset().setOnMouseClicked(projectTable::onReset);
        projectUtil.getBDeleteAll().setOnMouseClicked(projectTable::onDeleteAllSelected);

        projectTable.getMainPane().prefWidthProperty().bind(Bindings.
                when(widthProperty().lessThan(1500))
                .then(widthProperty().subtract(10))
                .otherwise(1500));
    }

    @Override
    protected void bindChildrenFragments() {
        projectTable.setSearchBox(projectUtil.getSearchBox());
        pagination.getPagination().currentPageIndexProperty().bindBidirectional(projectTable.getPageIndexProperty());
        pagination.getPagination().pageCountProperty().bind(projectTable.getPageCountProperty());
        projectTable.getStatusProperty().bind(projectUtil.getComboBoxStatus().valueProperty());
        projectTable.getStatusSelection().bindBidirectional(projectUtil.getComboBoxStatus().selectionModelProperty());
        projectTable.getSuccessProperty().bind(successProperty);
        Bindings.bindBidirectional(projectUtil.getLNumberOfProjects().textProperty(), projectTable.getSelectedProperty(),
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