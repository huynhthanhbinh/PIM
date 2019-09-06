package com.bht.pim.base;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 *
 * @author bht
 */
public abstract class BaseComponentFragment extends BaseFragment {

    @FXML
    protected VBox mainPane;

    /**
     * init method use for MainPane fragment only
     *
     * @param baseComponent parent component of this fragment
     */
    final void initialize(BaseComponent baseComponent) {
        parentFragment = null;
        registerChildren();
        onInit(baseComponent);
    }


    /**
     * register all children fragments
     * by using registerNewFragment() method
     */
    protected abstract void registerChildren();
}
