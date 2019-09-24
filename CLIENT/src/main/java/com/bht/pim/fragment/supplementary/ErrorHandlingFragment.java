package com.bht.pim.fragment.supplementary;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.configuration.SpringConfiguration;
import com.bht.pim.perspective.PimPerspective;
import com.bht.pim.util.LanguageUtil;

import io.grpc.ManagedChannel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j;

/**
 * @author bht
 */
@Log4j
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@Fragment(id = ErrorHandlingFragment.ID, scope = Scope.SINGLETON,
        resourceBundleLocation = SpringConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/supplementary/ErrorHandlingFragment.fxml")
public final class ErrorHandlingFragment extends BaseComponentFragment {

    public static final String ID = "errorHandlingFragment";

    @Autowired
    private ManagedChannel channel;

    @Resource
    private Context context;
    @FXML
    private VBox errorPane;
    @FXML
    private Label lUnexpected;
    @FXML
    private Label lPlease;
    @FXML
    private Label lContact;
    @FXML
    private Label lOr;
    @FXML
    private Label lReload;
    @FXML
    private Label lError;
    @FXML
    private TextArea detailField;

    @Override
    protected void onCreated() {
        initAllLabels();
        initAllStyles();
        addAllEventListeners();
    }

    @Override
    protected void configLayout() {
        layout = errorPane;
    }

    @Override
    protected void bindChildren() {
        //
    }

    private void initAllLabels() {
        LanguageUtil.initLabel(lUnexpected.textProperty(), "label.error.unexpectederror");
        LanguageUtil.initLabel(lPlease.textProperty(), "label.error.please");
        LanguageUtil.initLabel(lContact.textProperty(), "label.error.contactadmin");
        LanguageUtil.initLabel(lOr.textProperty(), "label.error.or");
        LanguageUtil.initLabel(lReload.textProperty(), "label.error.reload");
        LanguageUtil.initLabel(lError.textProperty(), "label.error.detail");
    }

    private void initAllStyles() {
        detailField.setEditable(false);
    }

    private void addAllEventListeners() {
        lContact.setOnMouseClicked(this::onContactAdmin);
        lReload.setOnMouseClicked(this::onReloadApp);
    }

    public void setDetail(Throwable detail) {
        log.error("[ERROR] " + detail);
        detailField.setText(detail.getClass().getName()
                + "\n" + detail.getMessage());
    }

    private void onContactAdmin(MouseEvent mouseEvent) {
        // ...
    }

    private void onReloadApp(MouseEvent mouseEvent) {
        channel.resetConnectBackoff();
        context.send(PimPerspective.ID, "show");
    }

    @Override
    protected void registerChildren() {
        //
    }
}
