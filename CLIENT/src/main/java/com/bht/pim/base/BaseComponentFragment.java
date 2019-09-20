package com.bht.pim.base;

import java.util.ArrayList;

import com.bht.pim.util.LoggingUtil;

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
     * init method use for MainPane fragment only
     *
     * @param baseComponent parent component of this fragment
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
     * register all children fragments
     * by using registerNewFragment() method
     */
    protected abstract void registerChildren();


    @Override
    protected final void onSwitch() {
        LOGGER.info(LoggingUtil.format("SHOW", "FXFragment", getClass().getSimpleName()));
        super.onSwitch();
    }


    @Override
    protected final void preLeft() {
        LOGGER.info(LoggingUtil.format("HIDE", "FXFragment", getClass().getSimpleName()));
        super.preLeft();
    }
}
