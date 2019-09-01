package com.bht.pim.base;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bht
 */
public abstract class BaseComponent implements FXComponent {

    protected static final Logger LOGGER = Logger.getLogger(BaseComponent.class);
    protected Context componentContext;
    protected List<ParentFragment> fragments;

    public BaseComponent() {
        fragments = new ArrayList<>();
    }

    private void onStarted(FXComponentLayout layout) {
        initComponent(layout);
        loadFragments();
        createFragmentList();
        initAllFragments();
        assignChildren();
    }

    private void initAllFragments() {
        fragments.forEach(ParentFragment::initialize);
    }

    protected abstract void initComponent(FXComponentLayout layout);

    protected abstract void loadFragments();

    protected abstract void createFragmentList();

    protected abstract void assignChildren();

    protected abstract Node handleMessage(Message<Event, Object> message);

    @PostConstruct
    public final void onStartComponent(final FXComponentLayout componentLayout) {
        onStarted(componentLayout);
        LOGGER.info("[INIT] FXComponentLayout: " + componentContext.getId());
    }

    @PreDestroy
    public final void onTearDownComponent(final FXComponentLayout componentLayout) {
        LOGGER.info("[DESTROY] FXComponentLayout: " + componentContext.getId());
    }

    @OnShow
    public final void onShowComponent(final FXComponentLayout componentLayout) {
        LOGGER.info("[SHOW] FXComponentLayout: " + componentContext.getId());
    }

    @OnHide
    public final void onHide(final FXComponentLayout componentLayout) {
        LOGGER.info("[HIDE] FXComponentLayout: " + componentContext.getId());
    }

    @Override
    public final Node handle(Message<Event, Object> message) {
        return null;
    }

    @Override
    public final Node postHandle(Node node, Message<Event, Object> message) {
        return handleMessage(message);
    }

    // for adding new child fragment to parent
    protected final <T extends ChildFragment> Pair<T, Node> registerChildFragment(Class<T> fragmentClass) {
        ManagedFragmentHandler<T> fragment = componentContext.getManagedFragmentHandler(fragmentClass);
        return new Pair<>(fragment.getController(), fragment.getFragmentNode());
    }
}
