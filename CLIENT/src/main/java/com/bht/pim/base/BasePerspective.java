package com.bht.pim.base;

import java.util.ArrayList;
import java.util.List;

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

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.ViewUtil;

import javafx.event.Event;
import lombok.Getter;

/**
 *
 * @author bht
 */
public abstract class BasePerspective implements FXPerspective {

    private static final Logger LOGGER = Logger.getLogger(BasePerspective.class);
    protected Context perspectiveContext;

    @Getter
    private List<BaseComponent> childComponents;


    @javax.annotation.PostConstruct
    private void onBeanCreation() {
        LOGGER.info("[SPRING] BeanCreation: " + getClass().getSimpleName());
    }


    @Override
    public void handlePerspective(Message<Event, Object> message,
                                  PerspectiveLayout perspectiveLayout) {
        handleMessage(message);
    }

    @PreDestroy
    public void onTearDownComponent(final FXComponentLayout componentLayout) {
        LOGGER.info("[DESTROY] FXPerspective: " + getClass().getSimpleName());
    }

    @OnShow
    public void onShow(final FXComponentLayout componentLayout) {
        LOGGER.info("[SHOW] FXPerspective: " + getClass().getSimpleName());
        onShowed();
        AppConfiguration.PERSPECTIVE_PROPERTY.set(this);
    }

    @OnHide
    public final void onHide(final FXComponentLayout componentLayout) {
        LOGGER.info("[HIDE] FXPerspective: " + getClass().getSimpleName());
    }

    @PostConstruct
    public final void onStart(final PerspectiveLayout perspectiveLayout,
                              final FXComponentLayout layout) {
        getContext();
        LOGGER.info("[INIT] FXPerspective: " + getClass().getSimpleName());
        childComponents = new ArrayList<>();
        onCreated(perspectiveLayout, layout);

        perspectiveContext.getComponentLayout().getGlassPane().getScene().getAccelerators()
                .put(ViewUtil.VIEW_GRAPHICS_KEY_COMBINATION, ViewUtil::viewGraphics);
    }

    final <T extends BaseComponent> void addChildComponent(T t) {
        childComponents.add(t);
    }

    protected abstract void getContext();

    protected abstract void onShowed();

    protected abstract void onCreated(final PerspectiveLayout perspectiveLayout, final FXComponentLayout layout);

    protected abstract void handleMessage(Message<Event, Object> message);
}