package com.bht.pim.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;

import javafx.scene.layout.Pane;
import lombok.Getter;

/**
 * this class using Template Method Design-Pattern ^^
 *
 * @author bht
 */
@Getter
public abstract class BaseFragment implements BaseBean {

    protected static final Logger LOGGER = Logger.getLogger(BaseFragment.class);
    protected BaseComponent component;
    protected Pane layout;
    protected BaseFragment parentFragment;
    protected List<BaseFragment> childrenFragments;

    /**
     * this is what initialize do for any fragment
     *      + new list of child fragments
     *      + init this fragment
     *      + binding all children fragments or controls inside
     *      + config the layout such as size (pref/min/max),...
     */
    final void onInit() {
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
     * on switching to this fragment
     * Override this method if this fragment
     * is final child fragment (not have children anymore)
     * and something need to be done on switch
     * otherwise, do not override it
     *
     * please call super method on overriding
     */
    protected void onSwitch() {
        childrenFragments.forEach(BaseFragment::onSwitch);
    }


    /**
     * on switching to another fragment
     * Override this method if this fragment
     * is final child fragment (not have children anymore)
     * and something need to be done before left
     * otherwise, do not override it
     *
     * please call super method on overriding
     */
    protected void preLeft() {
        childrenFragments.forEach(BaseFragment::preLeft);
    }


    /**
     * binding children fragment controls
     * after fragment is init / created
     * left it empty if no controls needs to bind
     */
    protected abstract void bindChildren();


    /**
     * register new child fragment method
     *
     * @param fClass fragment class
     * @return fragment controller of fClass
     */
    protected final <F extends BaseFragment> F registerNewFragment(Class<F> fClass) {
        ManagedFragmentHandler<F> fragmentHandler = component.registerNewFragment(fClass);
        layout.getChildren().add(fragmentHandler.getFragmentNode());
        return fragmentHandler.getController().initialize(component, this);
    }


    /**
     * initialize a child fragment
     *
     * @param component parent component of this
     * @param parentFragment parent fragment of this
     * @param <F> fragment class which extends BaseFragment
     * @return itself cast to its class
     */
    @SuppressWarnings("unchecked")
    final <F extends BaseFragment> F initialize(BaseComponent component, BaseFragment parentFragment) {
        childrenFragments = new ArrayList<>();
        parentFragment.childrenFragments.add(this);
        this.component = component;
        this.parentFragment = parentFragment;
        onInit();
        return (F) this;
    }
}