package com.bht.pim.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Getter;

/**
 * @author bht
 */
public abstract class BaseComponent extends VBox implements FXComponent {

    protected static final Logger LOGGER = Logger.getLogger(BaseComponent.class);
    protected Context componentContext;
    @Getter
    private List<BaseComponentFragment> fragments;
    @Getter
    protected ManagedFragmentHandler<? extends BaseComponentFragment> currentFragment;

    public BaseComponent() {
        fragments = new ArrayList<>();
    }

    private void onStarted(FXComponentLayout layout) {
        initLayout();
        loadFragments();
    }

    protected abstract void initComponent();

    protected abstract void initLayout();

    protected abstract void loadFragments();

    protected abstract Node handleMessage(Message<Event, Object> message);

    @PostConstruct
    public final void onStartComponent(final FXComponentLayout componentLayout) {
        initComponent();
        LOGGER.info("[INIT] FXComponentLayout: " + getClass().getSimpleName());
        onStarted(componentLayout);
    }

    @PreDestroy
    public final void onTearDownComponent(final FXComponentLayout componentLayout) {
        LOGGER.info("[DESTROY] FXComponentLayout: " + getClass().getSimpleName());
    }

    @Override
    public final Node handle(Message<Event, Object> message) {
        return null;
    }

    @Override
    public final Node postHandle(Node node, Message<Event, Object> message) {
        if (message.getMessageBody() instanceof BasePerspective) {
            ((BasePerspective) message.getMessageBody()).addChildComponent(this);
        }
        return handleMessage(message);
    }

    final <F extends BaseFragment> ManagedFragmentHandler<F> registerNewFragment(Class<F> fClass) {
        LOGGER.info("[REGISTER] FXFragment: " + fClass.getSimpleName());
        return componentContext.getManagedFragmentHandler(fClass);
    }

    // for adding new main fragment to parent
    protected final <F extends BaseComponentFragment> ManagedFragmentHandler<F> registerMainFragment(Class<F> fClass) {
        ManagedFragmentHandler<F> fragmentHandler = registerNewFragment(fClass);
        fragments.add(fragmentHandler.getController().initialize(this));
        return fragmentHandler;
    }

    // switch current-(parent)-fragment
    public static <C extends BaseComponent, F extends BaseComponentFragment> void switchMainFragment(C c, Class<F> fragmentClazz) {
        ObservableList<Node> nodes = c.getChildren();
        nodes.clear();

        if (c.currentFragment != null && c.currentFragment.getController() != null) {
            c.currentFragment.getController().preLeft();
        }

        ManagedFragmentHandler<F> target = c.componentContext.getManagedFragmentHandler(fragmentClazz);
        target.getController().onSwitch();

        c.currentFragment = target;
        nodes.add(c.currentFragment.getFragmentNode());
    }
}