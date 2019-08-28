package com.bht.pim.fragment.parent.group;

import java.net.URL;
import java.util.ResourceBundle;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Controller;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.children.group.GroupTable;
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

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_GROUP_LIST,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.SINGLETON,
        viewLocation = "/com/bht/pim/fragment/parent/group/GroupList.fxml")
public class GroupList implements Initializable, ChildrenContaining {

    private MainLabel mainLabel;
    private GroupTable groupTable;
    private PimPagination pagination;

    @Resource
    private Context context;
    @FXML
    private VBox mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Group List] On init scene\n");
        PimUtil.alignPane(mainPane, context);
    }

    @Override
    public <T> void addAllChildren(Pair<T, Node>[] children) {
        for (Pair<T, Node> child : children) {
            mainPane.getChildren().add(child.getValue());
        }

        mainLabel = (MainLabel) children[0].getKey();
        groupTable = (GroupTable) children[1].getKey();
        pagination = (PimPagination) children[2].getKey();

        mainLabel.setLabelText(AppConfiguration.LABEL_GROUP_LIST);

        pagination.getPagination().currentPageIndexProperty().bindBidirectional(groupTable.getPageIndexProperty());
        pagination.getPagination().pageCountProperty().bind(groupTable.getPageCountProperty());

        groupTable.getMainPane().prefWidthProperty().bind(Bindings.
                when(mainPane.widthProperty().lessThan(1000))
                .then(mainPane.widthProperty().subtract(10))
                .otherwise(1000));
    }

    @Override
    public void onSwitchParentFragment() {
        log.info("Switching fragment, new fragment: " + getClass().getSimpleName());

        mainLabel.onSwitchParentFragment();
        groupTable.onSwitchParentFragment();
        pagination.onSwitchParentFragment();
    }
}
