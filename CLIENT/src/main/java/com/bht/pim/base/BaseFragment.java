package com.bht.pim.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;

import javafx.scene.layout.Pane;
import lombok.Getter;

/**
 *
 * @author bht
 */
public abstract class BaseFragment {

    protected static final Logger LOGGER = Logger.getLogger(BaseFragment.class);
    protected BaseComponent component;

    @Getter
    protected Pane layout;
    @Getter
    protected BaseFragment parentFragment;
    @Getter
    private List<BaseFragment> childFragment;

    /**
     * init method use for other fragments except MainPaneFragment
     *
     * @param parent parent of current fragment
     * @return current fragment after init
     */
    final BaseFragment initialize(BaseFragment parent) {
        parentFragment = parent;
        onInit(parentFragment.component);
        return this;
    }


    /**
     * this is what initialize do for any fragment
     *      + get the component contains this
     *      + new list of child fragments
     *      + init this fragment
     *      + binding all children fragments or controls inside
     *      + config the layout such as size (pref/min/max),...
     *
     * @param baseComponent component contains this fragment
     */
    void onInit(BaseComponent baseComponent) {
        LOGGER.info("[INIT] On init fragment: " + getClass().getSimpleName());
        component = baseComponent;
        childFragment = new ArrayList<>();

        onCreated();
        bindChildren();
        configLayout();
    }


    /**
     * consider what to do on initialization
     */
    protected abstract void onCreated();


    /**
     * config layout after fragment is created
     * config size (pref, min, max)
     * bind height / width with parent,...
     */
    protected abstract void configLayout();


    /**
     * on switching to another main fragment
     * left it empty if nothing needs to consider
     */
    protected abstract void onSwitch();


    /**
     * on switching to another main fragment
     * left it empty if nothing needs to consider
     */
    protected abstract void preLeft();


    /**
     * binding children fragment controls
     * after fragment is init / created
     * left it empty if no controls needs to bind
     */
    protected abstract void bindChildren();


    /**
     * on switch to this main fragment
     * this method will be invoked in component
     * it will then recursive call to all children
     */
    final void onSwitchToThisFragment() {
        childFragment.forEach(BaseFragment::onSwitchToThisFragment);
        onSwitch();
    }


    /**
     * on left this main fragment
     * this method will be invoked in component
     * it will then recursive call to all children
     */
    final void preLeftToAnotherFragment() {
        childFragment.forEach(BaseFragment::preLeftToAnotherFragment);
        preLeft();
    }


    /**
     * @param fClass fragment class
     * @return fragmentHandler of fClass
     */
    protected final <F extends BaseFragment> ManagedFragmentHandler<F> registerNewFragment(Class<F> fClass) {
        LOGGER.info("[REGISTER] register new child fragment: " + getClass().getSimpleName());
        ManagedFragmentHandler<F> fragmentHandler = component.registerNewFragment(fClass);
        childFragment.add(fragmentHandler.getController().initialize(this));
        return fragmentHandler;
    }
}