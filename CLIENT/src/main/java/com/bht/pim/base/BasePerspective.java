package com.bht.pim.base;

import javafx.event.Event;
import org.apache.log4j.Logger;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.perspective.FXPerspective;

/**
 * @author bht
 */
public abstract class BasePerspective implements FXPerspective {

    protected static final Logger LOGGER = Logger.getLogger(BasePerspective.class);
    protected Context perspectiveContext;

    @Override
    public void handlePerspective(Message<Event, Object> message,
                                  PerspectiveLayout perspectiveLayout) {

        LOGGER.info("[HANDLE] FXPerspective: " + perspectiveContext.getId());
        handleMessage(message);
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout componentLayout) {
        LOGGER.info("[DESTROY] FXPerspective: " + perspectiveContext.getId());
    }

    @OnShow
    public void onShow(final FXComponentLayout componentLayout) {
        LOGGER.info("[SHOW] FXPerspective: " + perspectiveContext.getId());
        onShowed();
    }

    @OnHide
    public final void onHide(final FXComponentLayout componentLayout) {
        LOGGER.info("[HIDE] FXPerspective: " + perspectiveContext.getId());
    }

    @PostConstruct
    public final void onStart(final PerspectiveLayout perspectiveLayout,
                              final FXComponentLayout layout) {
        getContext();
        LOGGER.info("[INIT] FXPerspective: " + perspectiveContext.getId());
        onCreated(perspectiveLayout, layout);
    }

    protected abstract void getContext();

    protected abstract void onShowed();

    protected abstract void onCreated(final PerspectiveLayout perspectiveLayout, final FXComponentLayout layout);

    protected abstract void handleMessage(Message<Event, Object> message);
}