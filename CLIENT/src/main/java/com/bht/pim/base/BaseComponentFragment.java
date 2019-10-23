package com.bht.pim.base;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * this class using Template Method Design-Pattern ^^
 *
 * @author bht
 */
public abstract class BaseComponentFragment extends BaseFragment {

    @FXML
    protected VBox mainPane;

    /**
     * init method use for component fragment only
     * @param baseComponent parent component of this component fragment
     */
    final BaseComponentFragment initialize(BaseComponent baseComponent) {
        parentFragment = null;
        layout = mainPane;
        component = baseComponent;
        childrenFragments = new ArrayList<>();

        registerChildren();
        onInit();
        return this;
    }


    /**
     * register all children fragments by using registerNewFragment() method
     */
    protected abstract void registerChildren();


    @Override
    protected final void onSwitch() { // let make this method cannot be override if this is ComponentFragment
        super.onSwitch();
    }


    @Override
    protected final void preLeft() { // let make this method cannot be override if this is ComponentFragment
        super.preLeft();
    }
}