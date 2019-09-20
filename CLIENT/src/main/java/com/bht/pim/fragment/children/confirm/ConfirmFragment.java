package com.bht.pim.fragment.children.confirm;

import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;

import com.bht.pim.base.BaseFragment;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * @author bht
 */
@Fragment(id = ConfirmFragment.ID, scope = Scope.PROTOTYPE,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        viewLocation = "/com/bht/pim/fragment/children/confirm/ConfirmFragment.fxml")
public final class ConfirmFragment extends BaseFragment {

    static final String ID = "confirmFragment"; // ok_cancel box

    public static final String LABEL_CONFIRM_CREATE = "label.confirm.form.create";
    public static final String LABEL_CONFIRM_UPDATE = "label.confirm.form.update";
    public static final String LABEL_CONFIRM_CANCEL = "label.confirm.form.cancel";
    public static final String LABEL_CONFIRM_MODIFY = "label.confirm.form.modify";
    public static final String LABEL_CONFIRM_RETURN = "label.confirm.form.return";

    @FXML
    private VBox confirmPane;
    @FXML
    private Button bSubmit;
    @FXML
    private Button bCancel;

    @Override
    public void onCreated() {
        //
    }

    @Override
    protected void configLayout() {
        layout = confirmPane;
    }

    @Override
    protected void bindChildren() {
        //
    }

    @FXML
    public void setLabelConfirm(String label) {
        LanguageUtil.initLabel(bSubmit.textProperty(), label);
    }

    @FXML
    public void setLabelCancel(String label) {
        LanguageUtil.initLabel(bCancel.textProperty(), label);
    }

    // if user click submit
    public void setOnSubmit(EventHandler<MouseEvent> onSubmit) {
        bSubmit.setOnMouseClicked(onSubmit);
    }

    // if user click cancel
    public void setOnCancel(EventHandler<MouseEvent> onCancel) {
        bCancel.setOnMouseClicked(onCancel);
    }
}
