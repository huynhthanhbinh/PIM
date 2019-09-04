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
import javafx.util.Pair;
import lombok.Getter;

/**
 * @author bht
 */
public abstract class BaseComponent extends VBox implements FXComponent {

    protected static final Logger LOGGER = Logger.getLogger(BaseComponent.class);
    protected Context componentContext;
    protected List<ParentFragment> fragments;

    @Getter
    protected ManagedFragmentHandler currentFragment;

    public BaseComponent() {
        fragments = new ArrayList<>();
    }

    private void onStarted(FXComponentLayout layout) {
        initLayout();
        loadFragments();
        createFragmentList();
        initAllFragments(layout);
        assignChildren();
    }

    private void initAllFragments(FXComponentLayout layout) {
        fragments.forEach(parentFragment -> parentFragment.initialize(layout));
    }

    protected abstract void initComponent();

    protected abstract void initLayout();

    protected abstract void loadFragments();

    protected abstract void createFragmentList();

    protected abstract void assignChildren();

    protected abstract Node handleMessage(Message<Event, Object> message);

    @PostConstruct
    public final void onStartComponent(final FXComponentLayout componentLayout) {
        initComponent();
        LOGGER.info("[INIT] FXComponentLayout: " + componentContext.getId());
        onStarted(componentLayout);
    }

    @PreDestroy
    public final void onTearDownComponent(final FXComponentLayout componentLayout) {
        LOGGER.info("[DESTROY] FXComponentLayout: " + componentContext.getId());
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

    // for adding new child fragment to parent
    protected final <T extends ChildFragment> Pair<T, Node> registerChildFragment(Class<T> fragmentClass) {
        ManagedFragmentHandler<T> fragment = componentContext.getManagedFragmentHandler(fragmentClass);
        return new Pair<>(fragment.getController(), fragment.getFragmentNode());
    }

    // switch current-(parent)-fragment
    public static <T extends BaseComponent, F extends ParentFragment> void switchFragment(T t, Class<F> fragmentClazz) {
        ObservableList<Node> nodes = t.getChildren();
        nodes.clear();
        if (t.currentFragment != null && t.currentFragment.getController() instanceof ParentFragment) {
            ((ParentFragment) t.currentFragment.getController()).preSwitchToAnotherFragment();
        }
        ManagedFragmentHandler<F> target = t.componentContext.getManagedFragmentHandler(fragmentClazz);
        target.getController().onSwitchToThisFragment();
        t.currentFragment = target;
        nodes.add(t.currentFragment.getFragmentNode());
    }
}